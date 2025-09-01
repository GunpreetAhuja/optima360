
package com.optima360;

import com.optima360.config.JmsConfig;
import com.optima360.service.ReportProducer;
import org.apache.activemq.broker.BrokerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.activemq.broker-url=tcp://localhost:61617",
        "spring.activemq.user=admin",
        "spring.activemq.password=admin"
})
public class MessagingIntegrationTest {

    private static BrokerService broker;

    @Autowired
    private ReportProducer producer;

    private static CountDownLatch queueLatch = new CountDownLatch(1);
    private static CountDownLatch topicLatch = new CountDownLatch(1);

    @BeforeAll
    static void startEmbeddedBroker() throws Exception {
        broker = new BrokerService();
        broker.addConnector("tcp://localhost:61617");
        broker.setUseJmx(false);
        broker.setPersistent(false);
        broker.start();
    }

    @AfterAll
    static void stopEmbeddedBroker() throws Exception {
        if (broker != null) {
            broker.stop();
        }
    }

    @Test
    void endToEndQueueAndTopic() throws Exception {
        producer.enqueueReportRequest("T-1");
        producer.publishReportCompleted("T-1");

        assertTrue(queueLatch.await(5, TimeUnit.SECONDS), "Queue message not received");
        assertTrue(topicLatch.await(5, TimeUnit.SECONDS), "Topic message not received");
    }

    @JmsListener(destination = JmsConfig.REPORT_QUEUE, containerFactory = "queueListenerFactory")
    public void queueListener(String msg) {
        queueLatch.countDown();
    }

    @JmsListener(destination = JmsConfig.REPORT_TOPIC, containerFactory = "topicListenerFactory")
    public void topicListener(String msg) {
        topicLatch.countDown();
    }
}

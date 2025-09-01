
package com.optima360.service;

import com.optima360.config.JmsConfig;
import jakarta.jms.Queue;
import jakarta.jms.Topic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportProducer {

    private final JmsTemplate queueJmsTemplate;
    private final JmsTemplate topicJmsTemplate;
    private final Queue reportQueue;
    private final Topic reportTopic;

    public ReportProducer(JmsTemplate queueJmsTemplate, JmsTemplate topicJmsTemplate, Queue reportQueue, Topic reportTopic) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.topicJmsTemplate = topicJmsTemplate;
        this.reportQueue = reportQueue;
        this.reportTopic = reportTopic;
    }

    public void enqueueReportRequest(String reportId) {
        queueJmsTemplate.convertAndSend(reportQueue, reportId);
        System.out.println("📤 Queued report request -> " + reportId);
    }

    public void publishReportCompleted(String reportId) {
        topicJmsTemplate.convertAndSend(reportTopic, reportId);
        System.out.println("📣 Published report completed -> " + reportId);
    }
}

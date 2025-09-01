
package com.optima360.service;

import com.optima360.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReportConsumer {

    // Consume from Queue (one consumer processes a job)
    @JmsListener(destination = JmsConfig.REPORT_QUEUE, containerFactory = "queueListenerFactory")
    public void processReport(String reportId) {
        System.out.println("📥 Received report request: " + reportId);
        try {
            // Simulate heavy processing
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Report " + reportId + " generated successfully.");
    }
}

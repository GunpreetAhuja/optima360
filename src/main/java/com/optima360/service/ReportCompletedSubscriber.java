
package com.optima360.service;

import com.optima360.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReportCompletedSubscriber {

    // Multiple subscribers could listen to this topic in a real system
    @JmsListener(destination = JmsConfig.REPORT_TOPIC, containerFactory = "topicListenerFactory")
    public void onReportCompleted(String reportId) {
        System.out.println("🔔 Subscriber received 'report completed' event for: " + reportId);
        // e.g., send email, update dashboard, write audit log...
    }
}

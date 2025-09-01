
package com.optima360.controller;

import com.optima360.service.ReportProducer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportProducer reportProducer;

    public ReportController(ReportProducer reportProducer) {
        this.reportProducer = reportProducer;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> requestReport(@PathVariable @NotBlank String id) {
        reportProducer.enqueueReportRequest(id);
        return ResponseEntity.accepted().body("Report request for " + id + " accepted and queued.");
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> markReportComplete(@PathVariable String id) {
        reportProducer.publishReportCompleted(id);
        return ResponseEntity.ok("Report " + id + " completion event published.");
    }
}

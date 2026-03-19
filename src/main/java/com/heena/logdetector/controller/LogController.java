package com.heena.logdetector.controller;

import com.heena.logdetector.service.LogAnalysisService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogAnalysisService service;

    public LogController(LogAnalysisService service) {
        this.service = service;
    }

    // POST /api/logs/analyze
    // Upload a .log file and receive a plain-text summary report
    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> analyze(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }
        try {
            String report = service.analyze(file);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to analyze log file: " + e.getMessage());
        }
    }

    // GET /api/logs/health
    // Quick sanity check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Log Detector is running.");
    }
}
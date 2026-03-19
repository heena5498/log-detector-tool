package com.heena.logdetector.service;

import com.heena.logdetector.LogAnalyzer;
import com.heena.logdetector.LogParser;
import com.heena.logdetector.LogReportGenerator;
import com.heena.logdetector.model.LogEntry;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Service
public class LogAnalysisService {

    public String analyze(MultipartFile file) throws Exception {
        // Read raw lines from uploaded file
        List<String> rawLines = new BufferedReader(
                new InputStreamReader(file.getInputStream()))
                .lines()
                .toList();

        // Use your existing classes exactly as they expect — via constructor
        LogParser parser = new LogParser(rawLines);
        List<LogEntry> entries = parser.parseLines();

        LogAnalyzer analyzer = new LogAnalyzer(entries);
        Map<String, Integer> levelCounts = analyzer.countLogLevels();
        Map<String, Integer> messageFreq = analyzer.countMessageFrequency();

        LogReportGenerator generator = new LogReportGenerator(
                entries.size(), levelCounts, messageFreq
        );
        return generator.generateReport();
    }
}
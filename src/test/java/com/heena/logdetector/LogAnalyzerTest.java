package com.heena.logdetector;

import com.heena.logdetector.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogAnalyzerTest {

    @Test
    public void checkLogEntriesEmpty(){
        List<LogEntry> logEntries = new ArrayList<>();
        LogAnalyzer logAnalyzer = new LogAnalyzer(logEntries);
        Map<String, Integer> logCount =  logAnalyzer.countLogLevels();
        assertTrue(logCount.isEmpty());
        Map<String, Integer> messageCount =  logAnalyzer.countMessageFrequency();
        assertTrue(messageCount.isEmpty());
    }

    @Test
    public void shouldCountSingleInfoLog(){
        LogEntry entry = new LogEntry("2026-03-07 10:15:20", "INFO",  "Application started successfully");
        List<LogEntry> entries = new ArrayList<>();
        entries.add(entry);
        LogAnalyzer logAnalyzer = new LogAnalyzer(entries);
        Map<String, Integer> logCount =  logAnalyzer.countLogLevels();
        assertTrue(logCount.containsKey("INFO"));
        assertTrue(logCount.get("INFO") == 1);
    }

    @Test
    public void shouldCountTotalInfoLogs(){
        List<LogEntry> entries = new ArrayList<>();
        LogEntry entry1 = new LogEntry("2026-03-07 10:15:20", "INFO",  "Application started successfully");
        LogEntry entry2 = new LogEntry("2026-03-07 10:15:24", "INFO", "Loading configuration from config.yml");
        LogEntry entry3 = new LogEntry("2026-03-07 10:15:27", "WARN", "Database connection is slow");
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        LogAnalyzer logAnalyzer = new LogAnalyzer(entries);
        Map<String, Integer> logCount =  logAnalyzer.countLogLevels();
        assertTrue(logCount.containsKey("INFO"));
        assertTrue(logCount.get("INFO") == 2);
    }

    @Test
    public void shouldCountTotalMessageFrequency(){
        List<LogEntry> entries = new ArrayList<>();
        LogEntry entry1 = new LogEntry("2026-03-07 10:15:20", "ERROR",  "Failed to connect to database");
        LogEntry entry2 = new LogEntry("2026-03-07 10:15:24", "ERROR", "Failed to connect to database");
        LogEntry entry3 = new LogEntry("2026-03-07 10:15:27", "WARN", "Database connection is slow");
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        LogAnalyzer logAnalyzer = new LogAnalyzer(entries);
        Map<String, Integer> messageCount =  logAnalyzer.countMessageFrequency();
        assertTrue(messageCount.containsKey("Failed to connect to database"));
        assertTrue(messageCount.get("Failed to connect to database") == 2);
    }
}

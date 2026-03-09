package com.heena.logdetector;

import com.heena.logdetector.model.LogEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private List<LogEntry> logEntries;

    public LogAnalyzer(List<LogEntry> entries){
        this.logEntries = entries;
    }

    public Map<String, Integer> countLogLevels(){
        Map<String, Integer> levelCounts = new HashMap<>();
        for(LogEntry entry : logEntries){
            String level = entry.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0)  + 1);
        }
        return levelCounts;
    }

    public Map<String, Integer> countMessageFrequency(){
        Map<String, Integer> messageFreq = new HashMap<>();
        for(LogEntry entry : logEntries){
            String message = entry.getMessage();
            messageFreq.put(message, messageFreq.getOrDefault(message, 0)  + 1);
        }
        return messageFreq;
    }
}

package com.heena.logdetector;

import java.io.*;
import java.util.Map;

public class LogReportGenerator {
    private int totalEntries;
    private Map<String, Integer> logLevels;
    private Map<String, Integer> messageFreq;

    public LogReportGenerator(int totalEntries, Map<String, Integer> logLevels, Map<String, Integer> messageFreq){
        this.totalEntries = totalEntries;
        this.logLevels = logLevels;
        this.messageFreq = messageFreq;
    }

    public String generateReport(){
        StringBuilder sb = new StringBuilder();
        sb.append("Log Summary Report\n");
        sb.append("====================\n\n");
        sb.append("Total Log Entries Analyzed: " + totalEntries + "\n\n");
        sb.append("Log Level Counts\n");
        sb.append("--------------------\n");
        sb.append("INFO: " + logLevels.getOrDefault("INFO", 0) + "\n");
        sb.append("WARN: " + logLevels.getOrDefault("WARN", 0) + "\n");
        sb.append("ERROR: " + logLevels.getOrDefault("ERROR", 0) + "\n\n");

        sb.append("Repeated Messages\n");
        sb.append("--------------------\n");
        boolean foundRepeated = false;
        for (Map.Entry<String, Integer> entry : messageFreq.entrySet()) {
            if (entry.getValue() > 1) {
                foundRepeated = true;
                sb.append(entry.getKey() + " -> " + entry.getValue()+ "\n");
            }
        }
        if(!foundRepeated){
            sb.append("No repeated messages found\n");
        }
        return sb.toString();
    }
    public void writeReportToFile(String filepath){
        String report = generateReport();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            writer.write(report);
            System.out.println("Report written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing report");
        }
    }
}

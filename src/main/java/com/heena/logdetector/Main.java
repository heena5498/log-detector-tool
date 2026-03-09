package com.heena.logdetector;

import com.heena.logdetector.model.LogEntry;

import java.util.List;
import java.util.Map;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LogReader readObj = new LogReader("src/main/resources/module/app.log");
        List<String> lines = readObj.readLogFile();
        LogParser parseObj = new LogParser(lines);
        List<LogEntry> tokens = parseObj.parseLines();
        LogAnalyzer analyzerObj = new LogAnalyzer(tokens);
        Map<String, Integer> countLogLevels = analyzerObj.countLogLevels();
        Map<String, Integer> countMessageFrequency = analyzerObj.countMessageFrequency();
        int totalEntries = tokens.size();
        LogReportGenerator reportGenerator = new LogReportGenerator(totalEntries, countLogLevels, countMessageFrequency);
        reportGenerator.writeReportToFile("src/main/resources/module/log-summary-report.txt");
    }
}

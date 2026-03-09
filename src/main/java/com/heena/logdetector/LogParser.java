package com.heena.logdetector;

import com.heena.logdetector.model.LogEntry;
import java.util.ArrayList;
import java.util.List;

public class LogParser {
    private List<String> lines;

    public LogParser(List<String>lines){
        this.lines = lines;
    }

    public List<LogEntry> parseLines(){
        List<LogEntry> entries = new ArrayList<>();
        if(lines.isEmpty()){
            System.out.println("No lines to parse");
            return entries;
        }

        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            String[] parts = line.split(" ", 4);
            if(parts.length < 4){
                continue;
            }
            String timestamp = parts[0] + " " + parts[1];
            String level = parts[2];
            String message = parts[3];
            LogEntry entry = new LogEntry(timestamp, level, message);
            entries.add(entry);
        }
        return entries;
    }
}

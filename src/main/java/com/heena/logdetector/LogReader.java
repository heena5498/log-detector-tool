package com.heena.logdetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    private String filepath;

    public LogReader(String filepath){
        this.filepath = filepath;
    }

    //Method to read Log file and convert it into list of Strings
    public List<String> readLogFile(){
        List<String> lines = new ArrayList<>();
        File file = new File(filepath);
        if(!file.exists()) {
            System.out.println("File does not exist");
            return lines;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        }
        catch(Exception e){
            System.out.println("Error occurred");
        }
        return lines;
    }
}

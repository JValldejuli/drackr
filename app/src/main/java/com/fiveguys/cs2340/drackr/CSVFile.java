package com.fiveguys.cs2340.drackr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class CSVFile {

    private final InputStream inputStream;

    public CSVFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List read() {
        List<String[]> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine = reader.readLine();
            while (csvLine != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
                csvLine = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing inputStream.");
        }
        return resultList;
    }

}

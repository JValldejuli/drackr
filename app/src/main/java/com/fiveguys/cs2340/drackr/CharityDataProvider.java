package com.fiveguys.cs2340.drackr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CharityDataProvider {

    private ArrayList<Charity> charities = new ArrayList<Charity>();

    public CharityDataProvider(InputStream inputStream) {

        CSVFile csvFile = new CSVFile(inputStream);
        List charityDataObjects = csvFile.read();
        charityDataObjects.remove(0);

        for (Object charityDataObject : charityDataObjects) {
            String[] charityData = (String[]) charityDataObject;

            CharityType type = CharityType.charityType(charityData[8]);

            URL url;
            try {
                url = new URL(charityData[10]);
            } catch(Exception e) {
                url = null;
            }

            Charity charity = new Charity(Integer.parseInt(charityData[0]),
                    charityData[1],
                    Double.parseDouble(charityData[2]),
                    Double.parseDouble(charityData[3]),
                    charityData[4],
                    charityData[5],
                    charityData[6],
                    Integer.parseInt(charityData[7]),
                    type,
                    charityData[9],
                    url);
            charities.add(charity);
        }

    }

    public ArrayList<Charity> getCharities() {
        return charities;
    }

}

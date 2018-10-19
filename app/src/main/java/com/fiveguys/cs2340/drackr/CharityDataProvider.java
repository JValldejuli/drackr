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

    private static ArrayList<Charity> charities = new ArrayList<Charity>();

    private static boolean setup = false;
    public static void setup(InputStream inputStream) {
        if (setup) {
            return;
        }
        setup = true;
        CSVFile csvFile = new CSVFile(inputStream);
        List charityDataObjects = csvFile.read();
        charityDataObjects.remove(0);

        for (Object charityDataObject : charityDataObjects) {
            String[] charityData = (String[]) charityDataObject;

            CharityType type = CharityType.charityType(charityData[8]);

            Charity charity = new Charity(charityData[0],
                    charityData[1],
                    Double.parseDouble(charityData[2]),
                    Double.parseDouble(charityData[3]),
                    charityData[4],
                    charityData[5],
                    charityData[6],
                    Integer.parseInt(charityData[7]),
                    type,
                    charityData[9],
                    charityData[10]);
            charities.add(charity);
        }
    }

    public static Charity selectedCharity = null;

    public static ArrayList<Charity> getCharities() {
        return charities;
    }

}

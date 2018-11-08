package com.fiveguys.cs2340.drackr;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class CharityDataProvider {

    private static final List<Charity> charities = new ArrayList<>();

    private static SharedPreferences preferences;
    private static InputStream inputStream;

    private static boolean loaded;

    public static void setup(SharedPreferences preferences, InputStream inputStream) {
        CharityDataProvider.preferences = preferences;
        CharityDataProvider.inputStream = inputStream;
        load();
    }

    private static void load() {

        if (loaded) {
            return;
        }
        loaded = true;

        Gson gson = new Gson();

        String charitiesJSON = preferences.getString("charities", "");
        if (!("[]".equals(charitiesJSON) || charitiesJSON.isEmpty())) {
            Iterable<LinkedTreeMap> linkedTreeMapList
                    = gson.fromJson(charitiesJSON, ArrayList.class);
            for (LinkedTreeMap charityTreeMap : linkedTreeMapList) {

                Iterable<LinkedTreeMap> linkedTreeMapDonationsList
                        = (ArrayList<LinkedTreeMap>) charityTreeMap.get("donations");
                ArrayList<Donation> donations = new ArrayList<>();

                for (LinkedTreeMap donationTreeMap : linkedTreeMapDonationsList) {
                    String dateString = (String) donationTreeMap.get("date");
                    Date date;
                    try {
                        date = java.text.DateFormat.getDateInstance().parse(dateString);
                    } catch(Exception e) {
                        throw new RuntimeException("Failed to parse date");
                    }
                    Donation donation = new Donation(
                            date,
                            (String) donationTreeMap.get("zipCode"),
                            (String) donationTreeMap.get("description"),
                            (Double) donationTreeMap.get("amount"),
                            DonationType.donationType((String) donationTreeMap.get("type"))
                    );
                    donations.add(donation);
                }

                Charity charity = new Charity(
                        (String) charityTreeMap.get("key"),
                        (String) charityTreeMap.get("name"),
                        (Double) charityTreeMap.get("latitude"),
                        (Double) charityTreeMap.get("longitude"),
                        (String) charityTreeMap.get("streetAddress"),
                        (String) charityTreeMap.get("city"),
                        (String) charityTreeMap.get("state"),
                        ((Double) charityTreeMap.get("zip")).intValue(),
                        CharityType.charityType((String) charityTreeMap.get("type")),
                        (String) charityTreeMap.get("phoneNumber"),
                        (String) charityTreeMap.get("url"),
                        donations
                );
                charities.add(charity);
            }
        } else {
            loadFromCSV();
        }

        save();

    }

    private static void loadFromCSV() {
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
                    charityData[10],
                    new ArrayList<Donation>());
            charities.add(charity);
        }
    }

    public static void save() {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String charitiesJSON = gson.toJson(charities);
        editor.putString("charities", charitiesJSON);
        editor.commit();
    }

    private static Charity selectedCharity;

    public static Charity getSelectedCharity() {
        return selectedCharity;
    }

    public static void setSelectedCharity(Charity charity) {
        selectedCharity = charity;
    }

    public static List<Charity> getCharities() {
        return Collections.unmodifiableList(charities);
    }

    public static void addDonationToSelectedCharity(Donation donation) {
        selectedCharity.addDonation(donation);
    }

    public static List<Donation> getSelectedCharityDonations() {
        return getSelectedCharity().getDonations();
    }

}

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

    public static final CharityDataProvider shared = new CharityDataProvider();

    private final List<Charity> charities = new ArrayList<>();

    private SharedPreferences preferences;
    private InputStream inputStream;

    private boolean loaded;

    public void setup(SharedPreferences preferences, InputStream inputStream) {
        this.preferences = preferences;
        this.inputStream = inputStream;
        load();
    }

    private void load() {

        if (loaded) {
            return;
        }
        loaded = true;

        Gson gson = new Gson();

        String charitiesJSON = preferences.getString("charities", "");
        if (!("[]".equals(charitiesJSON) || charitiesJSON.isEmpty())) {
            Iterable<LinkedTreeMap> linkedTreeMapList
                    = (Iterable<LinkedTreeMap>) gson.fromJson(charitiesJSON, ArrayList.class);
            for (LinkedTreeMap charityTreeMap : linkedTreeMapList) {

                Iterable<LinkedTreeMap> linkedTreeMapDonationsList
                        = (Iterable<LinkedTreeMap>) charityTreeMap.get("donations");
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
                        CharityType.charityType((String) charityTreeMap.get("type"), false),
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

    private void loadFromCSV() {
        CSVFile csvFile = new CSVFile(inputStream);
        List charityDataObjects = csvFile.read();
        charityDataObjects.remove(0);

        for (Object charityDataObject : charityDataObjects) {
            String[] charityData = (String[]) charityDataObject;

            CharityType type = CharityType.charityType(charityData[8], true);

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

    public void save() {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String charitiesJSON = gson.toJson(charities);
        editor.putString("charities", charitiesJSON);
        editor.apply();
    }

    private Charity selectedCharity;

    public Charity getSelectedCharity() {
        return selectedCharity;
    }

    public void setSelectedCharity(Charity charity) {
        selectedCharity = charity;
    }

    public List<Charity> getCharities() {
        return Collections.unmodifiableList(charities);
    }

    public void addDonationToSelectedCharity(Donation donation) {
        selectedCharity.addDonation(donation);
    }

    public List<Donation> getSelectedCharityDonations() {
        return selectedCharity.getDonations();
    }

}

package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DonationSearchCoordinator {

    private static final ArrayList<Donation> results = new ArrayList<>();

    private static Charity specificCharity;

    public static List<Donation> getResults() {
        return Collections.unmodifiableList(results);
    }

    public static void setSpecificCharity(Charity charity) {
        specificCharity = charity;
    }
    public static Charity getSpecificCharity() {
        return specificCharity;
    }

    public static void searchDonationsByType(DonationType donationType) {

        if (donationType == null) {
            return;
        }

        results.clear();

        if (specificCharity != null) {
            for (Donation donation : specificCharity.getDonations()) {
                if (donation.getType().equals(donationType)) {
                    results.add(donation);
                }
            }
        } else {
            for (Charity charity : CharityDataProvider.getCharities()) {
                for (Donation donation : charity.getDonations()) {
                    if (donation.getType().equals(donationType)) {
                        results.add(donation);
                    }
                }
            }
        }

    }

    public static void searchDonationsByDescription(CharSequence donationDescription) {

        if (donationDescription == null || donationDescription.length() == 0) {
            return;
        }

        results.clear();

        if (specificCharity != null) {
            for (Donation donation : specificCharity.getDonations()) {
                if (donation.getDescription().contains(donationDescription)) {
                    results.add(donation);
                }
            }
        } else {
            for (Charity charity : CharityDataProvider.getCharities()) {
                for (Donation donation : charity.getDonations()) {
                    if (donation.getDescription().contains(donationDescription)) {
                        results.add(donation);
                    }
                }
            }
        }

    }

}

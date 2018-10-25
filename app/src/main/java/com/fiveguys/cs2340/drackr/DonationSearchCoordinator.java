package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;

public class DonationSearchCoordinator {

    public static ArrayList<Donation> results;

    public static void searchDonationsByType(DonationType donationType, Charity specificCharity) {

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

    public static void searchDonationsByDescription(String donationDescription, Charity specificCharity) {

        results.clear();

        if (specificCharity != null) {
            for (Donation donation : specificCharity.getDonations()) {
                if (donation.getDescription().equals(donationDescription)) {
                    results.add(donation);
                }
            }
        } else {
            for (Charity charity : CharityDataProvider.getCharities()) {
                for (Donation donation : charity.getDonations()) {
                    if (donation.getDescription().equals(donationDescription)) {
                        results.add(donation);
                    }
                }
            }
        }

    }

}

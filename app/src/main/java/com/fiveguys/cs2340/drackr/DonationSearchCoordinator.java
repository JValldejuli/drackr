package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;

public class DonationSearchCoordinator {

    public static ArrayList<Donation> results = new ArrayList<Donation>();

    public static Charity specificCharity;

    public static void searchDonationsByType(DonationType donationType) {

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

    public static void searchDonationsByDescription(String donationDescription) {

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

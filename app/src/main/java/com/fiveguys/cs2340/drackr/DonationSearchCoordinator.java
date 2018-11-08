package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DonationSearchCoordinator {

    public static final DonationSearchCoordinator shared = new DonationSearchCoordinator();

    private final List<Donation> results = new ArrayList<>();

    private Charity specificCharity;

    public List<Donation> getResults() {
        return Collections.unmodifiableList(results);
    }

    public void setSpecificCharity(Charity charity) {
        specificCharity = charity;
    }
    public Charity getSpecificCharity() {
        return specificCharity;
    }

    public void searchDonationsByType(DonationType donationType) {

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
            for (Charity charity : CharityDataProvider.shared.getCharities()) {
                for (Donation donation : charity.getDonations()) {
                    if (donation.getType().equals(donationType)) {
                        results.add(donation);
                    }
                }
            }
        }

    }

    public void searchDonationsByDescription(CharSequence donationDescription) {

        if ((donationDescription == null) || (donationDescription.length() == 0)) {
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
            for (Charity charity : CharityDataProvider.shared.getCharities()) {
                for (Donation donation : charity.getDonations()) {
                    if (donation.getDescription().contains(donationDescription)) {
                        results.add(donation);
                    }
                }
            }
        }

    }

}

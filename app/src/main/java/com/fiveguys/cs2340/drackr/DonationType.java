package com.fiveguys.cs2340.drackr;

public enum DonationType {
    CLOTHING, HAT, KITCHEN, ELECTRONICS,
    HOUSEHOLD, OTHER;

    @Override
    public String toString() {
        switch (this) {
            case CLOTHING:
                return "Clothing";
            case HAT:
                return "Hat";
            case KITCHEN:
                return "Kitchen";
            case ELECTRONICS:
                return "Electronics";
            case HOUSEHOLD:
                return "Household";
            case OTHER:
                return "Other";
            default:
                return "wat";
        }
    }

    public static DonationType donationType(String string) {
        switch (string) {
            case "CLOTHING":
                return CLOTHING;
            case "HAT":
                return HAT;
            case "KITCHEN":
                return KITCHEN;
            case "ELECTRONICS":
                return ELECTRONICS;
            case "HOUSEHOLD":
                return HOUSEHOLD;
            case "OTHER":
                return OTHER;
            default:
                return OTHER;
        }
    }


}

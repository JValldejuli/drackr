package com.fiveguys.cs2340.drackr;

/**
 * The set of possible donation types.
 */
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

    /**
     * Used to convert a string into a DonationType.
     * @param string The string to convert.
     * @return The resulting DonationType.
     */
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
                throw new RuntimeException("Invalid string passed to donationType");
        }
    }


}

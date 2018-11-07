package com.fiveguys.cs2340.drackr;

/**
 * The set of possible charity types.
 */
public enum CharityType {

    DROP_OFF, STORE, WAREHOUSE;

    @Override
    public String toString() {
        switch (this) {
            case DROP_OFF:
                return "Drop Off";
            case STORE:
                return "Store";
            case WAREHOUSE:
                return "Warehouse";
            default:
                return "whatever";
        }
    }

    /**
     * Gives a description of the CharityType.
     * @return Description of the CharityType.
     */
    public String description() {
        return toString();
    }

    /**
     * Used to convert a string to a CharityType.
     * @param string The string to convert.
     * @return The CharityType.
     */
    public static CharityType charityType(String string) {
        switch (string) {
            case "Drop Off":
                return DROP_OFF;
            case "Store":
                return STORE;
            case "Warehouse":
                return WAREHOUSE;
            default:
                return WAREHOUSE;
        }
    }

}

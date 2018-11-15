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
     * @param csv Whether or not the incoming string is from the provided CSV.
     * @return The CharityType.
     */
    public static CharityType charityType(String string, boolean csv) {
        if (csv) {
            switch (string) {
                case "Drop Off":
                    return DROP_OFF;
                case "Store":
                    return STORE;
                case "Warehouse":
                    return WAREHOUSE;
                default:
                    throw new RuntimeException("Invalid string passed to charityType - CSV");
            }
        } else {
            switch (string) {
                case "DROP_OFF":
                    return DROP_OFF;
                case "STORE":
                    return STORE;
                case "WAREHOUSE":
                    return WAREHOUSE;
                default:
                    throw new RuntimeException("Invalid string passed to charityType - NOT CSV");
            }
        }
    }

}

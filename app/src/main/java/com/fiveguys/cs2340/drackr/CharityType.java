package com.fiveguys.cs2340.drackr;

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

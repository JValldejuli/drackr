package com.fiveguys.cs2340.drackr;

/**
 * The types of accounts a user could have.
 */
public enum UserAccountType {

    USER, LOCATION_EMPLOYEE, ADMIN;

    @Override
    public String toString() {
        switch (this) {
            case USER:
                return "User";
            case LOCATION_EMPLOYEE:
                return "Location Employee";
            case ADMIN:
                return "Admin";
            default:
                return "whatever";
        }
    }

    /**
     * Used to convert a string to a UserAccountType.
     * @param string The string to convert.
     * @return The UserAccountType.
     */
    public static UserAccountType userAccountType(String string) {
        switch (string) {
            case "User":
                return USER;
            case "Location Employee":
                return LOCATION_EMPLOYEE;
            case "Admin":
                return ADMIN;
            default:
                return USER;
        }
    }
}

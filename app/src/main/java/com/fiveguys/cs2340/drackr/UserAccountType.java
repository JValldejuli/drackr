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
            case "USER":
                return USER;
            case "LOCATION_EMPLOYEE":
                return LOCATION_EMPLOYEE;
            case "ADMIN":
                return ADMIN;
            default:
                throw new RuntimeException("Invalid string passed to userAccountType");
        }
    }
}

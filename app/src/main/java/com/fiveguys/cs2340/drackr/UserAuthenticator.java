package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;

public class UserAuthenticator {

    private static ArrayList<UserAccount> accounts;

    private static UserAccount signedInUserAccount;

    public static void registerUserAccount(String name, String email, String password) {
        UserAccount newAccount = new UserAccount(name, email, password);
        accounts.add(newAccount);
        signedInUserAccount = newAccount;
    }

    public static UserAccount signInWith(String email, final String password) {
        for (UserAccount potential : accounts) {
            boolean foundAccount = potential.getEmail().equals(email) && potential.getPassword().equals(password);
            if (foundAccount) {
                signedInUserAccount = potential;
                return potential;
            }
        }
        return null;
    }

}

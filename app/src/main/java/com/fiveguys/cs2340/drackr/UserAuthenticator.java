package com.fiveguys.cs2340.drackr;

import java.util.ArrayList;

public class UserAuthenticator {

    private static ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();

    private static UserAccount signedInUserAccount = null;

    public static void registerUserAccount(String name, String email, String password) {
        UserAccount newAccount = new UserAccount(name, email, password);
        accounts.add(newAccount);
        signedInUserAccount = newAccount;
    }

    public static Boolean signInWith(String email, final String password) {
        for (UserAccount potential : accounts) {
            boolean foundAccount = potential.getEmail().equals(email) && potential.getPassword().equals(password);
            if (foundAccount) {
                signedInUserAccount = potential;
                return true;
            }
        }
        return false;
    }

}

package com.fiveguys.cs2340.drackr;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class UserAuthenticator {

    private static ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();;

    private static UserAccount signedInUserAccount = null;

    private static Boolean loaded = false;

    private static SharedPreferences preferences;

    public static void initializeWith(SharedPreferences preferences) {
        UserAuthenticator.preferences = preferences;
        load();
    }

    private static void load() {

        if (loaded) {
            return;
        }
        loaded = true;

        Gson gson = new Gson();

        String accountsJSON = preferences.getString("accounts", "");
        ArrayList<LinkedTreeMap> linkedTreeMapList = gson.fromJson(accountsJSON, ArrayList.class);
        for (LinkedTreeMap accountTreeMap : linkedTreeMapList) {
            UserAccount account = new UserAccount(
                    (String) accountTreeMap.get("name"),
                    (String) accountTreeMap.get("email"),
                    (String) accountTreeMap.get("password")
            );
            accounts.add(account);
        }

    }

    private static void save() {

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String accountsJSON = gson.toJson(accounts);
        editor.putString("accounts", accountsJSON);
        editor.commit();

    }

    public static void registerUserAccount(String name, String email, String password) {
        UserAccount newAccount = new UserAccount(name, email, password);
        accounts.add(newAccount);
        signedInUserAccount = newAccount;
        save();
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

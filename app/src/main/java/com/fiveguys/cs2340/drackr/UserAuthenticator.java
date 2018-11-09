package com.fiveguys.cs2340.drackr;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collection;

class UserAuthenticator {

    private final Collection<UserAccount> accounts = new ArrayList<>();

    private UserAccount signedInUserAccount;

    private Boolean loaded = false;

    private SharedPreferences preferences;

    public void initializeWith(SharedPreferences preferences) {
        UserAuthenticator.shared.preferences = preferences;
        load();
    }

    public static final UserAuthenticator shared = new UserAuthenticator();

    private void load() {

        if (loaded) {
            return;
        }
        loaded = true;

        Gson gson = new Gson();

        String accountsJSON = preferences.getString("accounts", "");
        if (!("[]".equals(accountsJSON) || accountsJSON.isEmpty())) {
            Iterable<LinkedTreeMap> linkedTreeMapList
                    = gson.fromJson(accountsJSON, ArrayList.class);
            for (LinkedTreeMap accountTreeMap : linkedTreeMapList) {
                String userAccountTypeString = (String) accountTreeMap.get("type");
                UserAccountType userAccountType = UserAccountType.userAccountType(userAccountTypeString);
                UserAccount account = new UserAccount(
                        (String) accountTreeMap.get("name"),
                        (String) accountTreeMap.get("email"),
                        (String) accountTreeMap.get("password"),
                        userAccountType
                );
                accounts.add(account);
            }
        }

    }

    private void save() {

        if (preferences == null) {
            return;
        }

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String accountsJSON = gson.toJson(accounts);
        editor.putString("accounts", accountsJSON);
        editor.apply();

    }

    public void registerUserAccount(String name, String email, String password, UserAccountType type) {
        boolean validName = (name != null) && !name.isEmpty();
        boolean validEmail = (email != null) && !email.isEmpty();
        boolean validPassword = (password != null) && !password.isEmpty();
        if (!validName || !validEmail || !validPassword) {
            return;
        }
        UserAccount newAccount = new UserAccount(name, email, password, type);
        accounts.add(newAccount);
        signedInUserAccount = newAccount;
        save();
    }

    public Boolean signInWith(String email, final String password) {
        boolean validEmail = (email != null) && !email.isEmpty();
        boolean validPassword = (password != null) && !password.isEmpty();
        if (!validEmail || !validPassword) {
            return false;
        }
        for (UserAccount potential : accounts) {
            boolean foundAccount
                    = potential.getEmail().equals(email)
                    && potential.getPassword().equals(password);
            if (foundAccount) {
                signedInUserAccount = potential;
                return true;
            }
        }
        return false;
    }

    public UserAccount getSignedInUserAccount() {
        return signedInUserAccount;
    }

}

package com.fiveguys.cs2340.drackr;

class UserAccount {

    // MARK: Instance variables
    private String name;
    private String email;
    private String password;
    private UserAccountType type;

    public UserAccount(String name, String email, String password, UserAccountType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    // MARK: Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccountType getType() { return type; }
    public void setType(UserAccountType type) { this.type = type; }

}

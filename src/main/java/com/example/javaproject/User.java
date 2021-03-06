package com.example.javaproject;

public class User {
    private int id;
    private String username;
    private String password;
    private String accountType;

    public User() {
    }

    public User(int id, String username, String password, String accountType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

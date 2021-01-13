package com.example.tictactoe;

import java.io.Serializable;

/**
 * This class presents a user.
 */
public class User implements Serializable {

    private String username; // The userName.
    private String password; // The password.

    private UserData data;

    public User(String username, String password, UserData data) {
        this.username = username;
        this.password = password;
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
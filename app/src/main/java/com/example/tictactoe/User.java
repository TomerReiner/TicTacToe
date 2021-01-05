package com.example.tictactoe;

import java.io.Serializable;

/**
 * This class presents a user.
 */
public class User implements Serializable {

    private String userName; // The userName.
    private String password; // The password.

    private UserData data;

    public User(String userName, String password, UserData data) {
        this.userName = userName;
        this.password = password;
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
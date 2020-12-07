package com.example.tictactoe;

/**
 * This class presents a user.
 */
public class User {

    private String userName; // The userName.
    private String password; // The password.

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}

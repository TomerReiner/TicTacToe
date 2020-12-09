package com.example.tictactoe;

import java.io.Serializable;

/**
 * This class presents a user.
 */
public class User implements Serializable {

    private String userName; // The userName.
    private String password; // The password.

    private int numOfPlayerVsPlayerGames; // Number of player vs player games the user has played.
    private int numOfPlayerVsComputerGames; // Number of player vs computer game the player ahs played.
    private int totalNumberOfGames; // Total number of games.
    private int numOfVictories; // Total number of victories.
    private int nunOfDefeats; // Total number of defeats.
    private int numOfTies; // Total number of tries.
    private int numOfEasyModeGame; // Total number of easy mode games in player vs computer mode.
    private int numOfMediumModeGames; // Total number of medium mode games player vs computer mode.
    private int numOfHardModeGames; // Total number of hard mode games player vs computer mode.


    public User(String userName, String password, int numOfPlayerVsPlayerGames, int numOfPlayerVsComputerGames, int numOfVictories, int nunOfDefeats, int numOfTies, int numOfEasyModeGame, int numOfMediumModeGames, int numOfHardModeGames) {
        this.userName = userName;
        this.password = password;

        this.numOfPlayerVsPlayerGames = numOfPlayerVsPlayerGames;
        this.numOfPlayerVsComputerGames = numOfPlayerVsComputerGames;
        this.totalNumberOfGames = this.numOfPlayerVsComputerGames + this.numOfPlayerVsPlayerGames;
        this.numOfVictories = numOfVictories;
        this.nunOfDefeats = nunOfDefeats;
        this.numOfTies = numOfTies;
        this.numOfEasyModeGame = numOfEasyModeGame;
        this.numOfMediumModeGames = numOfMediumModeGames;
        this.numOfHardModeGames = numOfHardModeGames;
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

    public int getNumOfPlayerVsPlayerGames() {
        return numOfPlayerVsPlayerGames;
    }

    public void setNumOfPlayerVsPlayerGames(int numOfPlayerVsPlayerGames) {
        this.numOfPlayerVsPlayerGames = numOfPlayerVsPlayerGames;
    }

    public int getNumOfPlayerVsComputerGames() {
        return numOfPlayerVsComputerGames;
    }

    public void setNumOfPlayerVsComputerGames(int numberOfPlayerVsComputerGames) {
        this.numOfPlayerVsComputerGames = numberOfPlayerVsComputerGames;
    }

    public int getTotalNumberOfGames() {
        return totalNumberOfGames;
    }

    public void setTotalNumberOfGames(int totalNumbersOfGames) {
        this.totalNumberOfGames = totalNumbersOfGames;
    }

    public int getNumOfVictories() {
        return numOfVictories;
    }

    public void setNumOfVictories(int numOfVictories) {
        this.numOfVictories = numOfVictories;
    }

    public int getNunOfDefeats() {
        return nunOfDefeats;
    }

    public void setNunOfDefeats(int nunOfDefeats) {
        this.nunOfDefeats = nunOfDefeats;
    }

    public int getNumOfTies() {
        return numOfTies;
    }

    public void setNumOfTies(int numOfTies) {
        this.numOfTies = numOfTies;
    }

    public int getNumOfEasyModeGame() {
        return numOfEasyModeGame;
    }

    public void setNumOfEasyModeGame(int numOfEasyModeGame) {
        this.numOfEasyModeGame = numOfEasyModeGame;
    }

    public int getNumOfMediumModeGames() {
        return numOfMediumModeGames;
    }

    public void setNumOfMediumModeGames(int numOfMediumModeGames) {
        this.numOfMediumModeGames = numOfMediumModeGames;
    }

    public int getNumOfHardModeGames() {
        return numOfHardModeGames;
    }

    public void setNumOfHardModeGames(int numOfHardModeGames) {
        this.numOfHardModeGames = numOfHardModeGames;
    }
}
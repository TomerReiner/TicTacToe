package com.example.tictactoe;

public class UserData {

    private int totalNumberOfGames; // Total number of games.
    private int numOfVictories; // Total number of victories.
    private int nunOfDefeats; // Total number of defeats.
    private int numOfTies; // Total number of tries.
    private double percentageOfWins = 0; // Percentage of wins.

    public UserData(int numOfVictories, int nunOfDefeats, int numOfTies) {
        this.numOfVictories = numOfVictories;
        this.nunOfDefeats = nunOfDefeats;
        this.numOfTies = numOfTies;
        this.totalNumberOfGames = this.numOfVictories + this.numOfTies + this.nunOfDefeats;
        this.percentageOfWins = (double) this.numOfVictories / this.totalNumberOfGames;
    }

    public int getTotalNumberOfGames() {
        return totalNumberOfGames;
    }

    public int getNumOfVictories() {
        return numOfVictories;
    }

    public void setNumOfVictories(int numOfVictories) {
        this.totalNumberOfGames = this.numOfVictories + this.numOfTies + this.nunOfDefeats;
        this.percentageOfWins = (double) this.numOfVictories / this.totalNumberOfGames;
        this.numOfVictories = numOfVictories;
    }

    public int getNunOfDefeats() {
        return nunOfDefeats;
    }

    public void setNunOfDefeats(int nunOfDefeats) {
        this.totalNumberOfGames = this.numOfVictories + this.numOfTies + this.nunOfDefeats;
        this.percentageOfWins = (double) this.numOfVictories / this.totalNumberOfGames;
        this.nunOfDefeats = nunOfDefeats;
    }

    public int getNumOfTies() {
        return numOfTies;
    }

    public void setNumOfTies(int numOfTies) {
        this.totalNumberOfGames = this.numOfVictories + this.numOfTies + this.nunOfDefeats;
        this.percentageOfWins = (double) this.numOfVictories / this.totalNumberOfGames;
        this.numOfTies = numOfTies;
    }
}

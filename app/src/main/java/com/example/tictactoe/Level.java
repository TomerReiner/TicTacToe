package com.example.tictactoe;

import java.util.ArrayList;

public class Level {

    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;
    public static final int LEVEL_THREE = 3; // Levels.

    protected Game game;

    public Level(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    /**
     * This function gets all the available spots in the game board.
     * @return {@link ArrayList} of int[].
     * Each array will contain 2 items.
     * The first item is the row index of the available spot.
     * The second item is the column index of the available spot.
     */
    protected ArrayList<int[]> getAvailableSpots() {
        ArrayList<int[]> availableSpots = new ArrayList<int[]>();
        String[][] board = this.game.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(""))
                    availableSpots.add(new int[]{i, j});
            }
        }
        return availableSpots;
    }
}
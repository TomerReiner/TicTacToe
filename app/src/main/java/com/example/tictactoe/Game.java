package com.example.tictactoe;

public class Game {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;
    public static final int GAME_IS_STILL_GOING_ON = Integer.MIN_VALUE;
    public static final int TIE = 0;
    public static final int CIRCLE_WON = -10;
    public static final int X_WON = 10;
    public static final String CIRCLE_TURN = "Circle's Turn";
    public static final String X_TURN = "X's Turn";
    public static final String CIRCLE_WON_GREET = "Circle Won!!!!!!";
    public static final String X_WON_GREET = "X Won!!!!!!";
    public static final String TIE_GREET = "Tie.";
    public static final String CIRCLE = "O";
    public static final String X = "X";
//TODO-add check game over and more methods
    private String[][] board;

    public Game() {
        this.board = new String[WIDTH][HEIGHT];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                this.board[i][j] = ""; // Init the array.
        }
    }

    /**
     * This function sets the value of board[i][j] to s.
     * @param s The value.
     * @param i Row index.
     * @param j Column index.
     */
    public void setItemAt(String s, int i, int j) {
        this.board[i][j] = s;
    }

    public String[][] getBoard() {
        return this.board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    /**
     * This function Checks if one of the users won.
     * @return {@link #X_WON} if X won,
     * {@link #CIRCLE_WON} if circle won,
     * {@link #TIE} if there is a tie,
     * and {@link #GAME_IS_STILL_GOING_ON} if the game is still going on.
     */
    public int checkForWin() {
        if ((this.board[0][0].equals("X") && this.board[0][1].equals("X") && this.board[0][2].equals("X")) || // If X took the first row.
                (this.board[1][0].equals("X") && this.board[1][1].equals("X") && this.board[1][2].equals("X")) || // If X took the second row.
                (this.board[2][0].equals("X") && this.board[2][1].equals("X") && this.board[2][2].equals("X")) || // If X took the third  row.
                (this.board[0][0].equals("X") && this.board[1][0].equals("X") && this.board[2][0].equals("X")) || // If X took the first column.
                (this.board[0][1].equals("X") && this.board[1][1].equals("X") && this.board[2][1].equals("X")) || // If X took the second column.
                (this.board[0][2].equals("X") && this.board[1][2].equals("X") && this.board[2][2].equals("X")) || // If X took the third column.
                (this.board[0][0].equals("X") && this.board[1][1].equals("X") && this.board[2][2].equals("X")) || // If X took the left diagonal.
                (this.board[0][2].equals("X") && this.board[1][1].equals("X") && this.board[2][0].equals("X"))) // If X took the right diagonal.
            return X_WON;

        else if ((this.board[0][0].equals("O") && this.board[0][1].equals("O") && this.board[0][2].equals("O")) || // If Circle took the first row.
                (this.board[1][0].equals("O") && this.board[1][1].equals("O") && this.board[1][2].equals("O")) || // If Circle took the second row.
                (this.board[2][0].equals("O") && this.board[2][1].equals("O") && this.board[2][2].equals("O")) || // If Circle took the third  row.
                (this.board[0][0].equals("O") && this.board[1][0].equals("O") && this.board[2][0].equals("O")) || // If Circle took the first column.
                (this.board[0][1].equals("O") && this.board[1][1].equals("O") && this.board[2][1].equals("O")) || // If Circle took the second column.
                (this.board[0][2].equals("O") && this.board[1][2].equals("O") && this.board[2][2].equals("O")) || // If Circle took the third column.
                (this.board[0][0].equals("O") && this.board[1][1].equals("O") && this.board[2][2].equals("O")) || // If Circle took the left diagonal.
                (this.board[0][2].equals("O") && this.board[1][1].equals("O") && this.board[2][0].equals("O"))) // If Circle took the right diagonal.)
            return CIRCLE_WON;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (this.board[i][j].equals(""))
                    return GAME_IS_STILL_GOING_ON; // If one cell is empty and no one one it meas the game is still going on.
            }
        }
        return TIE; // Tie.
    }

    /**
     * This function checks the game status and returns a matching greet if it was ended.
     * @return {@link #CIRCLE_WON_GREET} if Circle won,
     * {@link #X_WON_GREET} if X won,
     * and {@link #TIE_GREET} if there was a tie.
     */
    public String getGameOverGreet() {
        int victoryStatus = this.checkForWin();

        if (victoryStatus == CIRCLE_WON)
            return CIRCLE_WON_GREET;
        return victoryStatus == X_WON ? X_WON_GREET : TIE_GREET;

    }

    /**
     * @return The integer the char represents.
     */
    public int charToInt(char c) {
        if (c == '0')
            return 0;
        if (c == '1')
            return 1;
        return 2;
    }

    /**
     * This function returns the id of the item based on the location of it.
     * @param i row index.
     * @param j column index.
     * @return The match id based on i and j.
     * @example getId(1, 2) => R.id.btn12
     */
    public int getId(int i, int j) {
        switch (i) {
            case 0:
                switch (j) {
                    case 0:
                        return R.id.btn00;
                    case 1:
                        return R.id.btn01;
                    default:
                        return R.id.btn02;
                }
            case 1:
                switch (j) {
                    case 0:
                        return R.id.btn10;
                    case 1:
                        return R.id.btn11;
                    default:
                        return R.id.btn12;
                }
            default:
                switch (j) {
                    case 0:
                        return R.id.btn20;
                    case 1:
                        return R.id.btn21;
                    default:
                        return R.id.btn22;
                }
        }
    }

    /**
     * This function checks if X or Circle won the game
     * @return {@link #CIRCLE_WON} if circle won, {@link #X_WON} if X won, and {@link #TIE} if there is a tie, otherwise Integer.MIN_VALUE.
     */
    public int checkGameOver() {
        return this.checkForWin();
    }
}

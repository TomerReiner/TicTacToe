package com.example.tictactoe;

public class LevelThreeHandler extends Level{

    public LevelThreeHandler(Game game) {
        super(game);
    }

    public int evaluate() {
        return this.game.checkForWin();
    }

    private int miniMax(int depth, boolean isMax) {
        int win = this.evaluate();

        if (win != Game.GAME_IS_STILL_GOING_ON)
            return win;

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < Game.WIDTH; i++) {
                for (int j = 0; j < Game.HEIGHT; j++) {
                    if (this.game.getBoard()[i][j].equals("")) {
                        this.game.setItemAt(Game.X, i, j);
                        best = Math.max(best, miniMax(depth + 1, !isMax) - depth - 1);
                        this.game.setItemAt("", i, j);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < Game.WIDTH; i++) {
                for (int j = 0; j < Game.HEIGHT; j++) {
                    if (this.game.getBoard()[i][j].equals("")) {
                        this.game.setItemAt(Game.CIRCLE, i, j);
                        best = Math.min(best, miniMax(depth + 1, !isMax) + depth + 1);
                        this.game.setItemAt("", i, j);
                    }
                }
            }
            return best;
        }
    }


    /**
     * This function finds the best move for the computer.
     * @return array of two items. array[0] contains the row index of the best move,
     * array[1] contains the column index of the best move.
     */
    public int[] findBestMove() {
        int[] bestMove = {-1, -1};
        int bestVal = -1000;

        for (int i = 0; i < Game.WIDTH; i++) {
            for (int j = 0; j < Game.HEIGHT; j++) {
                if (this.game.getBoard()[i][j].equals("")) {
                    this.game.setItemAt(Game.X, i, j);

                    int moveVal = miniMax(0, false);
                    this.game.setItemAt("", i, j);
                    if (moveVal > bestVal) {
                        bestMove = new int[] {i , j};
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}
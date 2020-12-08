package com.example.tictactoe;

public class LevelTwoHandler extends Level {

    /**
     * Count is used for deciding which move to do.
     * When count is even, a random movement will be selected.
     * When count is odd, a minimax algorithm movement will be selected.
     */

    private int count = 0;

    public LevelTwoHandler(Game game) {
        super(game);
        this.count = 0;
    }


    /**
     * This function select a movement based on {@link #count}
     * @return {@link int[]} of  items.
     * The first item is the row index of the available spot.
     * The second item is the column index of the available spot.
     */
    public int[] selectMovement() {
        LevelOneHandler levelOneHandler = new LevelOneHandler(this.game);
        LevelThreeHandler levelThreeHandler = new LevelThreeHandler(this.game);

        boolean isCountOdd = count % 2 == 0;
        count++;

        if (isCountOdd) // If count is odd.
            return levelOneHandler.getRandomLocation(); // Random location.
        return levelThreeHandler.findBestMove(); // Minimax Algorithm.
    }
}
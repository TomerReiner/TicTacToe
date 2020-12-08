package com.example.tictactoe;

import java.util.ArrayList;

public class LevelOneHandler extends Level{

    public LevelOneHandler(Game game) {
        super(game);
    }

    /**
     * This function selects random movement for the computer's move.
     * @return {@link int[]} of 2 items(index for the random movement).
     * The first item is the row index of the random available spot.
     * The second item is the column index of the random available spot.
     */
    public int[] getRandomLocation() {
        ArrayList<int[]> availableSpots = super.getAvailableSpots(); // Available spots.
        int randomIndex = (int) (Math.random() * availableSpots.size()); // Random index for the computer's movement index.
        return availableSpots.get(randomIndex);
    }
}
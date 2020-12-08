package com.example.tictactoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// TODO-intent data
public class PlayerVsComputerActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mainLayoutPlayerVsComputer; // The main layout.

    private TextView tvGameStatus; // This text view will be used to show who's turn is and who won.

    private AlertDialog selectLevelDialog;

    private boolean xWon; // With those booleans we will detect if one of the user won.
    private boolean isPlayerAgainstPlayer; // true: Player vs Player false: Player vs Computer. When the mode is Player vs Computer, the Computer will be Circle.
    private boolean isGameOver; // true: the game is over. false: the game is still going on.
    private boolean xTurn; // X begins first. If true, it's X's turn. If false, it's Circle's turn.

    private int victoryStatus = Game.TIE; // Game status.
    private int level = 0; // This is the level.

    public static final int REQUEST = 1;
    private Game game; // Game manager.

    private LevelOneHandler levelOneHandler;
    private LevelTwoHandler levelTwoHandler;
    private LevelThreeHandler levelThreeHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_computer);

        game = new Game();

        levelOneHandler = new LevelOneHandler(game);
        levelTwoHandler = new LevelTwoHandler(game);
        levelThreeHandler = new LevelThreeHandler(game);

        xWon = false;
        isPlayerAgainstPlayer = true;
        isGameOver = false;
        xTurn = false;
        victoryStatus = Game.TIE;
        // Initialize important game variables.

        mainLayoutPlayerVsComputer = findViewById(R.id.mainLayoutPlayerVsComputer);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;

        for (int i = 0; i < Game.WIDTH; i++) {
            LinearLayout newLayout = new LinearLayout(this);
            newLayout.setOrientation(LinearLayout.HORIZONTAL);
            newLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < Game.HEIGHT; j++) {
                Button btn = new Button(this);
                btn.setText("");
                btn.setLayoutParams(new LinearLayout.LayoutParams(width / 3, width / 3));
                btn.setTextSize(40);
                btn.setTag(i + " " + j); // The tag of each element will be: row column.
                btn.setId(game.getId(i, j)); // Set id for the button.
                game.setItemAt("", i, j);
                btn.setOnClickListener(this);

                newLayout.addView(btn);
            }
            mainLayoutPlayerVsComputer.addView(newLayout);
        }
        createStatusLayout(); // Create the status layout.
        createSelectLevelDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        selectLevelDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

    /**
     * This function creates the status layout.
     * The layout has one text view, which shows the game status.
     */
    private void createStatusLayout() {
        LinearLayout layoutStatus = new LinearLayout(this);
        layoutStatus.setOrientation(LinearLayout.HORIZONTAL);
        layoutStatus.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        // Initialize the layout.

        tvGameStatus = new TextView(this);
        tvGameStatus.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tvGameStatus.setTextSize(40);
        tvGameStatus.setAllCaps(false);
        tvGameStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvGameStatus.setTextColor(Color.BLUE);
        tvGameStatus.setText(Game.CIRCLE_TURN);
        // Initialize the TextView.

        layoutStatus.addView(tvGameStatus);
        mainLayoutPlayerVsComputer.addView(layoutStatus);
        // Add views to the layouts.
    }

    private void createSelectLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setTitle("Please Select a level");

        builder.setPositiveButton("Easy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                level = Level.LEVEL_ONE; // Set the level to level1.
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Medium", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                level = Level.LEVEL_TWO; // Set the level to level1.
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                level = Level.LEVEL_THREE; // Set the level to level1.
                dialog.dismiss();
            }
        });
        selectLevelDialog = builder.create();
        selectLevelDialog.show();
    } // TODO-create custom layout?

    /**
     * This function sets the button background to be x or circle and manages the game.
     */
    private void manageGame(Button btn) {
        victoryStatus = checkGameOver();
        handleGameOver();

        if (!xTurn) { // If it's Circle's turn.
            if (btn.getText().toString().equals("")) { // If the button has not pressed yet.
                btn.setText(Game.CIRCLE);
                xTurn = true; // X's turn.
                String btnTag = btn.getTag().toString(); // The tag will be as my explanation: row_index space column index. This means that row_index = btnTag[0] and column_index = btnTag[2].
                game.setItemAt(Game.CIRCLE, game.charToInt(btnTag.charAt(0)), game.charToInt(btnTag.charAt(2))); // X took the match index.
                tvGameStatus.setText(Game.X_TURN);
                btn.setClickable(false);
                victoryStatus = checkGameOver();
                handleGameOver();
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ai's turn.
                if (!isGameOver) {
                    int[] locationOfComputersChoice = getMove(); // Get the location of the computer's choice.
                    xTurn = false;
                    if ((locationOfComputersChoice[0] >= 0 && locationOfComputersChoice[0] < Game.WIDTH) && (locationOfComputersChoice[1] >= 0 && locationOfComputersChoice[1] < Game.HEIGHT)) { // This is prevents ArrayIndexOutOfBoundsException which happens at the end of the game.
                        game.setItemAt(Game.X, locationOfComputersChoice[0], locationOfComputersChoice[1]);
                        levelOneHandler = new LevelOneHandler(game);
                        levelTwoHandler = new LevelTwoHandler(game);
                        levelThreeHandler = new LevelThreeHandler(game);
                        Button btnAiChoice = findViewById(game.getId(locationOfComputersChoice[0], locationOfComputersChoice[1])); // Get the id.
                        btnAiChoice.setText(Game.X);
                        btnAiChoice.setClickable(false);
                        tvGameStatus.setText(Game.CIRCLE_TURN);
                        handleGameOver();
                        return;
                    }
                }
            }
        }, 700); // We delay the AI's turn to make game look more realistic.
    }

    /**
     * This function checks if X or Circle won the game
     * @return Game.CIRCLE_WON if circle won, Game.X_WON if X won, and Game.TIE if there is a tie, otherwise Integer.MIN_VALUE.
     */
    private int checkGameOver() {
        victoryStatus = game.checkForWin();
        if (victoryStatus != Game.GAME_IS_STILL_GOING_ON) // If the game is over.
            isGameOver = true;
        return victoryStatus;
    }

    /**
     * This function cancels the click option for all the buttons.
     */
    private void cancelButtonClick() {
        if (isGameOver) {
            for (int i = 0; i < Game.WIDTH; i++) {
                for (int j = 0; j < Game.HEIGHT; j++) {
                    Button btn = findViewById(game.getId(i, j));
                    btn.setClickable(false);
                }
            }
        }
    }

    /**
     * This function selects random movement for the computer's move.
     * @return {@link int[]} of 2 items(index for the random movement).
     * The first item is the row index of the random available spot.
     * The second item is the column index of the random available spot.
     */
    private int[] getMove() {
        levelOneHandler = new LevelOneHandler(game);
        levelTwoHandler = new LevelTwoHandler(game);
        levelThreeHandler = new LevelThreeHandler(game);
        if (level == Level.LEVEL_ONE)
            return levelOneHandler.getRandomLocation();
        if (level == Level.LEVEL_TWO)
            return levelTwoHandler.selectMovement();
        return levelThreeHandler.findBestMove();
    }


    /**
     * This function checks if the game is over.
     * If so, it will call {@link #onGameOver}
     */
    private void handleGameOver() {
        victoryStatus = checkGameOver();
        if (isGameOver) {
            onGameOver();
            return;
        }
    }


    /**
     * This function is called When the game is over,
     * and moves the user to {@link GameOverActivity}.
     */
    private void onGameOver() {
        if (victoryStatus != Game.GAME_IS_STILL_GOING_ON) {
            Intent intent = new Intent(PlayerVsComputerActivity.this, GameOverActivity.class); // Move the GameOverActivity after the game ended.
            intent.putExtra("victoryStatus", victoryStatus);
            intent.putExtra("greet", game.getGameOverGreet());
            startActivityForResult(intent, REQUEST);
        }
    }

    @Override
    public void onClick(View v) {
        victoryStatus = checkGameOver();
        if (!isGameOver)
            manageGame(((Button) v));
        else
            onGameOver();
    }
}
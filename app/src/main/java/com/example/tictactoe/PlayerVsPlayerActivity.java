package com.example.tictactoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
// TODO-intent data

public class PlayerVsPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mainLayoutPlayerVsPlayer; // The main layout.

    private TextView tvGameStatus; // This text view will be used to show who's turn is and who won.

    private boolean xTurn; // X begins first. If true, it's X's turn. If false, it's Circle's turn.

    private int victoryStatus = Game.TIE; // Game status.
    private int level = 0; // This is the level.

    public static final int REQUEST = 1;
    private Game game; // Game manager.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_player);

        game = new Game();
        xTurn = false;
        victoryStatus = Game.GAME_IS_STILL_GOING_ON;
        // Initialize important game variables.

        mainLayoutPlayerVsPlayer = findViewById(R.id.mainLayoutPlayerVsPlayer);

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
            mainLayoutPlayerVsPlayer.addView(newLayout);
        }
        createStatusLayout(); // Create the status layout.
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        mainLayoutPlayerVsPlayer.addView(layoutStatus);
        // Add views to the layouts.
    }

    /**
     * This function sets the button background to be x or circle and manages the game.
     */
    private void manageGame(Button btn) {
        victoryStatus = game.checkForWin();
        handleGameOver();
        if (xTurn) { // If it's X's turn.
            handleGameOver();
            if (btn.getText().toString().equals("")) { // If the button has not pressed yet.
                btn.setText(Game.X);
                xTurn = false; // Circle's turn.
                game.setItemAt(Game.X, game.charToInt(btn.getTag().toString().charAt(0)), game.charToInt(btn.getTag().toString().charAt(2))); // X took the match index.
                tvGameStatus.setText(Game.CIRCLE_TURN);
                btn.setClickable(false);
                handleGameOver();
            }
        }
        else { // If its circle's turn.
            if (btn.getText().toString().equals("")) { // If the button has not pressed yet.
                btn.setText(Game.CIRCLE);
                xTurn = true; // X's turn.
                game.setItemAt(Game.CIRCLE, game.charToInt(btn.getTag().toString().charAt(0)), game.charToInt(btn.getTag().toString().charAt(2))); // Circle took the match index.
                tvGameStatus.setText(Game.X_TURN);
                btn.setClickable(false);
                handleGameOver();
            }
        }
    }

    /**
     * This function cancels the click option for all the buttons.
     */
    private void cancelButtonClick() {
        if (victoryStatus != Game.GAME_IS_STILL_GOING_ON) { // If the game is over for any reason.
            for (int i = 0; i < Game.WIDTH; i++) {
                for (int j = 0; j < Game.HEIGHT; j++) {
                    Button btn = findViewById(game.getId(i, j));
                    btn.setClickable(false);
                }
            }
        }
    }

    /**
     * This function checks if the game is over.
     * If so, it will call {@link #onGameOver}
     */
    private void handleGameOver() {
        victoryStatus = game.checkForWin();
        if (victoryStatus != Game.GAME_IS_STILL_GOING_ON) { // If the game is over for any reason.
            onGameOver();
        }
    }

    /**
     * This function is called When the game is over,
     * and moves the user to {@link GameOverActivity}.
     */
    private void onGameOver() {
        if (victoryStatus != Game.GAME_IS_STILL_GOING_ON) {
            Intent intent = new Intent(PlayerVsPlayerActivity.this, GameOverActivity.class); // Move the GameOverActivity after the game ended.
            intent.putExtra("victoryStatus", victoryStatus);
            intent.putExtra("greet", game.getGameOverGreet());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        victoryStatus = game.checkForWin();
        if (victoryStatus == Game.GAME_IS_STILL_GOING_ON)
            manageGame(((Button) v));
        else
            onGameOver();
    }
}
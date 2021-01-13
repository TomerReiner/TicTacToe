package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView tvGameOverGreet;
    private Button btnPlayAgain;

    private SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
    private String username = sharedPreferences.getString(MainActivity.USERNAME, ""); // Get the current username.

    private CustomDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        db = new CustomDatabaseHelper(GameOverActivity.this);

        tvGameOverGreet = findViewById(R.id.tvGameOverGreet);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        Intent intent = getIntent();
        int victoryStatus = intent.getIntExtra("victoryStatus", Game.TIE); // Get the victory status.
        String greet = intent.getStringExtra("greet");

        db.updateUserData(username, victoryStatus); // Updating the user data.

        tvGameOverGreet.setText(greet);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
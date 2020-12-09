package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView tvGameOverGreet;
    private Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tvGameOverGreet = findViewById(R.id.tvGameOverGreet);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        Intent intent = getIntent();
        int victoryStatus = intent.getIntExtra("victoryStatus", Game.TIE); // Get the victory status.
        String greet = intent.getStringExtra("greet");
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
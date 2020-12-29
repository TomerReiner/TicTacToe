package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ChampionsTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_table);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemLogOut) {
            //TODO-Log Out
        }
        else if (item.getItemId() == R.id.itemGoHome) {
            Intent moveBackToMainActivity = new Intent(ChampionsTableActivity.this, MainActivity.class);
            startActivity(moveBackToMainActivity);
        }
        // We don't implement what happens when clicking on champions table item icon because we are at home screen.
        return true;
    }
}
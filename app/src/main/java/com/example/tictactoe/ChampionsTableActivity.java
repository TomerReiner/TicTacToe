package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

public class ChampionsTableActivity extends AppCompatActivity {

    private ListView lvChampionsTable;

    private ArrayList<User> users;
    private ChampionsTableListVIewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_table);

        lvChampionsTable = findViewById(R.id.lvChampionsTable);

        users = new ArrayList<User>();
        users.add(new User("Tomer", "6", new UserData(50, 10, 15)));
        users.add(new User("King", "6", new UserData(5, 6, 1)));
        users.add(new User("Tomer2", "6", new UserData(23, 13, 24)));
        users.add(new User("King2", "6", new UserData(5, 78, 11)));
        users.add(new User("Tomer3", "6", new UserData(400, 341, 74)));

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                UserData dataO1 = o1.getData();
                UserData dataO2 = o2.getData();

                double o1PercentageOfWins = dataO1.getPercentageOfWins(); // Get the percentage of wins for user o1.
                double o2PercentageOfWins = dataO2.getPercentageOfWins(); // Get the percentage of wins for user o2.

                if (o1PercentageOfWins > o2PercentageOfWins)
                    return -1;
                return o1PercentageOfWins == o2PercentageOfWins ? 0 : 1;
            }
        });

        adapter = new ChampionsTableListVIewAdapter(ChampionsTableActivity.this, users);
        lvChampionsTable.setAdapter(adapter);
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
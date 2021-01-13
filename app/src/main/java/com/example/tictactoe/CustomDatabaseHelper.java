package com.example.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLOutput;

public class CustomDatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String TOTAL_NUM_OF_GAMES = "numOfGames";
    public static final String NUM_OF_WINS = "numOfWins";
    public static final String NUM_OF_TIES = "numOfTies";
    public static final String NUM_OF_DEFEATS = "numOfDefeats";

    public CustomDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "%s TEXT PRIMARY KEY NOT NULL, " +
                "%s TEXT NOT NULL, " +
                "%s INTEGER DEFAULT 0, " +
                "%s INTEGER DEFAULT 0, " +
                "%s INTEGER DEFAULT 0, " +
                "%s INTEGER DEFAULT 0" +
                ");", TABLE_NAME, USERNAME, PASSWORD, TOTAL_NUM_OF_GAMES, NUM_OF_WINS, NUM_OF_TIES, NUM_OF_DEFEATS);
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * This function adds another user to the database.
     * @param user The user.
     * @return <code>true<code/> if the user was successfully added to the database, <code>false</code> if not.
     */
    public boolean addUser(User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();

            ContentValues cv = new ContentValues();
            cv.put(USERNAME, username);
            cv.put(PASSWORD, password);

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_NAME, null, cv);
            db.close();
        }
        catch (Exception e) {
            /* Catch exception of any kind- Username or password empty,
             or trying to use an existing username. */
            return false;
        }
        return true;
    }

    /**
     * This function logs the user in.
     * @param user The user which we want to log in.
     * @return <code>true</code> if the user was successfully logged in, <code>false</code> if not.
     */
    public boolean logIn(User user) {
        String query = String.format("SELECT %s, %s FROM %s;", USERNAME, PASSWORD, TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();

        String username = user.getUsername();
        String password = user.getPassword();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String currentUsername = cursor.getString(cursor.getColumnIndex(USERNAME));
                String currentPassword = cursor.getString(cursor.getColumnIndex(PASSWORD));

                if (username.equals(currentUsername) && password.equals(currentPassword)) // If the user was found.
                    return true;
            }
        }
        db.close();
        return false; // No matching user was found.
    }
// TODO-handle sign up exception
    /**
     * This function updates the userdata
     * @param username The username which we want to update its data
     * @param column The column which we want to update.
     * @return <code>true</code> if the update succeeded, <code>false</code> if not.
     */
    private boolean updateUserData(String username, String column) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String query = String.format("UPDATE %s SET %s = %s + 1 WHERE %s = ?;", TABLE_NAME, column, column, USERNAME);
            db.execSQL(query, new String[] {String.valueOf(username)});

        }
        catch (Exception e) { // Error encountered.
            return false;
        }
        return true;
    }

    /**
     *
     * @param username The username.
     * @param victoryStatus The victory status.
     * @return <code>true</code> if the update succeeded, <code>false</code> if not.
     * @see #updateUserData(String, String)
     * @see Game
     */
    public boolean updateUserData(String username, int victoryStatus) {
        String column = "";
        if (victoryStatus == Game.CIRCLE_WON)
            column = NUM_OF_WINS;
        if (victoryStatus == Game.TIE)
            column = NUM_OF_TIES;
        if (victoryStatus == Game.X_WON)
            column = NUM_OF_DEFEATS;
        if (column.equals(""))
            return false;
        return updateUserData(username, column) && updateUserData(username, TOTAL_NUM_OF_GAMES);
    }

}
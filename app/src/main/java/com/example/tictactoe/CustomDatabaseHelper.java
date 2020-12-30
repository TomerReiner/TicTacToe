package com.example.tictactoe;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomDatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public CustomDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CustomDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String query = String.format("CREATE TABLE IF NOT EXISTS %s (" +
//                "%s VARCHAR(20) PRIMARY KEY NOT NULL, " +
//                "%s VARCHAR(20) NOT NULL);", TABLE_NAME, USERNAME, PASSWORD);

        db.execSQL("CREATE TABLE IF NOT EXISTS users (username VARCHAR(200) PRIMARY KEY NOT NULL, password VARCHAR(200) NOT NULL);");
        db.close();
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

        String username = user.getUserName();
        String password = user.getPassword();

        String query = String.format("INSERT INTO %s VALUES(%s, %s);", TABLE_NAME, username, password);

        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(query);
        }
        catch (SQLException e) {
            /* Catch exception of any kind- Username or password empty or too long(more than 200 characters),
             or trying to use an existing username. */
            return false;
        }

        return true;
    }
}
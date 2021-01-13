package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "username";

    private CustomDatabaseHelper db;

    private String currentUser = "";

    private Dialog logInDialog;
    private Dialog signUpDialog;

    private User user;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new CustomDatabaseHelper(MainActivity.this);

        logInDialog = new Dialog(MainActivity.this);
        signUpDialog = new Dialog(MainActivity.this);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        editor = sharedPreferences.edit();

        currentUser = sharedPreferences.getString(USERNAME, "");

        if (currentUser.equals("")) // If there is no user logged in.
            createLogInDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logInDialog.dismiss();
        signUpDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemLogOut) { // If the user wants to log out.
            editor.putString(USERNAME, "");
            editor.apply();
        }
        else if (item.getItemId() == R.id.itemChampionTable) {
            Intent moveToChampionsTableActivity = new Intent(MainActivity.this, ChampionsTableActivity.class);
            startActivity(moveToChampionsTableActivity);
        }
        // We don't implement what happens when clicking on home icon because we are at home screen.
        return true;
    }

    /**
     * This function creates the log in dialog, where the user will log in to the app.
     * The dialog won't show if the user is already logged in.
     */
    private void createLogInDialog() {
        logInDialog.setContentView(R.layout.custom_log_in_dialog);
        logInDialog.setCancelable(false);


        EditText etLogInUsername = logInDialog.findViewById(R.id.etLogInUsername);
        EditText etLogInPassword = logInDialog.findViewById(R.id.etLogInPassword);
        Button btnLogIn = logInDialog.findViewById(R.id.btnLogIn);
        Button btnMoveToSignUp = logInDialog.findViewById(R.id.btnMoveToSignUp);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLogInUsername.getText().toString();
                String password = etLogInPassword.getText().toString();

                user = new User(username, password, new UserData(0, 0, 0));

                boolean isSuccessfullyLoggedIn = db.logIn(user);

                if (!isSuccessfullyLoggedIn) { // If the login failed.
                    Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentUser = username;
                logInUser(currentUser);
                logInDialog.dismiss(); // This is temporary.
            }
        });

        btnMoveToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInDialog.dismiss();
                createSignUpDialog();
            }
        });
        logInDialog.show();
    }

    /**
     * This function creates the sign up dialog and signs up the user.
     * The function checks if the user name and password are valid,
     * and if so it will enter the user to the app.
     */
    private void createSignUpDialog() {
        signUpDialog.setContentView(R.layout.custom_sign_up_dialog);
        signUpDialog.setCancelable(false);

        signUpDialog.setTitle("Sign Up");

        EditText etSignUpUsername = signUpDialog.findViewById(R.id.etSignUpUsername);
        EditText etSignUpPassword = signUpDialog.findViewById(R.id.etSignUpPassword);
        EditText etSignUpRetypePassword = signUpDialog.findViewById(R.id.etSignUpRetypePassword);

        Button btnSignUp = signUpDialog.findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etSignUpUsername.getText().toString();
                String password = etSignUpPassword.getText().toString();
                String retypePassword = etSignUpRetypePassword.getText().toString();

                if (!isSignUpValid(username, password,retypePassword)) { // If one of the fields is not well filled.
                    Toast.makeText(MainActivity.this, R.string.log_in_alert, Toast.LENGTH_LONG).show();
                    etSignUpUsername.setText("");
                    etSignUpPassword.setText("");
                    etSignUpRetypePassword.setText(""); // Clear the fields.
                    return;
                }
                // If all the fields are good.
                user = new User(username, password, new UserData(0, 0, 0));

                boolean isSuccessfullyAddedUser = db.addUser(user);

                if (!isSuccessfullyAddedUser) { // If the user wasn't successfully added.
                    Toast.makeText(MainActivity.this, "Try using another username.", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentUser = username;
                logInUser(currentUser);

                signUpDialog.dismiss(); // Successfully logged in.
            }
        });
        signUpDialog.show();
    }

    /**
     * This function moves the user to the matching game activity.
     * @param view the button that was pressed
     */
    public void moveToGame(View view) {

        db.close();

        if (currentUser.equals("")) { // If there is no user logged in.
            Toast.makeText(MainActivity.this, "Please log in to continue.", Toast.LENGTH_SHORT).show();
        }

        if (view.getId() == R.id.btnMoveToPlayerVsPlayer) { // If the button that was pressed is btnMoveToPlayerVsPlayer.
            Intent intent = new Intent(MainActivity.this, PlayerVsPlayerActivity.class);
            startActivity(intent); // move to PlayerVsPlayerActivity.
        }
        else if (view.getId() == R.id.btnMoveToPlayerVsComputer) {
            Intent intent = new Intent(MainActivity.this, PlayerVsComputerActivity.class);
            startActivity(intent); // move to PlayerVsComputerActivity.
        }
    }

    /**
     * This function checks if the user name and pass words are valid.
     * @param username The username.
     * @param password The password.
     * @param retypePassword The password, typed again.
     * @return <code>true</code> if the user name in not empty and not taken, and <code>password</code> is not empty and equals to <code>retypePassword</code>. <code>false</code> if not.
     */
    private boolean isSignUpValid(String username, String password, String retypePassword) {
        return !username.equals("") && password.equals(retypePassword) && !password.equals("");
    }

    /**
     * This function logs the user in.
     * @param username The username.
     */
    private void logInUser(String username) {
        editor.putString(USERNAME, username); // Put the user in shared preferences.
        editor.apply();
    }
}
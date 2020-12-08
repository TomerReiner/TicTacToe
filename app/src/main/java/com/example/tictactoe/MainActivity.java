package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Dialog logInDialog;
    private Dialog signUpDialog;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInDialog = new Dialog(this);
        signUpDialog = new Dialog(this);

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
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemLogOut) {
            //TODO-Log Out
        }
        return true;
    }

    /**
     * This function creates the log in dialog, where the user will log in to the app.
     * The dialog won't show if the user is already logged in.
     * TODO-The dialog won't show if the user is already logged in.
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
                String userName = etLogInUsername.getText().toString();
                String password = etLogInPassword.getText().toString();

                user = new User(userName, password, 0, 0, 0, 0, 0, 0, 0, 0);
                // TODO-On Login.
                logInDialog.dismiss(); // This is temporary.
            }
        });

        btnMoveToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignUpDialog();
                logInDialog.dismiss();
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
                //TODO-condition if the user name is valid
                //TODO-check password valid

                signUpDialog.dismiss();
            }
        });
        signUpDialog.show();
    }

    /**
     * This function moves the user to the matching game activity.
     * @param view the button that was pressed
     */
    public void moveToGame(View view) {
        if (view.getId() == R.id.btnMoveToPlayerVsPlayer) { // If the button that was pressed is btnMoveToPlayerVsPlayer.
            Intent intent = new Intent(MainActivity.this, PlayerVsPlayerActivity.class);
            intent.putExtra("user", user); // Sending the user data so we will be able to change them.
            startActivity(intent); // move to PlayerVsPlayerActivity.
        }
        else if (view.getId() == R.id.btnMoveToPlayerVsComputer) {
            Intent intent = new Intent(MainActivity.this, PlayerVsComputerActivity.class);
            intent.putExtra("user", user); // Sending the user data so we will be able to change them.
            startActivity(intent); // move to PlayerVsComputerActivity.
        }
    }
}
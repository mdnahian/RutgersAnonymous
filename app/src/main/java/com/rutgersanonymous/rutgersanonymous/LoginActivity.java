package com.rutgersanonymous.rutgersanonymous;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rutgersanonymous.rutgersanonymous.data.SavedSession;

/**
 * Created by mdislam on 3/14/16.
 */
public class LoginActivity extends Activity {


    private EditText usernameField;
    private EditText passwordField;
    private TextView loginBtn;

    private TextView forgotBtn;
    private TextView signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        loginBtn = (TextView) findViewById(R.id.login);

        forgotBtn = (TextView) findViewById(R.id.forgot);
        signupBtn = (TextView) findViewById(R.id.signup);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(usernameField.getText().toString(), passwordField.getText().toString());
            }
        });


        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }


    public void login(final String username, final String password){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(final ParseUser user, ParseException e) {
                if (user != null) {
                    if (user.getBoolean("emailVerified")) {
                        SavedSession savedSession = new SavedSession();
                        savedSession.setUsername(username);
                        savedSession.setPassword(password);
                        createSavedSession(savedSession);

                        progressDialog.dismiss();

                        onPostLogin(getApplicationContext());
                    } else {

                        progressDialog.dismiss();

                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Confirm Email Address")
                                .setMessage("You have not yet confirmed your email address " + user.getEmail() + ".")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Resend Email", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sendVerificationEmail(user.getObjectId(), user.getEmail());
                                    }
                                })
                                .setIcon(R.drawable.logo)
                                .show();
                    }
                } else {
                    progressDialog.dismiss();
                    buildDialog("Try Again", "Username or Password Incorrect");
                }
            }
        });





    }


    public void createSavedSession(SavedSession savedSession){
        SharedPreferences sp = getSharedPreferences("SavedSession", 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("username", savedSession.getUsername());
        ed.putString("password", savedSession.getPassword());
        ed.apply();
    }


    public void onPostLogin(Context context){
        Intent intent = new Intent(context, ParentActivity.class);
        startActivity(intent);
        finish();
    }


    public void buildDialog(String title, String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.drawable.logo)
                .show();
    }


    public void sendVerificationEmail(String userId, String email){

    }

}

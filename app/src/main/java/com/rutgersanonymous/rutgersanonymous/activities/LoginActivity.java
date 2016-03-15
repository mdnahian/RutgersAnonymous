package com.rutgersanonymous.rutgersanonymous.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rutgersanonymous.rutgersanonymous.R;
import com.rutgersanonymous.rutgersanonymous.core.ExecuteSQL;
import com.rutgersanonymous.rutgersanonymous.core.ExecuteSQLResponse;
import com.rutgersanonymous.rutgersanonymous.core.ParseServerResponse;
import com.rutgersanonymous.rutgersanonymous.data.SavedSession;

import java.util.ArrayList;

/**
 * Created by mdislam on 3/14/16.
 */
public class LoginActivity extends SessionActivity implements ExecuteSQLResponse{


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

    public void login(String user, String pass){

        String sql = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pass + "' LIMIT 1";
        ExecuteSQL executeSQL = new ExecuteSQL();
        executeSQL.delegate = this;

        executeSQL.execute(sql, requestURL, apiKey);
    }


    @Override
    public void processFinish(String output) {

        boolean status = false;

        if(output != null) {
            ParseServerResponse parseServerResponse = new ParseServerResponse(output);

            if (parseServerResponse.execute()) {
                ArrayList objects = parseServerResponse.getObjects();

                if (objects != null) {
                    SavedSession savedSession = new SavedSession();

                    ArrayList object = (ArrayList) objects.get(0);

                    savedSession.setId(object.get(0).toString());
                    savedSession.setUsername(object.get(1).toString());
                    savedSession.setEmail(object.get(2).toString());
                    savedSession.setPassword(object.get(3).toString());
                    savedSession.setCampus(object.get(4).toString());

                    createSavedSession(savedSession);

                    status = true;
                }
            }
        }

        if(!status){
            buildDialog("Login Failed", "Username or Password Incorrect");
        } else {
            onPostLogin(LoginActivity.this);
        }
    }
}

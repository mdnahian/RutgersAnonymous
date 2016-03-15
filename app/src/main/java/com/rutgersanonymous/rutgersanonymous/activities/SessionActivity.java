package com.rutgersanonymous.rutgersanonymous.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.rutgersanonymous.rutgersanonymous.ParentActivity;
import com.rutgersanonymous.rutgersanonymous.R;
import com.rutgersanonymous.rutgersanonymous.data.SavedSession;

/**
 * Created by mdislam on 3/14/16.
 */
public class SessionActivity extends AppCompatActivity {

    private SavedSession savedSession;
    public String requestURL;
    public String apiKey;


    public SessionActivity(){
        savedSession = new SavedSession();
    }


    public boolean isLoggedIn(){
        loadSavedSession();
        requestURL = getString(R.string.baseURL)+getString(R.string.sqlExeURL);
        apiKey = getString(R.string.apiKey);
        return !(savedSession.getId() == null); // returns true if logged in
    }


    public SavedSession getSavedSession(){
        loadSavedSession();
        return savedSession;
    }


    public void loadSavedSession(){
        SharedPreferences sp1 = this.getSharedPreferences("SavedSession", 0);
        savedSession.setId(sp1.getString("id", null));
        savedSession.setUsername(sp1.getString("username", null));
        savedSession.setEmail(sp1.getString("email", null));
        savedSession.setPassword(sp1.getString("password", null));
        savedSession.setCampus(sp1.getString("campus", null));
    }


    public void createSavedSession(SavedSession savedSession){
        SharedPreferences sp = getSharedPreferences("SavedSession", 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("id", savedSession.getId());
        ed.putString("username", savedSession.getUsername());
        ed.putString("email", savedSession.getEmail());
        ed.putString("password", savedSession.getPassword());
        ed.putString("campus", savedSession.getCampus());
        ed.apply();
    }



    public void onPostLogin(Context context){
        Intent intent = new Intent(context, ParentActivity.class);
        startActivity(intent);
        finish();
    }



    public void logout(Context context){
        SharedPreferences sp = getSharedPreferences("SavedSession", 0);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.clear();
        Ed.apply();

        Intent intent = new Intent(context, LoginActivity.class);
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


}

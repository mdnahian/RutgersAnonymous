package com.rutgersanonymous.rutgersanonymous;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;


import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.rutgersanonymous.rutgersanonymous.data.SavedSession;
import com.rutgersanonymous.rutgersanonymous.fragments.AccountFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.ActivityFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.HomeFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.MessageFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.PostFragment;

public class ParentActivity extends AppCompatActivity {


    private SavedSession savedSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedSession = new SavedSession();

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(new Parse.Configuration.Builder(this)
                            .applicationId(getString(R.string.parseAppId))
                            .clientKey(null)
                            .server(getString(R.string.parseServerURL)).build()
            );
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e){
            Log.d("Crash", "Could not connect to parse server.");
        }

        if(!isLoggedIn()){
            logout(this);
        }

        setContentView(R.layout.parent_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.up_down));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.plus));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.message));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.person));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        home();
                        break;
                    case 1:
                        activity();
                        break;
                    case 2:
                        post();
                        break;
                    case 3:
                        message();
                        break;
                    case 4:
                        account();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        home();

    }


    public void home(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment, homeFragment);
        fragmentTransaction.commit();
    }


    public void activity(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActivityFragment activityFragment = new ActivityFragment();
        fragmentTransaction.replace(R.id.fragment, activityFragment);
        fragmentTransaction.commit();
    }

    public void post(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PostFragment postFragment = new PostFragment();
        fragmentTransaction.replace(R.id.fragment, postFragment);
        fragmentTransaction.commit();
    }

    public void message(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MessageFragment messageFragment = new MessageFragment();
        fragmentTransaction.replace(R.id.fragment, messageFragment);
        fragmentTransaction.commit();
    }

    public void account(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AccountFragment accountFragment = new AccountFragment();
        fragmentTransaction.replace(R.id.fragment, accountFragment);
        fragmentTransaction.commit();
    }


    public void loadSavedSession(){
        SharedPreferences sp1 = this.getSharedPreferences("SavedSession", 0);
        savedSession.setUsername(sp1.getString("username", null));
        savedSession.setPassword(sp1.getString("password", null));
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


    public boolean isLoggedIn(){

        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return true;
        }

        loadSavedSession();
        return (savedSession.getUsername() != null);
    }


    public void logout(Context context){
        SharedPreferences sp = getSharedPreferences("SavedSession", 0);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.clear();
        Ed.apply();

        ParseUser.logOut();

        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent_menu, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Anything...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }




}

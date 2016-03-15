package com.rutgersanonymous.rutgersanonymous;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.SearchView;

import com.rutgersanonymous.rutgersanonymous.activities.SessionActivity;
import com.rutgersanonymous.rutgersanonymous.fragments.AccountFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.ActivityFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.HomeFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.MessageFragment;
import com.rutgersanonymous.rutgersanonymous.fragments.PostFragment;

public class ParentActivity extends SessionActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(!isLoggedIn()){
            logout(ParentActivity.this);
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

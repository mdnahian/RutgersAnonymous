package com.rutgersanonymous.rutgersanonymous.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rutgersanonymous.rutgersanonymous.ParentActivity;
import com.rutgersanonymous.rutgersanonymous.R;

/**
 * Created by mdislam on 3/13/16.
 */
public class HomeFragment extends Fragment {

    private ParentActivity parentActivity;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        parentActivity = (ParentActivity) getActivity();

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Newest"));
        tabLayout.addTab(tabLayout.newTab().setText("Best"));


        return rootView;
    }
}

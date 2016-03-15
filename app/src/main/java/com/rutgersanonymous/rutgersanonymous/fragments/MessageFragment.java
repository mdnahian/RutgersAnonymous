package com.rutgersanonymous.rutgersanonymous.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rutgersanonymous.rutgersanonymous.R;

/**
 * Created by mdislam on 3/13/16.
 */
public class MessageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.empty_fragment, container, false);

        return rootView;
    }

}

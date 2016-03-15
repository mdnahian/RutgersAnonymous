package com.rutgersanonymous.rutgersanonymous.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.rutgersanonymous.rutgersanonymous.ParentActivity;
import com.rutgersanonymous.rutgersanonymous.R;

/**
 * Created by mdislam on 3/13/16.
 */
public class PostFragment extends Fragment {

    private ParentActivity parentActivity;

    private TabLayout tabLayout;
    private EditText post;
    private TextView postBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.post_fragment, container, false);

        parentActivity = (ParentActivity) getActivity();

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.image));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.video));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.document));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));

        post = (EditText) rootView.findViewById(R.id.post);

        postBtn = (TextView) rootView.findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!post.getText().toString().equals("")) {

                } else {
                    parentActivity.buildDialog("Cannot Leave Empty", "You must write something to post something.");
                }
            }
        });

        return rootView;
    }





}

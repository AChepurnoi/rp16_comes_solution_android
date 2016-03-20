package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Dashboard_fragment extends Fragment {


    private Toolbar toolbar;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_dashboard, container, false);
        configurePage(view);
        return view;
    }


    private void configurePage(View view){

        activity = (MainActivity)getActivity();
        configureToolbar(view);
        Button my_account = (Button)view.findViewById(R.id.button_my_account);
        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.my_account();
            }
        });

    }

    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Dashboard");

    }



}

package com.bionic.td_android.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Temporary_pass_fragment extends Fragment{



    private LoginActivity activity;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_pass,container,false);
        configure(view);
        return view;
    }

    private void configure(View view){
        activity = (LoginActivity) getActivity();
        configureToolbar(view);


    }


    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Password Recovery");

    }

}

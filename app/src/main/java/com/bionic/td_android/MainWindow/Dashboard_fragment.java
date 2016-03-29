package com.bionic.td_android.MainWindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Dashboard_fragment extends Fragment {


    private Toolbar toolbar;
    private MainActivity activity;
    private User user;
    private TextView greeting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_dashboard, container, false);
        configurePage(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void configurePage(View view){

        activity = (MainActivity)getActivity();
        user = activity.getUser();
        configureToolbar(view);
        configureViews(view);

        greeting = (TextView) view.findViewById(R.id.user_name_textfield);
        if(user != null && greeting != null)greeting.setText(user.getFirstName() + " " + user.getLastName());

        Button my_account = (Button)view.findViewById(R.id.button_my_account);
        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.my_account();
            }
        });

        Button new_shift = (Button)view.findViewById(R.id.button_shift);
        new_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.new_shift();
            }
        });

    }

    private void configureViews(View view){

        TextView logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void configureToolbar(View view){

        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Dashboard");

    }



}

package com.bionic.td_android.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Forgotten_password extends Fragment {


    private LoginActivity activity;
    private Toolbar toolbar;
    private EditText email;
    private Button reset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgotten_pass, container, false);
        configurePage(view);
        return view;


    }


    private void configurePage(View view) {

        activity = (LoginActivity) getActivity();
        configureToolbar(view);
        email = (EditText) view.findViewById(R.id.input_email);

        email.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                activity.resetPassword(email.getText().toString());
            }
            return false;
        });


        reset = (Button) view.findViewById(R.id.button_reset_pass);
        reset.setOnClickListener(v -> activity.resetPassword(email.getText().toString()));


    }

    private void configureToolbar(View view) {


        toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Password recovery");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                activity.onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}

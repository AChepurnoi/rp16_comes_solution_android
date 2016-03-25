package com.bionic.td_android.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bionic.td_android.R;

/**
 * Created by user on 17.03.2016.
 */
public class LoginFragment extends Fragment {


    private Button register,login;
    private EditText log,pass;
    private View forgot_pass;
    private Toolbar toolbar;
    private LoginActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        configurePage(view);
        return view;
    }


    private void configurePage(View view){

        activity = (LoginActivity)getActivity();
        configureToolbar(view);
        log = (EditText) view.findViewById(R.id.input_login);
        pass = (EditText) view.findViewById(R.id.input_password);

         pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    activity.login(log.getText().toString(),pass.getText().toString());
                }
                return false;
            }
         });


        register = (Button) view.findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.register();
            }
        });

        forgot_pass = view.findViewById(R.id.forgot_password);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.forgot_password();
            }
        });

        login = (Button) view.findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.login(log.getText().toString(),pass.getText().toString());
            }
        });


    }

    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Login");

    }

}

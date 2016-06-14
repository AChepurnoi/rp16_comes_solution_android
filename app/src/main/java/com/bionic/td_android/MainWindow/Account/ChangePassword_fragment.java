package com.bionic.td_android.MainWindow.Account;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.ApplyNewPassword;
import com.bionic.td_android.Networking.Requests.Login;
import com.bionic.td_android.R;

/**
 * Created by user on 15.04.2016.
 */

public class ChangePassword_fragment extends Fragment {


    private EditText tmpPass,newPass,repeatPass;
    private AppCompatActivity activity;
    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_pass,container,false);
        configure(view);
        return view;
    }

    private void configure(View view){
        activity = (AppCompatActivity) getActivity();
        configureToolbar(view);
        configureViews(view);

    }


    private boolean validate(String tmp,String newPs,String repeatPs){

        if(newPs.equals(repeatPs))return true;
        return false;

    }


    private void configureViews(final View view){
        tmpPass = (EditText) view.findViewById(R.id.input_temp_password);
        tmpPass.setHint("Password");
        newPass = (EditText) view.findViewById(R.id.input_new_password);
        repeatPass = (EditText) view.findViewById(R.id.input_repeat_password);

        Button button = (Button) view.findViewById(R.id.button_apply_new_password);
        button.setOnClickListener(v -> {
            String tmp = tmpPass.getText().toString();
            String newPassword = newPass.getText().toString();
            String repeatPassword = repeatPass.getText().toString();
            DbManager manager = new DbManager(getContext());
            User user = manager.loadUser();

            if (validate(tmp, newPassword, repeatPassword)){
                new ApplyNewPassword(user,tmp,newPassword,getView()).execute();
            }else Snackbar.make(view, "Check password matching", Snackbar.LENGTH_LONG).show();


        });
    }


    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Password Changing");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                activity.onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}

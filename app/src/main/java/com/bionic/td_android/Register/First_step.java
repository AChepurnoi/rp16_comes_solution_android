package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.R;

import java.util.Date;

/**
 * Created by user on 17.03.2016.
 */
public class First_step extends Fragment {



    private RegisterActivity activity;
    private EditText name, surname, second_name, code_area, email, password, repeat_password;
    private Spinner gender;
    private Button next_step;

    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_step1, container, false);
        configure(view);
        return view;

    }


    public boolean validateForm(){

        boolean empty = (isEmpty(name) || isEmpty(surname) || isEmpty(email) || isEmpty(password) || isEmpty(repeat_password));
        if(empty)return false;
        else return password.getText().toString().equals(repeat_password.getText().toString());

    }

    private void configure(View view){

        activity = (RegisterActivity)getActivity();
        configureToolbar(view);
        configureViews(view);



    }

    private User formUser(){
        User user = new User();
        user.setFirstName(name.getText().toString());
        user.setLastName(surname.getText().toString());
        user.setInsertion(second_name.getText().toString());
        user.setSex(gender.getSelectedItem().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPasswordExpire(new Date(new Date().getTime() * 2));

        return user;
    }

    private void configureViews(View view){

        name = (EditText) view.findViewById(R.id.input_name);
        surname = (EditText) view.findViewById(R.id.input_surname);
        second_name = (EditText) view.findViewById(R.id.input_second_name);
        gender = (Spinner) view.findViewById(R.id.input_gender);
        code_area = (EditText) view.findViewById(R.id.input_code_area);
        email = (EditText) view.findViewById(R.id.input_email);
        password = (EditText) view.findViewById(R.id.input_password);
        repeat_password = (EditText) view.findViewById(R.id.input_repeat_password);

        next_step = (Button) view.findViewById(R.id.button_go_step_2);
        next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity parent = (RegisterActivity) getActivity();
                parent.nextStep(formUser());
            }
        });


    }

    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Registration");

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



    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}

package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Second_step extends Fragment {

    private RegisterActivity activity;
    private Toolbar toolbar;
    private CheckBox driver,operator;
    private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private CheckBox day_contract, zero_day_contract;
    private EditText contract_days;
    private CheckBox mounthly_payments, four_week_payments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_step2, container, false);

        configure(view);
        return view;
    }




    private void configure(View view){

        activity = (RegisterActivity)getActivity();
        configureToolbar(view);
        configureViews(view);
        checkboxBehaviour();

    }

    private void configureViews(View view){

        driver = (CheckBox) view.findViewById(R.id.checkbox_driver);
        operator = (CheckBox) view.findViewById(R.id.checkbox_operator);
        monday = (EditText) view.findViewById(R.id.input_monday);
        tuesday = (EditText) view.findViewById(R.id.input_tuesday);
        wednesday = (EditText) view.findViewById(R.id.input_wednesday);
        thursday = (EditText) view.findViewById(R.id.input_thursday);
        friday = (EditText) view.findViewById(R.id.input_friday);
        saturday = (EditText) view.findViewById(R.id.input_saturday);
        sunday = (EditText) view.findViewById(R.id.input_sunday);
        day_contract = (CheckBox) view.findViewById(R.id.checkbox_day_contract);
        zero_day_contract = (CheckBox) view.findViewById(R.id.checkbox_zero_contract);
        contract_days = (EditText) view.findViewById(R.id.input_contract_days);
        mounthly_payments = (CheckBox) view.findViewById(R.id.checkbox_mounth_payments);
        four_week_payments = (CheckBox) view.findViewById(R.id.checkbox_four_week_payments);

    }


    private void checkboxBehaviour(){

        day_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(day_contract.isChecked())contract_days.setEnabled(true);
                    else contract_days.setEnabled(false);
                zero_day_contract.setChecked(false);

            }
        });

        zero_day_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zero_day_contract.isChecked()){
                    day_contract.setChecked(false);
                    contract_days.setEnabled(false);
                }

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

package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;
import com.bionic.td_android.R;

import java.util.Date;

/**
 * Created by user on 18.03.2016.
 */
public class Second_step extends Fragment implements TextWatcher{

    private RegisterActivity activity;
    private Toolbar toolbar;
    private CheckBox driver,operator;
    private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private CheckBox day_contract, zero_day_contract;
    private EditText contract_days;
    private CheckBox mounthly_payments, four_week_payments;
    private View scheduleBlock;
    private TextView error;
    private View button_help;

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    @Override
    public void afterTextChanged(Editable s) {

        if(day_contract.isChecked()) {

            int time = getHoursSum();
            int expectedTime = 0;
            try {
                expectedTime = Integer.parseInt(contract_days.getText().toString());
            } catch (Exception e) {
                Log.e("Bionic", "Error parsing time");
            }
            if (time != expectedTime) {
                error.setVisibility(View.VISIBLE);

            }else error.setVisibility(View.GONE);
        }else {
            error.setVisibility(View.GONE);
        }
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


        monday.addTextChangedListener(this);
        tuesday.addTextChangedListener(this);
        wednesday.addTextChangedListener(this);
        thursday.addTextChangedListener(this);
        friday.addTextChangedListener(this);
        saturday.addTextChangedListener(this);
        sunday.addTextChangedListener(this);

        day_contract = (CheckBox) view.findViewById(R.id.checkbox_day_contract);
        zero_day_contract = (CheckBox) view.findViewById(R.id.checkbox_zero_contract);
        contract_days = (EditText) view.findViewById(R.id.input_contract_days);
        contract_days.addTextChangedListener(this);
        mounthly_payments = (CheckBox) view.findViewById(R.id.checkbox_mounth_payments);
        four_week_payments = (CheckBox) view.findViewById(R.id.checkbox_four_week_payments);
        scheduleBlock = view.findViewById(R.id.block_schedule);
        Button register = (Button)view.findViewById(R.id.button_register);
        button_help = view.findViewById(R.id.button_help);
        error = (TextView) view.findViewById(R.id.error_hours);

        button_help.setOnClickListener(v -> {
            AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());
            ab.setTitle("Schedule tip");
            ab.setMessage("In this work schedule you fill in the number of hours you work each day");
            ab.setCancelable(true);
            ab.show();
        });

        register.setOnClickListener(v -> activity.secondStedRegister(formSecondPart()));

    }

    public boolean validateForm(View layout) {

        boolean checkbox = (driver.isChecked() || operator.isChecked())
                && ( (day_contract.isChecked() && !contract_days.getText().toString().isEmpty()) || zero_day_contract.isChecked())
                && (mounthly_payments.isChecked() || four_week_payments.isChecked());


        if(day_contract.isChecked()) {

            int time = getHoursSum();
            int expectedTime = 0;
            try {
                expectedTime = Integer.parseInt(contract_days.getText().toString());
            } catch (Exception e) {
                Log.e("Bionic", "Error parsing time");
                Snackbar.make(layout, "Fill in necessary forms", Snackbar.LENGTH_LONG).show();
                return false;
            }
            if (time != expectedTime) {
                Snackbar.make(layout,"The hours sum must be equal to total time",Snackbar.LENGTH_LONG).show();
                return false;
            }


            if (!(isDayValid(monday) && isDayValid(tuesday) && isDayValid(wednesday) && isDayValid(thursday) && isDayValid(friday) && isDayValid(saturday) && isDayValid(sunday))) {
                Snackbar.make(layout,"The input value must be between 0 and 24",Snackbar.LENGTH_LONG).show();
                return false;
            }

        }
        return checkbox;

    }

    private boolean isDayValid(EditText day) {
        if(day.getText().toString().isEmpty())return true;
        if ((Integer.parseInt(day.getText().toString()) < 0 || Integer.parseInt(day.getText().toString()) > 24)) {
            return false;
        } else {
            return true;
        }
    }

    private int getHoursSum(){
        int res = 0;
        try{
            if(!monday.getText().toString().isEmpty())
                res += Integer.parseInt(monday.getText().toString());
            if(!tuesday.getText().toString().isEmpty())
                res += Integer.parseInt(tuesday.getText().toString());
            if(!wednesday.getText().toString().isEmpty())
                res += Integer.parseInt(wednesday.getText().toString());
            if(!thursday.getText().toString().isEmpty())
                res += Integer.parseInt(thursday.getText().toString());
            if(!friday.getText().toString().isEmpty())
                res += Integer.parseInt(friday.getText().toString());
            if(!saturday.getText().toString().isEmpty())
                res += Integer.parseInt(saturday.getText().toString());
            if(!sunday.getText().toString().isEmpty())
                res += Integer.parseInt(sunday.getText().toString());
            return res;

        }catch (Exception e){
            Log.e("Bionic","Error parsing time");
            return 0;
        }

    }
    private User formSecondPart(){
        User user = new User();
//        List<Job> jobs = new ArrayList<>();
//        if(driver.isChecked()){
//            Job tmp = new Job();
//            tmp.setJobName("Driver");
//            jobs.add(tmp);
//        }
//        if(operator.isChecked()){
//            Job tmp = new Job();
//            tmp.setJobName("Operator");
//            jobs.add(tmp);
//        }
//        user.setJobs(jobs);
        WorkSchedule schedule = new WorkSchedule();
        schedule.setCreationTime(new Date());
        schedule.setMonday(monday.getText().toString());
        schedule.setTuesday(tuesday.getText().toString());
        schedule.setWednesday(wednesday.getText().toString());
        schedule.setThursday(thursday.getText().toString());
        schedule.setFriday(friday.getText().toString());
        schedule.setSaturday(saturday.getText().toString());
        schedule.setSunday(sunday.getText().toString());
        user.setWorkSchedule(schedule);
        if(day_contract.isChecked()){
            user.setZeroHours(false);
            if(!contract_days.getText().toString().isEmpty())
                user.setContractHours(Integer.parseInt(contract_days.getText().toString()));
        }

        if(zero_day_contract.isChecked()){
            user.setZeroHours(true);
        }

        if(four_week_payments.isChecked())user.setFourWeekPayOff(true);

        return user;


    }

    private void checkboxBehaviour(){

        day_contract.setOnClickListener(v -> {

            if (day_contract.isChecked()) contract_days.setEnabled(true);
            else contract_days.setEnabled(false);
            zero_day_contract.setChecked(false);

            scheduleBlock.setVisibility(View.VISIBLE);
            afterTextChanged(null);

        });

        zero_day_contract.setOnClickListener(v -> {
            if (zero_day_contract.isChecked()) {
                day_contract.setChecked(false);
                contract_days.setEnabled(false);
            }
            scheduleBlock.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
        });
        four_week_payments.setOnClickListener(v -> {
            if (mounthly_payments.isChecked()) mounthly_payments.setChecked(false);
        });
        mounthly_payments.setOnClickListener(v -> {
            if(four_week_payments.isChecked())four_week_payments.setChecked(false);

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


}

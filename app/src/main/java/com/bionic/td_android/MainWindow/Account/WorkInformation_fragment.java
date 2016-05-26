package com.bionic.td_android.MainWindow.Account;

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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 15.04.2016.
 */

public class WorkInformation_fragment extends Fragment implements TextWatcher {


    private MainActivity activity;
    private Toolbar toolbar;
    private CheckBox driver,operator;
    private RadioButton mandatoryTvt,voluntarilyTvt;
    private RadioButton paidTvt,buildUpTvt;
    private EditText tvtHours;
    private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private RadioButton day_contract, zero_day_contract;
    private EditText contract_days;
    private RadioButton mounthly_payments, four_week_payments;
    private View scheduleBlock;
    private View button_help;
    private TextView error;
    private User user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_step2, container, false);

        configure(view);
        return view;
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

    private void configure(View view){

        activity = (MainActivity)getActivity();

        configureToolbar(view);
        configureViews(view);
        checkboxBehaviour();
        loadValues();

    }

    private void loadValues(){
        DbManager manager = new DbManager(getContext());
        user = manager.loadUser();

        List<Integer> jobs = user.getJobs();
        for (Integer job : jobs) {
            if(job.equals(0))driver.setChecked(true);
            if(job.equals(1))operator.setChecked(true);
        }
        if(user.isFourWeekPayOff())four_week_payments.setChecked(true);
        else mounthly_payments.setChecked(true);

        if(user.isPaidTimeForTime())paidTvt.setChecked(true);
        else buildUpTvt.setChecked(true);

        if(user.getTvt() != 220){
            voluntarilyTvt.setChecked(true);
            tvtHours.setText(String.valueOf(user.getTvt()));
            tvtHours.setEnabled(true);
        }


        if(user.isZeroHours()){
            zero_day_contract.setChecked(true);
            scheduleBlock.setVisibility(View.GONE);


        }else {
            day_contract.setChecked(true);
            contract_days.setText(String.valueOf(user.getContractHours()));
            WorkSchedule schedule = user.getWorkSchedule();
            monday.setText(schedule.getMonday());
            tuesday.setText(schedule.getTuesday());
            wednesday.setText(schedule.getWednesday());
            thursday.setText(schedule.getThursday());
            friday.setText(schedule.getFriday());
            saturday.setText(schedule.getSaturday());
            sunday.setText(schedule.getSunday());

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

        mandatoryTvt = (RadioButton) view.findViewById(R.id.checkbox_mandatory);
        voluntarilyTvt = (RadioButton) view.findViewById(R.id.checkbox_voluntarily);
        paidTvt = (RadioButton) view.findViewById(R.id.checkbox_paid);
        buildUpTvt = (RadioButton) view.findViewById(R.id.checkbox_buildup);
        tvtHours = (EditText) view.findViewById(R.id.tvt_edit_text);
        tvtHours.setEnabled(false);


        day_contract = (RadioButton) view.findViewById(R.id.checkbox_day_contract);
        zero_day_contract = (RadioButton) view.findViewById(R.id.checkbox_zero_contract);
        contract_days = (EditText) view.findViewById(R.id.input_contract_days);
        contract_days.addTextChangedListener(this);

        mounthly_payments = (RadioButton) view.findViewById(R.id.checkbox_mounth_payments);
        four_week_payments = (RadioButton) view.findViewById(R.id.checkbox_four_week_payments);
        scheduleBlock = view.findViewById(R.id.block_schedule);
        error = (TextView) view.findViewById(R.id.error_hours);
        Button register = (Button)view.findViewById(R.id.button_register);
        button_help = view.findViewById(R.id.button_help);

        button_help.setOnClickListener(v -> {
            AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());
            ab.setTitle("Schedule tip");
            ab.setMessage("In this work schedule you fill in the number of hours you work each day");
            ab.setCancelable(true);
            ab.show();


        });
        register.setText("Apply");
        register.setOnClickListener(v -> {
            if(!validateForm(v)) return;

            Log.e("Bionic", "User before: " + user.toString());
            String[] jobsName = {"Driver", "Operator"};
            List<Integer> jobs = new ArrayList<Integer>();
            if(driver.isChecked())jobs.add(0);
            if(operator.isChecked())jobs.add(1);
            user.setJobs(jobs);

            if(zero_day_contract.isChecked()){
                user.setZeroHours(true);
                user.setWorkSchedule(null);
            }


            else {
                if(!contract_days.getText().toString().isEmpty())
                    user.setContractHours(Integer.parseInt(contract_days.getText().toString()));
                user.setZeroHours(false);
                WorkSchedule schedule = user.getWorkSchedule();
                if(schedule == null) schedule = new WorkSchedule();
                schedule.setCreationTime(new Date());
                schedule.setMonday(monday.getText().toString());
                schedule.setTuesday(tuesday.getText().toString());
                schedule.setWednesday(wednesday.getText().toString());
                schedule.setThursday(thursday.getText().toString());
                schedule.setFriday(friday.getText().toString());
                schedule.setSaturday(saturday.getText().toString());
                schedule.setSunday(sunday.getText().toString());
                user.setWorkSchedule(schedule);
            }
            if(four_week_payments.isChecked())user.setFourWeekPayOff(true);
            else user.setFourWeekPayOff(false);

            if(mandatoryTvt.isChecked())user.setTvt(220);
            else {
                int newtvt = 0;
                if(tvtHours.getText().toString().isEmpty())newtvt = 0;
                else newtvt = Integer.parseInt(tvtHours.getText().toString());
                if(newtvt > 0)user.setTvt(newtvt);
            }

            if(paidTvt.isChecked())user.setPaidTimeForTime(true);
            else user.setPaidTimeForTime(false);

            Log.e("Bionic",user.toString());

//            new UpdateWorkInfo(user,getView()).execute();



        });

    }

    public boolean validateForm(View layout) {

        boolean checkbox = (driver.isChecked() || operator.isChecked())
                && ( (day_contract.isChecked() && !contract_days.getText().toString().isEmpty()) || zero_day_contract.isChecked())
                && (mounthly_payments.isChecked() || four_week_payments.isChecked());

        if(!checkbox){
            Snackbar.make(layout, "Check necessary checkboxes", Snackbar.LENGTH_LONG).show();
            return false;
        }

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

        if(!mandatoryTvt.isChecked()){
            if(tvtHours.getText().toString().isEmpty()){
                Snackbar.make(layout,"Input Custom tvt",Snackbar.LENGTH_LONG).show();
                return false;
            }
            if(Integer.parseInt(tvtHours.getText().toString()) <= 0){
                Snackbar.make(layout,"Tvt must be positive",Snackbar.LENGTH_LONG).show();
                return false;
            }

        }
        return true;

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


    private void checkboxBehaviour(){

        day_contract.setOnClickListener(v -> {
            if(day_contract.isChecked()) {
                contract_days.setEnabled(true);
                scheduleBlock.setVisibility(View.VISIBLE);
            }
            else contract_days.setEnabled(false);
            afterTextChanged(null);
        });

        zero_day_contract.setOnClickListener(v -> {
            if (zero_day_contract.isChecked()) {
                contract_days.setEnabled(false);
                scheduleBlock.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
            }
        });


        mandatoryTvt.setOnClickListener(v -> tvtHours.setEnabled(false));
        voluntarilyTvt.setOnClickListener(v -> tvtHours.setEnabled(true));

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


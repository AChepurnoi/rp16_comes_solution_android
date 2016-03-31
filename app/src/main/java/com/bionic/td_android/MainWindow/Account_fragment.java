package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bionic.td_android.Entity.Job;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EmailValidator;
import com.bionic.td_android.Utility.EntitySaver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 18.03.2016.
 */
public class Account_fragment extends Fragment {


    interface IScheduleFactory {
        public View getUserSchedule(User user);
    }

    class ZeroContract implements IScheduleFactory {


        @Override
        public View getUserSchedule(User user) {
            TextView text = new TextView(getContext());
            text.setText("You have 0 hour contract. Schedule unavailable.");
            text.setGravity(Gravity.CENTER);
            text.setTextSize(16f);
            return text;
        }
    }

    class HourContract implements IScheduleFactory {

        private View getViewForDay(String day, String schedule){

            TextView text = new TextView(getContext());
            text.setTextSize(16f);
            text.setGravity(Gravity.CENTER);
            text.setText(day + ": " + schedule + " ");
            return text;


        }


        @Override
        public View getUserSchedule(User user) {

            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            layout.setLayoutParams(params);
            WorkSchedule schedule = user.getWorkSchedule();
            layout.addView(getViewForDay("Monday",schedule.getMonday()));

            layout.addView(getViewForDay("Tuesday",schedule.getTuesday()));

            layout.addView(getViewForDay("Wednesday",schedule.getWednesday()));
            layout.addView(getViewForDay("Thursday",schedule.getThursday()));
            layout.addView(getViewForDay("Friday",schedule.getFriday()));
            layout.addView(getViewForDay("Saturday",schedule.getSaturday()));
            layout.addView(getViewForDay("Sunday",schedule.getSunday()));

            return layout;
        }


    }

    public static class PersonalInformation_fragment extends Fragment{


        public static class ChangePassword_fragment extends Fragment{


            private EditText tmpPass,newPass,repeatPass;
            private MainActivity activity;
            private Toolbar toolbar;


            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                View view = inflater.inflate(R.layout.fragment_temp_pass,container,false);
                configure(view);
                return view;
            }

            private void configure(View view){
                activity = (MainActivity) getActivity();
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
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        String tmp = tmpPass.getText().toString();
                        String newPassword = newPass.getText().toString();
                        String repeatPassword = repeatPass.getText().toString();
                        if (validate(tmp, newPassword, repeatPassword)){
                            //Changing password
                        }
                        else Snackbar.make(view, "Check password matching", Snackbar.LENGTH_LONG).show();

                    }
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

        private Toolbar toolbar;
        private MainActivity activity;
        private EditText name,lastname,insertion,postalCode,email;
        private Spinner gender;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
            configurePage(view);
            return view;
        }

        private void configureToolbar(View view){

            toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setTitle("Personal information");

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


        private void configurePage(View view){
            activity = (MainActivity) getActivity();
            configureToolbar(view);
            configureViews(view);


        }

        private boolean validate(){

            if(name.getText().toString().isEmpty() || lastname.getText().toString().isEmpty()
                    || email.getText().toString().isEmpty() )return false;

            EmailValidator validator = new EmailValidator();
            if(validator.validate(email.getText().toString()) == false) return false;


//            @TODO add postal code validation


            return true;
        }

        private void configureViews(View view){
            name = (EditText) view.findViewById(R.id.input_name);
            lastname = (EditText)view.findViewById(R.id.input_surname);
            insertion = (EditText)view.findViewById(R.id.input_second_name);
            postalCode = (EditText)view.findViewById(R.id.input_code_area);
            email = (EditText)view.findViewById(R.id.input_email);
            gender = (Spinner) view.findViewById(R.id.input_gender);

            User user = EntitySaver.getUser();

            name.setText(user.getFirstName());
            lastname.setText(user.getLastName());
            insertion.setText(user.getInsertion());
            if(user.getSex().equals("Male"))gender.setSelection(0);
            else gender.setSelection(1);

            email.setText(user.getEmail());

            TextView change_password = (TextView) view.findViewById(R.id.button_change_password);
            change_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.change_password();
                }
            });

            Button save = (Button) view.findViewById(R.id.button_save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }



    }


    public static class WorkInformation_fragment extends Fragment implements TextWatcher{


        private MainActivity activity;
        private Toolbar toolbar;
        private CheckBox driver,operator;
        private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
        private CheckBox day_contract, zero_day_contract;
        private EditText contract_days;
        private CheckBox mounthly_payments, four_week_payments;
        private View scheduleBlock;
        private View button_help;
        private TextView error;


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
            User user = EntitySaver.getUser();

            List<Job> jobs = user.getJobs();
            for (Job job : jobs) {
                if(job.getJobName().equals("Driver"))driver.setChecked(true);
                if(job.getJobName().equals("Operator"))operator.setChecked(true);
            }
            if(user.isFourWeekPayOff())four_week_payments.setChecked(true);
            else mounthly_payments.setChecked(true);

            if(user.isZeroHours()){
                zero_day_contract.setChecked(true);
                scheduleBlock.setVisibility(View.GONE);


            }else {
                day_contract.setChecked(true);
                contract_days.setText(user.getContractHours());
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

            day_contract = (CheckBox) view.findViewById(R.id.checkbox_day_contract);
            zero_day_contract = (CheckBox) view.findViewById(R.id.checkbox_zero_contract);
            contract_days = (EditText) view.findViewById(R.id.input_contract_days);
            mounthly_payments = (CheckBox) view.findViewById(R.id.checkbox_mounth_payments);
            four_week_payments = (CheckBox) view.findViewById(R.id.checkbox_four_week_payments);
            scheduleBlock = view.findViewById(R.id.block_schedule);
            error = (TextView) view.findViewById(R.id.error_hours);
            Button register = (Button)view.findViewById(R.id.button_register);
            button_help = view.findViewById(R.id.button_help);

            button_help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());
                    ab.setTitle("Schedule tip");
                    ab.setMessage("In this work schedule you fill in the number of hours you work each day");
                    ab.setCancelable(true);
                    ab.show();


                }
            });
            register.setText("Apply");
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

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
            List<Job> jobs = new ArrayList<>();
            if(driver.isChecked()){
                Job tmp = new Job();
                tmp.setJobName("Driver");
                jobs.add(tmp);
            }
            if(operator.isChecked()){
                Job tmp = new Job();
                tmp.setJobName("Operator");
                jobs.add(tmp);
            }
            user.setJobs(jobs);
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

            day_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(day_contract.isChecked())contract_days.setEnabled(true);
                    else contract_days.setEnabled(false);
                    zero_day_contract.setChecked(false);

                    scheduleBlock.setVisibility(View.VISIBLE);

                }
            });

            zero_day_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(zero_day_contract.isChecked()){
                        day_contract.setChecked(false);
                        contract_days.setEnabled(false);
                    }

                    scheduleBlock.setVisibility(View.GONE);
                }
            });


            four_week_payments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mounthly_payments.isChecked())mounthly_payments.setChecked(false);
                }
            });

            mounthly_payments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(four_week_payments.isChecked())four_week_payments.setChecked(false);

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

    }


    private MainActivity activity;
    private Toolbar toolbar;

    private TextView name,sex,email,pos_1,pos_2,payment,contract,personalInfo,workInfo;
    private User user;
    private LinearLayout scheduleContainer;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_info, container, false);
        configurePage(view);
        return view;
    }


    private void configurePage(View view){

        activity = (MainActivity)getActivity();
        configureToolbar(view);
        configureViews(view);


    }



    private void configureViews(View view){

        name = (TextView) view.findViewById(R.id.view_name);
        sex = (TextView) view.findViewById(R.id.view_sex);
        email = (TextView) view.findViewById(R.id.view_email);
        pos_1 = (TextView) view.findViewById(R.id.view_position_1);
        pos_2 = (TextView) view.findViewById(R.id.view_position_2);
        payment = (TextView) view.findViewById(R.id.view_payment);
        contract = (TextView) view.findViewById(R.id.view_contract_type);
        scheduleContainer = (LinearLayout) view.findViewById(R.id.working_schedule_list);
        pos_1.setVisibility(View.INVISIBLE);
        pos_2.setVisibility(View.INVISIBLE);
        personalInfo = (TextView) view.findViewById(R.id.button_personal_info);
        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.personal_information();
            }
        });

        workInfo = (TextView) view.findViewById(R.id.button_work_info);
        workInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.work_information();
            }
        });



        configUser();
    }

    public void setUser(User user){
        this.user = user;
    }

    private void configUser(){
        user = EntitySaver.getUser();
        name.setText(user.getFirstName() + " " + user.getInsertion() + " " + user.getLastName());
        sex.setText(user.getSex());
        email.setText(user.getEmail());
        List<Job> list = user.getJobs();
        TextView arr[] = {pos_1,pos_2};
        for(int i = 0; i < list.size(); i++){
            arr[i].setText(list.get(i).getJobName());
            arr[i].setVisibility(View.VISIBLE);
        }
        if(user.isFourWeekPayOff())
            payment.setText("Four weeks payment");
        else payment.setText("Mounthly payments");

        if(user.isZeroHours())
            contract.setText("0-h contract");
        else contract.setText(user.getContractHours() + "-h contract");
        IScheduleFactory scheduleFactory = null;
        if(user.isZeroHours())scheduleFactory = new ZeroContract();
        else scheduleFactory = new HourContract();
        scheduleContainer.addView(scheduleFactory.getUserSchedule(user));

    }

    private void configureToolbar(View view){

        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Account info");

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

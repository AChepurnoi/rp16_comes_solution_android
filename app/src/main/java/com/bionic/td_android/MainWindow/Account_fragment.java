package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bionic.td_android.Entity.Job;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;
import com.bionic.td_android.R;

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

    private MainActivity activity;
    private Toolbar toolbar;

    private TextView name,sex,email,pos_1,pos_2,payment,contract;
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

        configUser();
    }

    public void setUser(User user){
        this.user = user;
    }

    private void configUser(){
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

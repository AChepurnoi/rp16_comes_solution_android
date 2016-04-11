package com.bionic.td_android.Entity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bionic.td_android.R;
import com.bionic.td_android.Utility.DatePickerFragment;
import com.bionic.td_android.Utility.PauseEditor;
import com.bionic.td_android.Utility.TimePickerFragment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 09.04.2016.
 */
public class Shift extends SugarRecord implements TextWatcher{

    @JsonProperty("id")
    private Long mId;
    private Date startTime;
    private Date endTime;
    @JsonIgnore
    private User user;

    private long pauseTime;
    private List<Ride> rides;
    public Shift() {
        rides = new ArrayList<>();
    }
    @JsonProperty("id")
    public Long getmId() {
        return mId;
    }
    @JsonProperty("id")
    public void setmId(Long id) {
        this.mId = id;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public long getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(long pauseTime) {
        this.pauseTime = pauseTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    @Override
    public List<Field> getTableFields() {
        return super.getTableFields();
    }

    @JsonIgnore
    @Override
    public String getSqlName() {
        return super.getSqlName();
    }



    @JsonIgnore
    private TextView startDatefield;
    @JsonIgnore
    private TextView startTimefield;
    @JsonIgnore
    private LinearLayout ridesContainer;
    @JsonIgnore
    private TextView endDatefiled;
    @JsonIgnore
    private TextView endTimefield;

    @JsonIgnore
    private TextView totalPausefield;
    @JsonIgnore
    private TextView editTotalPause;
    @JsonIgnore
    private Button save,saveAndExit;

    @JsonIgnore
    private FragmentManager manager;

    @JsonIgnore
    private View view;

    @JsonIgnore
    public View getView() {
        return view;
    }

    private void bindViews(){
        final Shift shift = this;

        totalPausefield = (TextView) view.findViewById(R.id.block_pause_total);

        startDatefield = (TextView) view.findViewById(R.id.block_begin_shift_date);
        startDatefield.addTextChangedListener(this);
        startDatefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment(startDatefield, shift).show(manager, "StartDatePick");
            }
        });
        startTimefield = (TextView) view.findViewById(R.id.block_begin_shift_time);
        startTimefield.addTextChangedListener(this);
        startTimefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerFragment(startTimefield, shift).show(manager, "StartDatePick");
            }
        });

        endDatefiled = (TextView) view.findViewById(R.id.block_end_shift_date);
        endDatefiled.addTextChangedListener(this);
        endDatefiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment(endDatefiled, shift).show(manager, "EndDatePick");
            }
        });
        endTimefield = (TextView) view.findViewById(R.id.block_end_shift_time);
        endTimefield.addTextChangedListener(this);
        endTimefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerFragment(endTimefield,shift).show(manager,"EndTimePick");
            }
        });


        editTotalPause = (TextView) view.findViewById(R.id.button_edit_pause);
        editTotalPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PauseEditor(shift).show(manager,"Edit pause");
            }
        });
        ridesContainer = (LinearLayout) view.findViewById(R.id.rides_container);


        TextView add_ride = (TextView) view.findViewById(R.id.button_add_new_ride);
        add_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rides.size() > 0 ? rides.get(rides.size() - 1).validate(view) : true) {
                    Ride ride = new Ride();
                    ride.setShift(shift);
                    rides.add(ride);
                    View rideView = ride.getViewPresentation(v.getContext(), manager);
                    ridesContainer.addView(rideView);
                    afterTextChanged(null);
                }
            }
        });

    }

    public void deleteView(View deleting,Ride ride){
        ridesContainer.removeView(deleting);
        rides.remove(ride);
        afterTextChanged(null);

    }

    @JsonIgnore
    public View getShiftView(LayoutInflater inflater,ViewGroup parent,FragmentManager manager){
        this.manager = manager;
        view = inflater.inflate(R.layout.fragment_shift_page,parent,false);
        bindViews();

        return view;


    }


    public boolean validate(){


        if(startTime == null || endTime == null){
            Snackbar.make(view, "Input start date and end date", Snackbar.LENGTH_LONG).show();
            return false;

        }
        if(startTime.after(endTime)){
            Snackbar.make(view, "Start date is after end date", Snackbar.LENGTH_LONG).show();
            return false;
        }

        for (Ride ride : rides) {
            if(!ride.validate(view))
                return false;
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(endTime == null || startTime == null)return;
        long interval = endTime.getTime() - startTime.getTime();
        long workingTime = 0;

        for (Ride ride : rides) {
            if(ride.getEndTime() == null || ride.getStartTime() == null)return;
            workingTime += ride.getWorkTime();
        }

        long diff = interval - workingTime;
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        pauseTime = diff;
        totalPausefield.setText(diffHours + "h " + diffMinutes + "m");
    }

    public boolean setCustomPauseTime(String time){

        //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//the above commented line was changed to the one below, as per Grodriguez's pertinent comment:

        try {
            if(!time.matches("^(\\d+:[0-59]{2})$")){
                return false;
            }
            String[] vals = time.split(":");
            long hours = Long.parseLong(vals[0]);
            long mins = Long.parseLong(vals[1]);
            totalPausefield.setText(hours + "h " + mins + "m");
            pauseTime = (hours * 3600000) + (mins * 60000);
            Log.e("Bionic","Setted pause time: " + pauseTime);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public String toString() {
        return "Shift{" +
                "mId=" + mId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                ", rides=" + rides +
                ", pause=" + pauseTime +
                '}';
    }
}


package com.bionic.td_android.Entity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bionic.td_android.R;
import com.bionic.td_android.Utility.BreakCalculator;
import com.bionic.td_android.Utility.PauseEditor;
import com.bionic.td_android.Utility.ShiftDatePicker;
import com.bionic.td_android.Utility.ShiftTimePicker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by user on 09.04.2016.
 */
public class Shift implements TextWatcher{

    @JsonProperty("id")
    private Long mId;
    private Date startTime;
    private Date endTime;

    @JsonIgnore
    private User user;



    private long pause;
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

    public long getPause() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause = pause;
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
    private FragmentManager manager;

    @JsonIgnore
    private View view;

    @JsonIgnore
    public View getView() {
        return view;
    }


    public void addFirstRide(){
        Ride ride = new Ride();
        ride.setShift(this);
        rides.add(ride);
        View rideView = ride.getViewPresentation(view.getContext(), manager);
        ridesContainer.addView(rideView);
    }
    private void bindViews(){
        final Shift shift = this;

        totalPausefield = (TextView) view.findViewById(R.id.block_pause_total);

        startDatefield = (TextView) view.findViewById(R.id.block_begin_shift_date);
        startDatefield.addTextChangedListener(this);

        startDatefield.setOnClickListener(v -> new ShiftDatePicker(startDatefield, shift).show(manager, "StartDatePick"));
        startTimefield = (TextView) view.findViewById(R.id.block_begin_shift_time);
        startTimefield.addTextChangedListener(this);

        startTimefield.setOnClickListener(v -> new ShiftTimePicker(startTimefield, shift).show(manager, "StartDatePick"));

        endDatefiled = (TextView) view.findViewById(R.id.block_end_shift_date);
        endDatefiled.setOnClickListener(v -> new ShiftDatePicker(endDatefiled, shift).show(manager, "EndDatePick"));
        endTimefield = (TextView) view.findViewById(R.id.block_end_shift_time);
        endTimefield.setOnClickListener(v -> new ShiftTimePicker(endTimefield, shift).show(manager, "EndTimePick"));


        editTotalPause = (TextView) view.findViewById(R.id.button_edit_pause);
        editTotalPause.setOnClickListener(v -> new PauseEditor(shift).show(manager,"Edit pause"));
        ridesContainer = (LinearLayout) view.findViewById(R.id.rides_container);

        addFirstRide();
        TextView add_ride = (TextView) view.findViewById(R.id.button_add_new_ride);
        add_ride.setOnClickListener(v -> {
            if(rides.get(rides.size() - 1).validate(view)) {
                Ride ride = new Ride();
                ride.setShift(shift);
                rides.add(ride);
                View rideView = ride.getViewPresentation(v.getContext(), manager);
                ridesContainer.addView(rideView);
                afterTextChanged(null);
            }
        });



    }

    public void deleteView(View deleting,Ride ride){
        if(rides.size() == 1)return;
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

    public boolean checkOverlays(){
        for (Ride ride : rides) {
            for (Ride ride1 : rides) {
                if(ride != ride1){
                    if(ride.getStartTime().after(ride1.getStartTime())
                            && ride.getEndTime().before(ride1.getEndTime()))return true;

                    if(ride.getStartTime().before(ride1.getStartTime())
                            && ride.getEndTime().after(ride1.getStartTime())
                            && ride.getEndTime().before(ride1.getEndTime()))return true;

                    if(ride.getStartTime().before(ride1.getEndTime())
                            && ride.getStartTime().after(ride1.getStartTime())
                            && ride.getEndTime().after(ride1.getEndTime()))return true;
                }
            }
        }
        return false;
    }


    public boolean checkBounds(){

        for (Ride ride : rides) {
            if(startTime!= null && endTime!= null) {
                if (ride.getStartTime().before(startTime) || ride.getStartTime().after(endTime)
                        || ride.getEndTime().before(startTime) || ride.getEndTime().after(endTime))
                    return false;
            }
            else return false;
        }

        return true;
    }

    public boolean checkRides(){
        for (Ride ride : rides) {
            if(!ride.validate(view))return false;
        }
        return true;
    }
    public boolean validate(){


        if(!checkRides()){
            Snackbar.make(view, "Rides are incorrect", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(checkOverlays()){
            Snackbar.make(view, "Rides overlaying", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(!checkBounds()){
            Snackbar.make(view, "Wrong rides bounds", Snackbar.LENGTH_LONG).show();
            return false;

        }

        if(startTime == null){
            Snackbar.make(view, "Input start date", Snackbar.LENGTH_LONG).show();
            return false;

        }

        if(endTime!= null)
            if(startTime.after(endTime)){
                Snackbar.make(view, "Start date is after end date", Snackbar.LENGTH_LONG).show();
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

    private Date findLastTimeOfRide(){
        Date end = new Date(0);
        for (Ride ride : rides) {
            if(ride.getEndTime().after(end))end = new Date(ride.getEndTime().getTime());
        }
        return end;
    }

    public void calculateEndTime(){
        Date date = findLastTimeOfRide();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        StringBuilder text = new StringBuilder();
        if (cal.get(Calendar.HOUR_OF_DAY) < 10)
            text.append("0");
        text.append(cal.get(Calendar.HOUR_OF_DAY)).append(":");
        if (cal.get(Calendar.MINUTE) < 10)
            text.append("0");
        text.append(cal.get(Calendar.MINUTE));
        endTimefield.setText(text);


        text = new StringBuilder();
        if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            text.append("0");
        text.append(cal.get(Calendar.DAY_OF_MONTH)).append("/");
        if (cal.get(Calendar.MONTH) + 1 < 10)
            text.append("0");
        text.append(cal.get(Calendar.MONTH) + 1).append("/").append(cal.get(Calendar.YEAR));
        endDatefiled.setText(text);


        endTime = date;




    }



    @Override
    public void afterTextChanged(Editable s) {


        boolean valid = checkRides();
        if(valid)calculateEndTime();

        if(validate()){
            BreakCalculator counter = new BreakCalculator(this);
            long pauseMinutes = counter.calculate();
            totalPausefield.setText(pauseMinutes / 60 + "h " + pauseMinutes % 60 +"m");
            pause = pauseMinutes;
        };



    }

    public void recountPause(){
        if(validate()){
            BreakCalculator counter = new BreakCalculator(this);
            long pauseMinutes = counter.calculate();
            totalPausefield.setText(pauseMinutes / 60 + "h " + pauseMinutes % 60 +"m");
            pause = pauseMinutes;
        };

    }

    public boolean setCustomPauseTime(String time){
        try {
            if(!time.matches("^(\\d+:[0-59]{2})$")){
                return false;
            }
            String[] vals = time.split(":");
            long hours = Long.parseLong(vals[0]);
            long mins = Long.parseLong(vals[1]);
            totalPausefield.setText(hours + "h " + mins + "m");
            pause = (hours * 3600000) + (mins * 60000);
            Log.e("Bionic","Setted pause time: " + pause);
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
                ", pause=" + pause +
                '}';
    }
}


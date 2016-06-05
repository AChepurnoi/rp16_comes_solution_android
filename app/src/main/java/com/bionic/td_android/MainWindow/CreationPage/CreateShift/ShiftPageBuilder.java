package com.bionic.td_android.MainWindow.CreationPage.CreateShift;

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

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.BreakCalculator;
import com.bionic.td_android.Utility.DateUtility;
import com.bionic.td_android.Utility.PauseEditor;
import com.bionic.td_android.Utility.ShiftDatePicker;
import com.bionic.td_android.Utility.ShiftTimePicker;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 12.05.2016.
 */
public class ShiftPageBuilder implements TextWatcher{

    private Shift shift;
    private List<RideViewBuilder> rideBuilders;
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


    public ShiftPageBuilder(Shift shift) {
        this.shift = shift;
        rideBuilders = new ArrayList<>();
    }

    public void addFirstRide(){
        Ride ride = new Ride();
        ride.setShift(shift);
        shift.getRides().add(ride);
        RideViewBuilder rideBuilder = new RideViewBuilder(ride,this);
        rideBuilders.add(rideBuilder);
        View rideView = rideBuilder.getViewPresentation(view.getContext(), manager);
        ridesContainer.addView(rideView);
    }
    private void bindViews(){


        totalPausefield = (TextView) view.findViewById(R.id.block_pause_total);

        startDatefield = (TextView) view.findViewById(R.id.block_begin_shift_date);


        startDatefield.setOnClickListener(v -> new ShiftDatePicker(startDatefield, this).show(manager, "StartDatePick"));
        startTimefield = (TextView) view.findViewById(R.id.block_begin_shift_time);


        startTimefield.setOnClickListener(v -> new ShiftTimePicker(startTimefield, this).show(manager, "StartDatePick"));

        endDatefiled = (TextView) view.findViewById(R.id.block_end_shift_date);
        endDatefiled.setOnClickListener(v -> new ShiftDatePicker(endDatefiled, this).show(manager, "EndDatePick"));
        endTimefield = (TextView) view.findViewById(R.id.block_end_shift_time);
        endTimefield.setOnClickListener(v -> new ShiftTimePicker(endTimefield, this).show(manager, "EndTimePick"));


        editTotalPause = (TextView) view.findViewById(R.id.button_edit_pause);
        editTotalPause.setOnClickListener(v -> new PauseEditor(this).show(manager, "Edit pause"));
        ridesContainer = (LinearLayout) view.findViewById(R.id.rides_container);

        if(shift.getRides().size() == 0)addFirstRide();
        TextView add_ride = (TextView) view.findViewById(R.id.button_add_new_ride);
        add_ride.setOnClickListener(v -> {
            if (rideBuilders.get(shift.getRides().size() - 1).validate(view)) {
                Ride ride = new Ride();
                ride.setShift(shift);
                shift.getRides().add(ride);
                RideViewBuilder rideBuilder = new RideViewBuilder(ride, this);
                rideBuilders.add(rideBuilder);
                View rideView = rideBuilder.getViewPresentation(view.getContext(), manager);

                ridesContainer.addView(rideView);
                afterTextChanged(null);
            }
        });



    }

    public void attachListeners(){
        startDatefield.addTextChangedListener(this);
        startTimefield.addTextChangedListener(this);
    }

    public void deleteView(View deleting,Ride ride){
        if(shift.getRides().size() == 1)return;
        ridesContainer.removeView(deleting);
        shift.getRides().remove(ride);
        deleteBuilder(ride);
        afterTextChanged(null);

    }

    private void deleteBuilder(Ride ride){
        rideBuilders = Stream.of(rideBuilders).filterNot( b ->(b.getRide() == ride)).collect(Collectors.toList());
    }
    @JsonIgnore
    public View getShiftView(LayoutInflater inflater,ViewGroup parent,FragmentManager manager, int layout){
        this.manager = manager;
        view = inflater.inflate(layout,parent,false);
        bindViews();
        if(shift.getStartTime() != null && shift.getEndTime() != null)populateView();
        attachListeners();
        return view;
    }

    public void populateView(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(shift.getStartTime() );
        startDatefield.setText(DateUtility.getDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
        startTimefield.setText(DateUtility.getTime(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE)));
        cal.setTime(shift.getEndTime());
        endDatefiled.setText(DateUtility.getDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
        endTimefield.setText(DateUtility.getTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
        totalPausefield.setText(shift.getPause() / 3600000 + "h " + ((shift.getPause() / 60000) % 60) + "m");

        for (Ride ride : shift.getRides()) {
            RideViewBuilder rideBuilder = new RideViewBuilder(ride, this);
            rideBuilders.add(rideBuilder);
            View rideView = rideBuilder.getViewPresentation(view.getContext(), manager);
            ridesContainer.addView(rideView);
        }
    }

    public boolean checkOverlays(){


        for (Ride ride : shift.getRides()) {
            for (Ride ride1 : shift.getRides()) {
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
        for (Ride ride : shift.getRides()) {
            if(shift.getStartTime()!= null && shift.getEndTime()!= null) {
                if (ride.getStartTime().before(shift.getStartTime()) || ride.getStartTime().after(shift.getEndTime())
                        || ride.getEndTime().before(shift.getStartTime()) || ride.getEndTime().after(shift.getEndTime()))
                    return false;
            }
            else return false;
        }
        return true;
    }

    public boolean checkRides(){
        for (RideViewBuilder ride : rideBuilders) {
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
        if(shift.getStartTime() == null){
            Snackbar.make(view, "Input start date", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if(shift.getEndTime()!= null)
            if(shift.getStartTime().after(shift.getEndTime())){
                Snackbar.make(view, "Start date is after end date", Snackbar.LENGTH_LONG).show();
                return false;
            }

        if(!checkDate()){
            Snackbar.make(view, "You cant add future shifts", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkDate(){
        if(shift.getEndTime().after(new Date()))return false;
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    private Date findLastTimeOfRide(){
        Date end = new Date(0);
        for (Ride ride : shift.getRides()) {
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
        if(endDatefiled != null)endTimefield.setText(text);
        text = new StringBuilder();
        if (cal.get(Calendar.DAY_OF_MONTH) < 10)
            text.append("0");
        text.append(cal.get(Calendar.DAY_OF_MONTH)).append("/");
        if (cal.get(Calendar.MONTH) + 1 < 10)
            text.append("0");
        text.append(cal.get(Calendar.MONTH) + 1).append("/").append(cal.get(Calendar.YEAR));
        if(endDatefiled != null)endDatefiled.setText(text);
        shift.setEndTime(date);
    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean valid = checkRides();
        if(valid)calculateEndTime();
        if(validate()){
            BreakCalculator counter = new BreakCalculator(shift);
            long pauseMili = counter.calculate();
            totalPausefield.setText(pauseMili / 3600000 + "h " + ((pauseMili / 60000) % 60) + "m");
            shift.setPause(pauseMili);
        };
    }

    public void recountPause(){
        if(validate()){
            BreakCalculator counter = new BreakCalculator(shift);
            long pauseMili = counter.calculate();
            if(totalPausefield != null)totalPausefield.setText(pauseMili / 3600000 + "h " + ((pauseMili / 60000) % 60)  +"m");
            shift.setPause(pauseMili);
        }
    }

    public boolean setCustomPauseTime(String time){
        try {
            if(!time.matches("^(\\d+:[0-59]{2})$")) return false;
            String[] vals = time.split(":");
            long hours = Long.parseLong(vals[0]);
            long mins = Long.parseLong(vals[1]);
            totalPausefield.setText(hours + "h " + mins + "m");
            shift.setPause((hours * 3600000) + (mins * 60000));
            Log.e("Bionic", "Setted pause time: " + shift.getPause());
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

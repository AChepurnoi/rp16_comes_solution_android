package com.bionic.td_android.Entity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bionic.td_android.R;
import com.bionic.td_android.Utility.TimePack;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;

public class Ride{


    @JsonProperty("id")
    private Long mId;
    private Date startTime;
    private Date endTime;

    @JsonIgnore
    private Shift shift;

    public Ride() {
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

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
    @JsonIgnore
    private FragmentManager manager;

    @JsonIgnore
    private View view;


    @JsonIgnore
    private TimePack startHour;

    @JsonIgnore
    private TimePack endHour;
    @JsonIgnore
    private Date selectedDate;

    @JsonIgnore
    private TextView datefield;
    @JsonIgnore
    private TextView fromTimefield;
    @JsonIgnore
    private TextView endTimefield;

    @JsonIgnore
    public View getView(){return view;}
    @JsonIgnore
    public View getViewPresentation(Context context,FragmentManager manager){
        this.manager = manager;
        view = LayoutInflater.from(context).inflate(R.layout.view_add_ride,null);
        configView();
        return view;
    }




    private void configView(){
        final Ride ride = this;
        startHour = new TimePack();
        endHour = new TimePack();
        datefield = (TextView) view.findViewById(R.id.item_begin_date);
        datefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment(datefield,ride).show(manager,"");
            }
        });
        fromTimefield = (TextView) view.findViewById(R.id.item_begin_time);
        fromTimefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedDate != null)
                    new TimePickerFragment(fromTimefield,ride).show(manager,"StartTime");
                else  Snackbar.make(ride.getView(), "Select date first", Snackbar.LENGTH_LONG).show();
            }
        });
        endTimefield = (TextView) view.findViewById(R.id.item_end_time);
        endTimefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startHour.isValid())
                    new TimePickerFragment(endTimefield,ride).show(manager,"EndTime");
                else  Snackbar.make(ride.getView(), "Input start time first", Snackbar.LENGTH_LONG).show();
            }
        });

        ImageButton del = (ImageButton) view.findViewById(R.id.button_delete_ride);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shift.deleteView(view,ride);
            }
        });

    }

    public void invalidateEndTime(){
        endHour.invalidate();
        endTime = null;
        endTimefield.setText("00:00");
    }

    public void invalidateStartTime(){
        startHour.invalidate();
        startTime = null;
        fromTimefield.setText("00:00");
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public TextView textView;
        private Ride ride;

        public TimePickerFragment() {

        }
        public TimePickerFragment(TextView textView,Ride ride) {
            this.textView = textView;
            this.ride = ride;
        }

        @Override
        public Dialog onCreateDialog(Bundle saveInstanceState) {
            final Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);


            return new TimePickerDialog(getActivity(), this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StringBuilder text = new StringBuilder();
            if (hourOfDay < 10)
                text.append("0");
            text.append(hourOfDay).append(":");
            if (minute < 10)
                text.append("0");
            text.append(minute);


            if(getTag().contains("Start")) {

                ride.startHour.hours = hourOfDay;
                ride.startHour.minutes = minute;
                textView.setText(text);
                ride.invalidateEndTime();
                Log.e("Bionic", "Ride date: " + ride.startHour.toString());

            }

            if (getTag().contains("End")){

                ride.endHour.hours = hourOfDay;
                ride.endHour.minutes = minute;
                textView.setText(text);
                ride.invokeAutoCountings();
                Log.e("Bionic", "Ride end date: " + ride.endHour.toString());
            }
        }
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        private TextView textView;
        private Ride ride;

        public DatePickerFragment() {

        }
        public DatePickerFragment(TextView textView,Ride ride) {
            this.textView = textView;
            this.ride = ride;
        }

        @Override
        public Dialog onCreateDialog(Bundle saveInstanceState) {
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            StringBuilder text = new StringBuilder();
            if (dayOfMonth < 10)
                text.append("0");
            text.append(dayOfMonth).append("/");
            if (monthOfYear + 1 < 10)
                text.append("0");
            text.append(monthOfYear + 1).append("/").append(year);

            Calendar cal = Calendar.getInstance();
            cal.set(year,monthOfYear,dayOfMonth, 0, 0 ,0);

            cal.set(Calendar.MILLISECOND,0);

            ride.selectedDate = cal.getTime();
            textView.setText(text);
            ride.invalidateStartTime();
            ride.invalidateEndTime();

        }
    }


    public boolean validate(View view){


        if(!startHour.isValid() || !endHour.isValid()) return false;

        createDates();

        if(startTime == null || endTime == null){
            Snackbar.make(view,"Input time in rides" , Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void createDates(){
        Date start = new Date(selectedDate.getTime() + startHour.getLongFromDaystart());
        startTime = start;
        Date end = null;
        Date nextSelectedDay = new Date(selectedDate.getTime() + 86400000l);
        if(endHour.hours < startHour.hours || (endHour.hours == startHour.hours && endHour.minutes < startHour.minutes))
            end = new Date(nextSelectedDay.getTime() + endHour.getLongFromDaystart() );
        else end = new Date(selectedDate.getTime() + endHour.getLongFromDaystart() );


        endTime = end;

    }



    public void invokeAutoCountings(){
        shift.afterTextChanged(null);
    }
    public long getWorkTime(){
        return endTime.getTime() - startTime.getTime();
    }

    @Override
    public String toString() {
        return "Ride{" +
                "endTime=" + endTime +
                ", mId=" + mId +
                ", startTime=" + startTime +
                '}';
    }
}

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Ride extends SugarRecord {


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
    @Override
    public List<Field> getTableFields() {
        return super.getTableFields();
    }

    @JsonIgnore
    private FragmentManager manager;

    @JsonIgnore
    private View view;


    @JsonIgnore
    @Override
    public String getSqlName() {
        return super.getSqlName();
    }

    @JsonIgnore
    public View getView(){return view;}
    @JsonIgnore
    public View getViewPresentation(Context context,FragmentManager manager){
        this.manager = manager;
        view = LayoutInflater.from(context).inflate(R.layout.view_add_ride,null);
        configView();
        return view;
    }


    private TextView datefield;
    private TextView fromTimefield;
    private TextView endTimefield;

    private void configView(){
        final Ride ride = this;
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
                new TimePickerFragment(fromTimefield,ride).show(manager,"StartTime");

            }
        });
        endTimefield = (TextView) view.findViewById(R.id.item_end_time);
        endTimefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerFragment(endTimefield,ride).show(manager,"EndTime");
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
                Calendar calendar = GregorianCalendar.getInstance();
                Date date = ride.getStartTime();
                if(date == null){
                    Snackbar.make(ride.getView(), "Please input date first", Snackbar.LENGTH_LONG).show();
                    return;
                }
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                textView.setText(text);
                ride.setStartTime(calendar.getTime());
                Log.e("Bionic", "Ride date: " + ride.getStartTime().toString());
            }

            if (getTag().contains("End")){

                Calendar calendar = GregorianCalendar.getInstance();
                Date date = ride.getEndTime();
                if(date == null){
                    Snackbar.make(ride.getView(),"Please input date first",Snackbar.LENGTH_LONG).show();
                    return;
                }
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                textView.setText(text);
                ride.setEndTime(calendar.getTime());
                Log.e("Bionic", "Ride end date: " + ride.getEndTime().toString());
            }
            ride.invokeCountingPause();
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
            textView.setText(text);

            Calendar calendar = GregorianCalendar.getInstance();
            Date date = ride.getStartTime();
            if(date != null){
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                Calendar tmp = GregorianCalendar.getInstance();
                tmp.setTime(date);

                calendar.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
                calendar.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
            }
            else calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND,0);
            ride.setStartTime(calendar.getTime());
            Log.e("Bionic", "Ride  date: " + ride.getStartTime().toString());
            Calendar calendar2 = GregorianCalendar.getInstance();
            if(date != null){
                calendar2.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                Calendar tmp = GregorianCalendar.getInstance();
                tmp.setTime(date);

                calendar2.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
                calendar2.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
            }else calendar2.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
            calendar2.set(Calendar.MILLISECOND,0);
            ride.setEndTime(calendar.getTime());
            Log.e("Bionic", "Ride  date: " + ride.getEndTime().toString());
            ride.invokeCountingPause();
        }
    }


    public boolean validate(View view){


        if(startTime == null || endTime == null){
            Snackbar.make(view,"Input time in rides" , Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(startTime.after(endTime)){
            Snackbar.make(view,"Start time of ride is after end time" , Snackbar.LENGTH_LONG).show();
            return false;
        }

        if( startTime.after(shift.getEndTime())
            || startTime.before(shift.getStartTime())
            || endTime.after(shift.getEndTime())
            || endTime.before(shift.getStartTime())){
            Snackbar.make(view,"Start time of ride outside of Shift period" , Snackbar.LENGTH_LONG).show();
            return false;

        }

        return true;
    }




    public void invokeCountingPause(){
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

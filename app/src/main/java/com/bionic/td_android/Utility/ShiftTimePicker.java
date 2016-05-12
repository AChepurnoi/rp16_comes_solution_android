package com.bionic.td_android.Utility;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bionic.td_android.MainWindow.CreateShift.ShiftPageBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 09.04.2016.
 */


public class ShiftTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    public TextView textView;
    private ShiftPageBuilder shift;

    public ShiftTimePicker() {

    }
    public ShiftTimePicker(TextView textView, ShiftPageBuilder shift) {
        this.textView = textView;
        this.shift = shift;
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
            Date date = shift.getShift().getStartTime();
            if(date == null){
                Snackbar.make(shift.getView(),"Please input date first",Snackbar.LENGTH_LONG).show();
                return;
            }
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            textView.setText(text);
            shift.getShift().setStartTime(calendar.getTime());
            Log.e("Bionic", "Shift date: " + shift.getShift().getStartTime().toString());
        }

        if (getTag().contains("End")){

            Calendar calendar = GregorianCalendar.getInstance();
            Date date = shift.getShift().getEndTime();
            if(date == null){
                Snackbar.make(shift.getView(),"Please input date first",Snackbar.LENGTH_LONG).show();
                return;
            }
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            textView.setText(text);
            shift.getShift().setEndTime(calendar.getTime());
            Log.e("Bionic", "Shift end date: " + shift.getShift().getEndTime().toString());
        }
        shift.recountPause();
    }
}

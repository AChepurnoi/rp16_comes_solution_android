package com.bionic.td_android.MainWindow.CreationPage.Daytypes.Utility;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.IDayType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Granium on 02.06.16.
 */
public class DTTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private TextView textView;
    private IDayType dateSettable;
    private boolean eightHours;

    public DTTimePicker() {

    }
    public DTTimePicker(TextView textView, IDayType dateSettable, boolean eightHours) {
        this.textView = textView;
        this.dateSettable = dateSettable;
        this.eightHours = eightHours;
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
        if(getTag().contains("Start")) {
            Calendar calendar = GregorianCalendar.getInstance();
            Date date = dateSettable.getStartDate();
            if(date == null){
                Snackbar.make(textView,"Please input date first",Snackbar.LENGTH_LONG).show();
                return;
            }
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateSettable.setStartDate(calendar.getTime());
            Log.e("Bionic", "Daytype  date: " + dateSettable.getStartDate().toString());
            if(eightHours){
                Date date1 = dateSettable.getStartDate();
                date1 = new Date(date1.getTime() + (1000 * 60 * 60 * 8));
                dateSettable.setEndDate(date1);
            }
        }

        if (getTag().contains("End")){
            Calendar calendar = GregorianCalendar.getInstance();
            Date date = dateSettable.getStartDate();
            if(date == null){
                Snackbar.make(textView,"Please input date first",Snackbar.LENGTH_LONG).show();
                return;
            }
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            dateSettable.setEndDate(calendar.getTime());
            Log.e("Bionic", "Daytype end date: " + dateSettable.getEndDate().toString());
        }

    }
}

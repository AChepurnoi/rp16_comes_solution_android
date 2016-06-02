package com.bionic.td_android.MainWindow.CreationPage.Daytypes.Utility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.IDayType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Granium on 02.06.16.
 */
public class DTDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private TextView textView;
    private IDayType dateSettable;
    private boolean eightHours;

    public DTDatePicker() {

    }

    public DTDatePicker(TextView textView, IDayType dateSettable, boolean eightHours) {
        this.textView = textView;
        this.dateSettable = dateSettable;
        this.eightHours = eightHours;
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

        if(getTag().contains("Start")) {
            Calendar calendar = GregorianCalendar.getInstance();
            Date date = dateSettable.getStartDate();
            if(date != null){
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                calendar.set(Calendar.MILLISECOND,0);
                Calendar tmp = GregorianCalendar.getInstance();
                tmp.setTime(date);

                calendar.add(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
                calendar.add(Calendar.HOUR_OF_DAY,tmp.get(Calendar.HOUR_OF_DAY));

            }
            else calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
            calendar.set(Calendar.MILLISECOND,0);
            dateSettable.setStartDate(calendar.getTime());
            Log.e("Bionic", "Daytype date: " + dateSettable.getStartDate().toString());

        }
//        if(getTag().contains("End")){
//
//            Calendar calendar = GregorianCalendar.getInstance();
//            Date date = dateSettable.getStartDate();
//            if(date != null){
//                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
//                Calendar tmp = GregorianCalendar.getInstance();
//                tmp.setTime(date);
//                calendar.add(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
//                calendar.add(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
//
//            }else calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
//            calendar.set(Calendar.MILLISECOND,0);
//            dateSettable.setEndDate(calendar.getTime());
//            Log.e("Bionic", "DateType date: " + dateSettable.getEndDate().toString());
//        }
    }
}
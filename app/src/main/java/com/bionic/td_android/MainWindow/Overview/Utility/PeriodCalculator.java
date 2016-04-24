package com.bionic.td_android.MainWindow.Overview.Utility;

import android.support.v4.util.Pair;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 23.04.2016.
 */
public class PeriodCalculator {

    public Pair<Date, Date> getPeriodWeeks(int year,int startweek, int weeks){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 0, 0, 0, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.WEEK_OF_YEAR, startweek);
        Date start = calendar.getTime();
        Log.e("Bionic", start.toString());

        calendar.add(Calendar.WEEK_OF_YEAR, weeks);
        Date end = calendar.getTime();
        Log.e("Bionic", end.toString());
        return Pair.create(start,end);

    }

    public Pair<Date,Date> getPeriodMonths(int year, int month){

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        Date start = calendar.getTime();
        Log.e("Bionic", start.toString());
        calendar.add(Calendar.MONTH, 1);
        Date end = calendar.getTime();
        Log.e("Bionic", end.toString());

        return Pair.create(start,end);

    }
}

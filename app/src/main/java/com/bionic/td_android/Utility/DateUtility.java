package com.bionic.td_android.Utility;

/**
 * Created by user on 18.04.2016.
 */
public class DateUtility {

    private DateUtility(){

    }
    public static StringBuilder getDate(int year, int monthOfYear, int dayOfMonth){
        StringBuilder text = new StringBuilder();
        if (dayOfMonth < 10)
            text.append("0");
        text.append(dayOfMonth).append("/");
        if (monthOfYear + 1 < 10)
            text.append("0");
        text.append(monthOfYear + 1).append("/").append(year);
        return text;
    }

    public static StringBuilder getTime(int hourOfDay, int minute){
        StringBuilder text = new StringBuilder();
        if (hourOfDay < 10)
            text.append("0");
        text.append(hourOfDay).append(":");
        if (minute < 10)
            text.append("0");
        text.append(minute);
        return text;
    }


}

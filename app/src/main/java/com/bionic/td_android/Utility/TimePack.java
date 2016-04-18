package com.bionic.td_android.Utility;

/**
 * Created by user on 17.04.2016.
 */
public class TimePack {

    public int hours;
    public int minutes;

    public TimePack() {
        hours = -1;
        minutes = -1;
    }

    public TimePack(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public boolean isValid(){
        return  hours!= - 1 && minutes != -1;
    }

    public void invalidate(){
        hours = -1;
        minutes = -1;
    }

    public long getLongFromDaystart(){
        return hours * 3600000 + minutes * 60000;
    }

    @Override
    public String toString() {
        return "TimePack{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}

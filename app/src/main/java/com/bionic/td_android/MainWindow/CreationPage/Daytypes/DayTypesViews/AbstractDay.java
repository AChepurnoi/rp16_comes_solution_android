package com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bionic.td_android.MainWindow.CreationPage.Daytypes.Utility.DTDatePicker;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.Utility.DTTimePicker;
import com.bionic.td_android.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Granium on 02.06.16.
 */
public class AbstractDay implements IDayType {

    private DayType dayType;
    private TextView date, startTime,endTime;
    private View view = null;
    private boolean eightHours;
    private DayType.DayTypeEnum type;


    public AbstractDay(boolean eH, DayType.DayTypeEnum t) {
        type = t;
        eightHours = eH;
        dayType = new DayType();
        dayType.setDayTypeName(type);
    }


    @Override
    public View getViewPresentation(AppCompatActivity activity) {
        if(view == null)view = LayoutInflater.from(activity).inflate(R.layout.view_add_daytype,null);
        date = (TextView) view.findViewById(R.id.item_begin_date);
        startTime = (TextView) view.findViewById(R.id.item_begin_time);
        endTime = (TextView) view.findViewById(R.id.item_end_time);
        date.setOnClickListener((v) -> new DTDatePicker(date,this,eightHours).show(activity.getSupportFragmentManager(),"Start"));
        startTime.setOnClickListener( v-> new DTTimePicker(startTime,this,0).show(activity.getSupportFragmentManager(),"Start"));

        if(type == DayType.DayTypeEnum.HOLIDAY) {
            endTime.setOnClickListener(v -> new DTTimePicker(startTime, this, 0).show(activity.getSupportFragmentManager(), "End"));
        }else endTime.setVisibility(View.INVISIBLE);
        return view;
    }
    public DayType getDayType() {
        return dayType;
    }
    @Override
    public Date getStartDate() {


        return dayType.getStartTime();

    }

    @Override
    public Date getEndDate() {
        return dayType.getEndTime();
    }

    @Override
    public void setStartDate(Date date) {
        dayType.setStartTime(date);
        updateDate();
        updateTime();

    }

    @Override
    public void setEndDate(Date date) {
        dayType.setEndTime(date);
        updateDate();
        updateTime();
    }

    private void updateTime(){
        Calendar calendar = Calendar.getInstance();
        if (getStartDate() == null)return;
        calendar.setTime(getStartDate());
        StringBuilder text = new StringBuilder();
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10)
            text.append("0");
        text.append(calendar.get(Calendar.HOUR_OF_DAY) ).append(":");
        if (calendar.get(Calendar.MINUTE) < 10)
            text.append("0");
        text.append(calendar.get(Calendar.MINUTE) );
        startTime.setText(text);
        text = new StringBuilder();
        if(getEndDate() == null)return;
        calendar.setTime(getEndDate());
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10)
            text.append("0");
        text.append(calendar.get(Calendar.HOUR_OF_DAY) ).append(":");
        if (calendar.get(Calendar.MINUTE) < 10)
            text.append("0");
        text.append(calendar.get(Calendar.MINUTE) );
        endTime.setText(text);


    }

    private void updateDate(){
        Calendar calendar = Calendar.getInstance();
        if(getStartDate() == null)return;
        calendar.setTime(getStartDate());
        StringBuilder text = new StringBuilder();
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10)
            text.append("0");
        text.append(calendar.get(Calendar.DAY_OF_MONTH)).append("/");
        if (calendar.get(Calendar.MONTH) + 1 < 10)
            text.append("0");
        text.append(calendar.get(Calendar.MONTH) + 1).append("/").append(calendar.get(Calendar.YEAR));
        date.setText(text);
    }


}

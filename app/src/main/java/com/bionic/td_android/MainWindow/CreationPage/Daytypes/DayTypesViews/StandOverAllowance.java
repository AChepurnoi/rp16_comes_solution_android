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
public class StandOverAllowance implements IDayType {


    private DayType dayType;
    private TextView date, startTime,endTime;
    private View view = null;

    public StandOverAllowance() {
        dayType = new DayType();
        dayType.setDayTypeName(DayType.DayTypeEnum.STAND_OVER_ALLOWANCE);
    }


    @Override
    public View getViewPresentation(AppCompatActivity activity) {
        if(view == null)view = LayoutInflater.from(activity).inflate(R.layout.view_add_daytype,null);
        date = (TextView) view.findViewById(R.id.item_begin_date);
        startTime = (TextView) view.findViewById(R.id.item_begin_time);
        startTime.setVisibility(View.GONE);
        endTime = (TextView) view.findViewById(R.id.item_end_time);
        TextView tmp = (TextView) view.findViewById(R.id.text_from);
        tmp.setVisibility(View.GONE);
        tmp = (TextView) view.findViewById(R.id.text_to);
        tmp.setVisibility(View.GONE);

        endTime.setVisibility(View.GONE);
        date.setOnClickListener((v) -> new DTDatePicker(date,this,true).show(activity.getSupportFragmentManager(),"Start"));
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
        dayType.setEndTime(date);
        updateDate();

    }

    @Override
    public void setEndDate(Date date) {
        dayType.setEndTime(date);
        updateDate();
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

package com.bionic.td_android.MainWindow.CreationPage.Daytypes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.annimon.stream.Exceptional;
import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.AbstractDay;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.DayType;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.IDayType;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.StandOverAllowance;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.AddDayType;
import com.bionic.td_android.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Granium on 02.06.16.
 */
public class DayTypeFragment extends Fragment {

    private LinearLayout viewContainer;
    private IDayType dayType;
    private View view;
    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmnet_daytypes_input,container,false);
        viewContainer = (LinearLayout) view.findViewById(R.id.daytype_container);
        DbManager manager = new DbManager(view.getContext());
        user = manager.loadUser();

        configurePage();

        return view;
    }

    public void configurePage(){
        Button save = (Button) view.findViewById(R.id.button_save);
        save.setOnClickListener(v -> onSave());

        Spinner spinner = (Spinner) view.findViewById(R.id.daytype_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayType = getDT(position);
                viewContainer.removeAllViews();
                viewContainer.addView(dayType.getViewPresentation((AppCompatActivity) getActivity()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void onSave(){
        DayType day = dayType.getDayType();
        Log.e("Bionic", day.toString());
        if(hasErrors()) {Snackbar.make(view,"Wrong input",Snackbar.LENGTH_LONG).show();return;}
//        sending to server;

        if(user.isZeroHours() && !(day.getDayTypeName() == DayType.DayTypeEnum.HOLIDAY || day.getDayTypeName() == DayType.DayTypeEnum.STAND_OVER_ALLOWANCE)){
            Snackbar.make(view,"For zero hours you can only add Holiday/Stand over Allowance",Snackbar.LENGTH_LONG).show();
            return;
        }


//        if(day.getDayTypeName() == DayType.DayTypeEnum.CONSIGNMENT_FEE)
//            if(checkConsFeeErr()){Snackbar.make(view,"Consignment fee day can only be a maximum of 8 hours",Snackbar.LENGTH_LONG).show();return;}


        if(!user.isZeroHours() && day.getDayTypeName() != DayType.DayTypeEnum.HOLIDAY){
            WorkSchedule schedule = new DbManager(getContext()).loadUser().getWorkSchedule();
            int hrs = getHoursFromDay(schedule,day.getStartTime());
            if(hrs == 0){
                Snackbar.make(view,"You have 0 hours schedule for this day",Snackbar.LENGTH_LONG).show();
                return;
            }
            day.setEndTime(new Date(day.getStartTime().getTime() + 1000 * 60 * 60 * hrs));

        }

        if(day.getDayTypeName() == DayType.DayTypeEnum.HOLIDAY){
            if(day.getEndTime() == null || day.getStartTime().before(day.getEndTime())){
                Snackbar.make(view,"Wrong time bounds",Snackbar.LENGTH_LONG).show();
                return;
            }
        }
        Log.e("Bionic", day.toString());
        new AddDayType(day,view, (MainActivity) getActivity()).execute();

    }

    private int getHoursFromDay(WorkSchedule schedule, Date date){
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek){
            case Calendar.MONDAY:
                if(schedule.getMonday() == null)return 0;
                else return Integer.parseInt(schedule.getMonday());
            case Calendar.TUESDAY:
                if(schedule.getTuesday() == null)return 0;
                else return Integer.parseInt(schedule.getTuesday());
            case Calendar.WEDNESDAY:
                if(schedule.getWednesday() == null)return 0;
                else return Integer.parseInt(schedule.getWednesday());
            case Calendar.THURSDAY:
                if(schedule.getThursday() == null)return 0;
                else return Integer.parseInt(schedule.getThursday());
            case Calendar.FRIDAY:
                if(schedule.getFriday() == null)return 0;
                else return Integer.parseInt(schedule.getFriday());
            case Calendar.SATURDAY:
                if(schedule.getSaturday() == null)return 0;
                else return Integer.parseInt(schedule.getSaturday());
            case Calendar.SUNDAY:
                if(schedule.getSunday() == null)return 0;
                else return Integer.parseInt(schedule.getSunday());

        }

        return 0;

    }

    private boolean checkConsFeeErr(){
        DayType day = dayType.getDayType();
        Calendar from = Calendar.getInstance();
        from.setTime(day.getStartTime());
        Calendar to = Calendar.getInstance();
        to.setTime(day.getEndTime());

        if(to.getTimeInMillis() - from.getTimeInMillis() > 1000*60*60*8)return true;

        return false;

        //consignment fee day can only be a maximum of 8 hours,
    }
    private boolean hasErrors(){
        DayType day = dayType.getDayType();
        if(day.getStartTime() == null)return true;
        return false;
    }

    public IDayType getDT(int pos){
        switch (pos){
            case 0:
                return new AbstractDay(true, DayType.DayTypeEnum.SICK_DAY);
            case 1:
                return new AbstractDay(true, DayType.DayTypeEnum.HOLIDAY);
            case 2:
                return new AbstractDay(true, DayType.DayTypeEnum.ATV_DAY);
            case 3:
                return new AbstractDay(false, DayType.DayTypeEnum.PAID_LEAVE_OF_ABSENCE);
            case 4:
                return new AbstractDay(false, DayType.DayTypeEnum.NON_PAID_LEAVE_OF_ABSENCE);
            case 5:
                return new AbstractDay(false, DayType.DayTypeEnum.STAND_TIME);
            case 6:
                return new AbstractDay(false, DayType.DayTypeEnum.CONSIGNMENT_FEE);
            case 7:
                return new StandOverAllowance();
            case 8:
                return new AbstractDay(true, DayType.DayTypeEnum.TIME_FOR_TIME_DAY);
            default:return null;
        }
    }
}

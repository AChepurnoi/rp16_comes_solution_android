package com.bionic.td_android.MainWindow;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bionic.td_android.R;

import java.util.Calendar;



/**
 * Created by Евгений on 03/28/16.
 */
public class Shift_fragment extends Fragment {
    private MainActivity activity;
    private Toolbar toolbar;

    private TextView shiftFromDate;
    private TextView shiftToDate;
    private TextView shiftFromTime;
    private TextView shiftToTime;
    private TextView shiftPause;

    private TextView driveFromDate;
    private TextView driveToDate;
    private TextView driveFromTime;
    private TextView driveToTime;
    private TextView drivePause;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shift_page, container, false);
        configurePage(view);
        return view;
    }

    private void configurePage(View view) {
        activity = (MainActivity) getActivity();
        // configureToolbar(view);
        configureViews(view);
    }

    private void configureViews(View view) {
        shiftFromDate = (TextView) view.findViewById(R.id.block_shift_info_from_date);
        shiftFromTime = (TextView) view.findViewById(R.id.block_shift_info_from_time);
        shiftToDate = (TextView) view.findViewById(R.id.block_shift_info_to_date);
        shiftToTime = (TextView) view.findViewById(R.id.block_shift_info_to_time);
        shiftPause =  (TextView) view.findViewById(R.id.block_shift_info_pauze);

        driveFromDate = (TextView) view.findViewById(R.id.block_drive_info_from_date);
        driveFromTime = (TextView) view.findViewById(R.id.block_drive_info_from_time);
        driveToDate = (TextView) view.findViewById(R.id.block_drive_info_to_date);
        driveToTime = (TextView) view.findViewById(R.id.block_drive_info_to_time);
        drivePause =  (TextView) view.findViewById(R.id.block_drive_info_pauze);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        shiftFromDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        shiftFromTime.setText(new StringBuilder().append(hour).append(":").append(minute));
        shiftToDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        shiftToTime.setText(new StringBuilder().append(hour).append(":").append(minute));

        driveFromDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        driveFromTime.setText(new StringBuilder().append(hour).append(":").append(minute));
        driveToDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        driveToTime.setText(new StringBuilder().append(hour).append(":").append(minute));

        shiftFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialogFragment(shiftFromDate).show(activity.getSupportFragmentManager(), "datePicker");
            }
        });
        shiftFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(shiftFromTime).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });
        shiftToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialogFragment(shiftToDate).show(activity.getSupportFragmentManager(), "datePicker");
            }
        });
        shiftToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(shiftToTime).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });
        shiftPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(shiftPause).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });

        driveFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialogFragment(driveFromDate).show(activity.getSupportFragmentManager(), "datePicker");
            }
        });
        driveFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(driveFromTime).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });
        driveToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialogFragment(driveToDate).show(activity.getSupportFragmentManager(), "datePicker");
            }
        });
        driveToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(driveToTime).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });
        drivePause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialogFragment(drivePause).show(activity.getSupportFragmentManager(), "timePicker");
            }
        });





    }

    private void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Create shift");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                activity.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public TextView textView;

        public DatePickerDialogFragment() {

        }
        public DatePickerDialogFragment(TextView textView) {
            this.textView = textView;
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
        }
    }

    public static class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public TextView textView;

        public TimePickerDialogFragment() {

        }
        public TimePickerDialogFragment(TextView textView) {
            this.textView = textView;
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
            textView.setText(text);
        }
    }
}

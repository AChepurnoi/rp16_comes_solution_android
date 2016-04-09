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
        configureToolbar(view);
        configureViews(view);
    }

    private void configureViews(View view) {

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

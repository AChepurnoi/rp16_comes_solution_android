package com.bionic.td_android.MainWindow.Overview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.MainWindow.Overview.Utility.ShiftsAdapter;
import com.bionic.td_android.MainWindow.Overview.Utility.WorkingWeekDTO;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.DateUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.04.2016.
 */
public class SelectedPeriod extends Fragment {

    private View view;
    private MainActivity activity;
    private List<WorkingWeekDTO> list;

    private int paired = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_watch_period,container,false);
        configureToolbar();
        configureView();
        return view;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Period Overview");

    }


    private void parse(){
        String json = getArguments().getString("json");
        try {
            list = new ObjectMapper().readValue(json, new TypeReference<List<WorkingWeekDTO>>(){});
        } catch (IOException e) {
            Log.e("Bionic","Error parsing");
        }
    }


    private void configureView(){

        parse();
        TextView textView = (TextView) view.findViewById(R.id.period_text);
        textView.setText("Overview for: " + getArguments().getString("period"));
        LinearLayout listView = (LinearLayout) view.findViewById(R.id.periods_list);
        listView.addView(formView("Week", "Contract time", "Overtime"));
        Spinner spinner = (Spinner) view.findViewById(R.id.shifts_spinner);
        List<Shift> shifts =  new ArrayList<>();
        Stream.of(list)
                .peek(dto -> shifts.addAll(dto.getShiftList()))
                .map(value -> new String[]{String.valueOf(value.getWeekNumber()),
                        DateUtility.getHourMinutes(value.getContractTime()),
                        DateUtility.getHourMinutes(value.getOverTime())})
                .map(value1 -> formView(value1[0], value1[1], value1[2]))
                .forEach(listView::addView);


        Shift[] arr = Stream.of(shifts)
                .sorted((lhs, rhs) -> lhs.getStartTime().compareTo(rhs.getStartTime()))
                .toArray(Shift[]::new);
        List<Shift> tmp = Stream.of(arr).collect(Collectors.toList());
        tmp.add(new Shift());
        Shift[] array = Stream.of(tmp).toArray(Shift[]::new);

        ShiftsAdapter adapter = new ShiftsAdapter(getContext(),array);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Bionic", "selected item " + position + "Count " + adapter.getCount() );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("Bionic", "Nothing selected");
            }
        });




    }

    private View formView(String week, String contract, String overtime) {

        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_period_table_row, null, false);
        TextView weekText = (TextView)convertView.findViewById(R.id.view_week);
        TextView contractText = (TextView)convertView.findViewById(R.id.view_contract_hours);
        TextView overtimeText = (TextView)convertView.findViewById(R.id.view_overtime);
        weekText.setText(week);
        contractText.setText(contract);
        overtimeText.setText(overtime);
        if(paired++ % 2 != 0)convertView.setBackgroundColor(Color.GRAY);
        return convertView;
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




}
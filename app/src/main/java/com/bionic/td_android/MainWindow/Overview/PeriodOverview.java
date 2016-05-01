package com.bionic.td_android.MainWindow.Overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.GetPeriodData;
import com.bionic.td_android.R;

/**
 * Created by user on 23.04.2016.
 */
public class PeriodOverview extends Fragment {

    private int year;
    private MainActivity activity;

    public PeriodOverview() {
        super();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            year = getArguments().getInt("year");
        }
        activity = (MainActivity) getActivity();
    }

    private String[] periods = {"Week: 1-4"," Week: 5-8","Week: 9-12","Week: 13-16","Week: 17-20","Week: 21-24",
            "Week: 25-28","Week: 29-32","Week: 33-36","Week: 37-40","Week: 41-44","Week: 45-48","Week: 49-52"};
    private String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_period_overview,container,false);
        configureView();
        return view;

    }


    private void configureView(){
        User user = new DbManager(getContext()).loadUser();
        ListView list = (ListView) view.findViewById(R.id.periods_list);
        String[] array ;
        if(user.isFourWeekPayOff())array = periods;
        else array = months;
        ArrayAdapter<String> periodsAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,array);
        list.setAdapter(periodsAdapter);
        list.setOnItemClickListener((parent, view1, position, id) -> {
            new GetPeriodData(view1,year,id + 1, (MainActivity) getActivity()).execute();

        });

    }
}

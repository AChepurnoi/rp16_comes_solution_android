package com.bionic.td_android.MainWindow.Overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.R;

/**
 * Created by user on 23.04.2016.
 */
public class SelectedPeriod extends Fragment {

    private View view;

    private MainActivity activity;

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
        return view;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Overview");

    }


    private void configureView(){

        TextView textView = (TextView) view.findViewById(R.id.period_text);

        ListView list = (ListView) view.findViewById(R.id.periods_list);

        list.setOnItemClickListener((parent, view1, position, id) -> {


        });

    }




}

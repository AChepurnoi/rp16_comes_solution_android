package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.R;

import java.util.List;


/**
 * Created by Евгений on 03/28/16.
 */
public class Shift_fragment extends Fragment {
    private MainActivity activity;
    private Toolbar toolbar;

    private Shift shift;
    private List<Ride> rides;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        shift = new Shift();
        View view = shift.getShiftView(inflater,container,getActivity().getSupportFragmentManager());

        configurePage(view);
        return view;
    }

    private void configurePage(View view) {
        activity = (MainActivity) getActivity();
        configureToolbar(view);
        configureViews(view);
    }

    private void configureViews(View view) {


        Button apply = (Button) view.findViewById(R.id.button_apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shift.validate())saveShift();
            }
        });

        Button submit = (Button) view.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shift.validate())saveShift();
            }
        });

    }

    private void saveShift(){
        Log.e("Bionic",shift.toString());
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




}

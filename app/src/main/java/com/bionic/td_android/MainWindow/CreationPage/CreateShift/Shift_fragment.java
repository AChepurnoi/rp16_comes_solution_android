package com.bionic.td_android.MainWindow.CreationPage.CreateShift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.AddShift;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.BreakCalculator;


/**
 * Created by Евгений on 03/28/16.
 */
public class Shift_fragment extends Fragment {
    private MainActivity activity;
    private Toolbar toolbar;
    private DbManager manager;
    private Shift shift;
    private ShiftPageBuilder pageBuilder;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        manager = new DbManager(getContext());
        shift = manager.loadLocalShift();
        if(shift == null)shift = new Shift();
        pageBuilder = new ShiftPageBuilder(shift);
        View view = pageBuilder.getShiftView(inflater, container, getActivity().getSupportFragmentManager(),R.layout.fragment_shift_page);
//        View view = shift.getShiftView(inflater,container,getActivity().getSupportFragmentManager());

        configurePage(view);
        return view;
    }

    private void configurePage(View view) {
        activity = (MainActivity) getActivity();
//        configureToolbar(view);
        configureViews(view);
    }

    private void configureViews(View view) {


        Button apply = (Button) view.findViewById(R.id.button_apply);
        apply.setOnClickListener(v -> {
            Log.e("Bionic", shift.toString());
            if(pageBuilder.validate()){
                Log.e("Bionic","SHIFT OK");
                Log.e("Bionic", shift.toString());
                Log.e("Bionic", "Total breaktime seconds (Calculated)" + new BreakCalculator(shift).calculate());
                Log.e("Bionic", "Setted pause :" + shift.getPause());
//                manager.clearLocalShift();
                manager.saveUpdateLocalShift(shift);
                Snackbar.make(view, "Shift has been saved", Snackbar.LENGTH_LONG).show();
                activity.onBackPressed();
            }

        });


        Button submit = (Button) view.findViewById(R.id.button_submit);
        submit.setOnClickListener(v -> {
            Log.e("Bionic", shift.toString());
            if(pageBuilder.validate()){
                Log.e("Bionic","SHIFT OK");
                Log.e("Bionic", shift.toString());
                Log.e("Bionic","Total breaktime minutes " + new BreakCalculator(shift).calculate());
                new AddShift(shift,v,activity).execute();
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

//        switch (item.getItemId()) {
//            case android.R.id.home:
//                activity.onBackPressed();
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }




}

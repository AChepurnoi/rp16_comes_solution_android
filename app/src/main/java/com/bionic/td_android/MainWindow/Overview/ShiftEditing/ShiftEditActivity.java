package com.bionic.td_android.MainWindow.Overview.ShiftEditing;

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

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.MainWindow.CreationPage.CreateShift.ShiftPageBuilder;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.UpdateShift;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.BreakCalculator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by user on 12.05.2016.
 */
public class ShiftEditActivity extends Fragment {

    private ShiftPageBuilder pageBuilder = null;
    private Shift shift = null;
    private DbManager manager = null;
    private MainActivity activity;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            shift = new ObjectMapper().readValue(getArguments().getString("shift"), new TypeReference<Shift>(){});
        } catch (IOException e) {
            Log.e("Bionic", "Error parsing shift");
            activity.onBackPressed();
        }
        manager = new DbManager(activity);
        pageBuilder = new ShiftPageBuilder(shift);
        view = pageBuilder.getShiftView(inflater, container, activity.getSupportFragmentManager(),R.layout.fragment_shift_edit_page);
        configureToolbar();
        configureViews();
        return view;
    }

    private void configureViews() {

        Button apply = (Button) view.findViewById(R.id.button_apply);
        apply.setOnClickListener(v -> {
            Log.e("Bionic", shift.toString());
            if (pageBuilder.validate()) {
                Log.e("Bionic", "SHIFT OK");
                Log.e("Bionic", shift.toString());
                Log.e("Bionic", "Total breaktime seconds (Calculated)" + new BreakCalculator(shift).calculate());
                Log.e("Bionic", "Setted pause :" + shift.getPause());
                new UpdateShift(shift,pageBuilder.getView(),activity).execute();
            }

        });

        Button submit = (Button) view.findViewById(R.id.button_submit);
        submit.setVisibility(View.GONE);

    }


    private void configureToolbar() {
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Edit shift");
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

package com.bionic.td_android.MainWindow.Overview.ShiftEditing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.MainWindow.CreateShift.ShiftPageBuilder;
import com.bionic.td_android.Networking.Requests.UpdateShift;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.BreakCalculator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by user on 12.05.2016.
 */
public class ShiftEditActivity extends AppCompatActivity {

    private ShiftPageBuilder pageBuilder = null;
    private Shift shift = null;
    private DbManager manager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            shift = new ObjectMapper().readValue(getIntent().getExtras().getString("shift"), new TypeReference<Shift>(){});
        } catch (IOException e) {
            Log.e("Bionic", "Error parsing shift");
            onBackPressed();
        }
        manager = new DbManager(getApplicationContext());
        pageBuilder = new ShiftPageBuilder(shift);
        setContentView(pageBuilder.getShiftView(getLayoutInflater(), null, getSupportFragmentManager()));
        configureToolbar();
        configureViews();
    }

    private void configureViews() {

        Button apply = (Button) findViewById(R.id.button_apply);
        apply.setOnClickListener(v -> {
            Log.e("Bionic", shift.toString());
            if (pageBuilder.validate()) {
                Log.e("Bionic", "SHIFT OK");
                Log.e("Bionic", shift.toString());
                Log.e("Bionic", "Total breaktime seconds (Calculated)" + new BreakCalculator(shift).calculate());
                Log.e("Bionic", "Setted pause :" + shift.getPause());
                new UpdateShift(shift,pageBuilder.getView(),this).execute();
            }

        });

        Button submit = (Button) findViewById(R.id.button_submit);
        submit.setVisibility(View.GONE);

    }


    private void configureToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.simple_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit shift");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

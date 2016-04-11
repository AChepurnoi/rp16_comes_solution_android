package com.bionic.td_android.MainWindow;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EntitySaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;


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
                if(shift.validate())saveShift(false);
            }
        });


        Button submit = (Button) view.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shift.validate()){
                    saveShift(true);

                }
            }
        });

    }

    private void saveShift(final boolean navigate){
        Log.e("Bionic",shift.toString());

        User user = EntitySaver.getUser();
        Log.e("Bionic", "Start");
        String url = API.ADD_SHIFT(user.getmId());
        final AlertDialog dialog = new SpotsDialog(getContext(),"Shift processing");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String login = sharedPref.getString("login", "");
        String pass = sharedPref.getString("password", "");

        Log.e("Bionic", "L:" + login + "|P: " + pass + "|id: " + user.getmId());
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        client.addHeader("Authorization", "Basic " + encoded);

        String jsonInString = null;
        try {
            jsonInString = new ObjectMapper().writeValueAsString(shift);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Log.e("Bionic",e.getMessage());
        }
        Log.e("Bionic", jsonInString);
        jsonInString = jsonInString + "kk";
        client.post(getContext(), url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("Bionic", "Fail " + statusCode);
                        Log.e("Bionic", headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();
                        Snackbar.make(getView(), "Failed to add shift", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.e("Bionic", "Success " + statusCode);
                        Log.e("Bionic", headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();
                        Snackbar.make(getView(), "Done", Snackbar.LENGTH_LONG).show();
                        if(navigate) activity.getSupportFragmentManager().popBackStack();
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




}

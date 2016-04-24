package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 23.04.2016.
 */
public class AddShift implements IRequest {


    Shift shift;
    View view;
    MainActivity activity;

    public AddShift(Shift shift, View view, MainActivity activity) {
        this.shift = shift;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void execute() {
        Log.e("Bionic", shift.toString());

        DbManager manager = new DbManager(view.getContext());
        User user = manager.loadUser();
        //@TODO
        Log.e("Bionic", "Start");
        String url = API.SHIFT_API(user.getmId());
        final AlertDialog dialog = new SpotsDialog(view.getContext(),"Shift processing");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
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
        client.post(view.getContext(), url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("Bionic", "Fail " + statusCode);
                        Log.e("Bionic", headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();
                        Snackbar.make(view, "Failed to add shift", Snackbar.LENGTH_LONG).show();
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.e("Bionic", "Success " + statusCode);
                        Log.e("Bionic", headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();
                        Snackbar.make(view, "Done", Snackbar.LENGTH_LONG).show();
                        manager.clearLocalShift();
                        activity.getSupportFragmentManager().popBackStack();
                    }
                });
    }
}

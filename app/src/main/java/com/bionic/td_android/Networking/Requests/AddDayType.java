package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.annimon.stream.Exceptional;
import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews.DayType;
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
 * Created by Granium on 02.06.16.
 */
public class AddDayType implements IRequest {


    private DayType dayType;
    private View view;
    private MainActivity activity;

    public AddDayType(DayType dayType, View view, MainActivity activity) {
        this.dayType = dayType;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void execute() {
        Log.e("Bionic", dayType.toString());

        DbManager manager = new DbManager(view.getContext());
        User user = manager.loadUser();
        Log.e("Bionic", "Start");
        String url = API.addDayType(user.getmId());
        final AlertDialog dialog = new SpotsDialog(view.getContext(),"Adding new day type");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String login = sharedPref.getString("login", "");
        String pass = sharedPref.getString("password", "");
        Log.e("Bionic", "L:" + login + "|P: " + pass + "|id: " + user.getmId());
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        client.addHeader("Authorization", "Basic " + encoded);

        String jsonInString = Exceptional.of( () -> new ObjectMapper().writeValueAsString(dayType)).ifException( (throwable)-> Log.e("Bionic",throwable.getMessage())).get();

        Log.e("Bionic", jsonInString);
        jsonInString = jsonInString + "kk";
        client.post(view.getContext(), url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        switch (statusCode){

                            default:
                                Log.e("Bionic", "Fail " + statusCode);
                                if(responseString != null)
                                    Log.e("Bionic", responseString);
                                dialog.dismiss();
                                Snackbar.make(view, "Failed to add day type", Snackbar.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.e("Bionic", "Success " + statusCode);
                        Log.e("Bionic", headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();
                        Snackbar.make(view, "Day type added successfully", Snackbar.LENGTH_LONG).show();
                        activity.onBackPressed();
                    }
                });
    }
}

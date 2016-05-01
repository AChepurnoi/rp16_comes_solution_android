package com.bionic.td_android.Networking.Requests;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class UpdatePersonalInfo implements IRequest {

    private User user;
    private View view;
    private DbManager manager;
    public UpdatePersonalInfo(User user, View view) {
        this.user = user;
        this.view = view;
        manager = new DbManager(view.getContext());
    }

    @Override
    public void execute() {
        Log.e("Bionic", "User after: " + user.toString());
        final String url = API.UPDATE_USER(user.getmId());
        final android.app.AlertDialog dialog = new SpotsDialog(view.getContext(),"Editing");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String login = sharedPref.getString("login", "");
        String pass = sharedPref.getString("password", "");

        Log.e("Bionic", "L:" +login + "|P: " +pass + "|id: " + user.getmId());
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        client.addHeader("Authorization", "Basic " + encoded);
        String jsonInString = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithType(User.class);
            jsonInString = writer.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            dialog.dismiss();
            Snackbar.make(view, "Parsing error", Snackbar.LENGTH_LONG).show();
        }

        //Опять эта же проблема
        jsonInString = jsonInString + "kk";

        Log.e("Bionic", "JSON: " + jsonInString);
        client.put(view.getContext(), url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        switch (statusCode){
                            case 403:
                                Snackbar.make(view, "This email is already exsists", Snackbar.LENGTH_LONG).show();
                                break;
                            default:
                                Snackbar.make(view, "Error editing profile", Snackbar.LENGTH_LONG).show();
                                break;
                        }
                        dialog.dismiss();
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        dialog.dismiss();
                        Snackbar.make(view, "Changes saved", Snackbar.LENGTH_LONG).show();
                        User user1 = null;
                        try {
                            user1 = new ObjectMapper().readValue(responseString, User.class);
                            manager.update(user1);
                            Log.e("Bionic", user1.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("Bionic", "error parsing");
                        }


                    }
                });
    }
}

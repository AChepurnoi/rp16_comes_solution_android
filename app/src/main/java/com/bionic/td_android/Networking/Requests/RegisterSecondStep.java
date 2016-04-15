package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.bionic.td_android.Register.RegisterActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class RegisterSecondStep implements IRequest {

    User user;
    View view;
    RegisterActivity activity;

    public RegisterSecondStep(User user, View view, RegisterActivity activity) {
        this.user = user;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void execute() {

        Log.e("Bionic", "Start");
        String url = API.REGISTER();

        final AlertDialog dialog = new SpotsDialog(activity,"Registration");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();

        String jsonInString = null;
        try {
            jsonInString = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Log.e("Bionic", "Error " + e.getMessage());
        }
        Log.e("Bionic", jsonInString);
        client.post(activity, url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("Bionic","Fail " + statusCode);
                        Log.e("Bionic",headers.toString());
                        Log.e("Bionic",responseString);
                        dialog.dismiss();
                        Snackbar.make(view, "Failed to register user", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.e("Bionic","Success " + statusCode);
                        Log.e("Bionic",headers.toString());
                        Log.e("Bionic", responseString);
                        dialog.dismiss();

                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Intent.EXTRA_TEXT,"Registration complete. You have been sent a link to verify your account. Please check your email.");
                        activity.startActivity(intent);
                    }
                });

    }
}

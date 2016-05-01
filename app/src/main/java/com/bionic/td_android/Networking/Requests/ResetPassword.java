package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class ResetPassword implements IRequest {

    private LoginActivity activity;
    private String email;
    private View view;


    public ResetPassword(LoginActivity activity, String email, View view) {
        this.activity = activity;
        this.email = email;
        this.view = view;
    }

    @Override
    public void execute() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = API.RESET_PASSWORD();
        final AlertDialog dialog = new SpotsDialog(activity,"Sending email");
        dialog.show();
        client.put(activity, url, new ByteArrayEntity(email.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        dialog.dismiss();
                        Snackbar.make(view, responseString, Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic", "Fail " + responseString);
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        dialog.dismiss();
                        Snackbar.make(view, "mail successfully sent", Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic", responseString);
                        activity.onBackPressed();
                    }
                });
    }
}

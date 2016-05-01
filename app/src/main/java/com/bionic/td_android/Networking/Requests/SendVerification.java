package com.bionic.td_android.Networking.Requests;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class SendVerification implements IRequest {


    private User user;
    private View view;
    private View sendLink;

    public SendVerification(User user, View view, View sendLink) {
        this.user = user;
        this.view = view;
        this.sendLink = sendLink;
    }

    @Override
    public void execute() {


        final String url = API.VERIFICATION(user.getmId());
        final android.app.AlertDialog dialog = new SpotsDialog(view.getContext(), "Sending verification link");
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String login = sharedPref.getString("login", "");
        String pass = sharedPref.getString("password", "");
        Log.e("Bionic", "L:" + login + "|P: " + pass + "|id: " + user.getmId());
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        client.addHeader("Authorization", "Basic " + encoded);
        client.get(view.getContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog.dismiss();
                Snackbar.make(view, "Verification failed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dialog.dismiss();
                Snackbar.make(view, "Verification mail has been sent", Snackbar.LENGTH_LONG).show();
                sendLink.setVisibility(View.GONE);
            }
        });

    }
}

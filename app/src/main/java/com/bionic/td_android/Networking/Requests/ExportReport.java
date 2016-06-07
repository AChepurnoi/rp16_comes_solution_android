package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

/**
 * Created by Granium on 22.05.16.
 */
public class ExportReport implements IRequest {


    private long year, period;
    private View view;

    public ExportReport(View view, long year, long period) {
        this.year = year;
        this.period = period;
        this.view = view;
    }

    @Override
    public void execute() {

        Log.e("Bionic", "Start");

        DbManager manager = new DbManager(view.getContext());
        User user = manager.loadUser();

        String url = API.GETEXCEL(user.getmId(),year, period);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String login = sharedPref.getString("login", "");
        String pass = sharedPref.getString("password", "");

        final AlertDialog dialog = new SpotsDialog(view.getContext(),"Sending report");
        dialog.show();
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        Log.e("Bionic", encoded);
        Log.e("Bionic", year + "Period: " +  period);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization","Basic " + encoded);
        client.get(view.getContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog.dismiss();
                Log.e("Bionic", "Fail " + statusCode + responseString);
                switch (statusCode){


                    default:
                        Snackbar.make(view, "Error sending report", Snackbar.LENGTH_LONG).show();
                        break;
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dialog.dismiss();
                Log.e("Bionic", statusCode + " " + responseString);
                Snackbar.make(view, "Report has been sent ", Snackbar.LENGTH_LONG).show();
            }
        });
    }


}

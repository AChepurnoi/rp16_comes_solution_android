package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.Login.Temporary_pass_fragment;
import com.bionic.td_android.MainWindow.Account.ChangePassword_fragment;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class Login implements IRequest {


    private String login, pass;
    private View view;
    private LoginActivity activity;
    public Login(String login, String pass, LoginActivity activity,View view) {
        this.login = login;
        this.pass = pass;
        this.activity = activity;
        this.view = view;

    }
    @Override
    public void execute() {

        final DbManager manager = new DbManager(activity);
        Log.e("Bionic", "Start");
        String url = API.GET_USER();
        final AlertDialog dialog = new SpotsDialog(activity, "Loging in");
        dialog.show();
        Log.e("Bionic", "Login: " + login + ". Pass: " + pass);
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        Log.e("Bionic", encoded);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Basic " + encoded);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", login);
        editor.putString("password", pass);
        editor.commit();

        client.get(activity, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog.dismiss();
                Log.e("Bionic", "Fail " + statusCode + " " + responseString);
                User user = null;
                switch (statusCode) {
                    case 401:
                        if (responseString.contains("locked")) {
                            Snackbar.make(view, "You have input wrong password 5 times. Your account was blocked for 30 minutes", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(view, "Your login or password is incorrect", Snackbar.LENGTH_LONG).show();
                        }
                        break;

                    case 404:
                        Log.e("Bionic", statusCode + "code " + responseString);
                        Snackbar.make(view, "Server does not respond. Please check your internet connection", Snackbar.LENGTH_LONG).show();
                        break;

                    case 417:
                        Log.e("Bionic", statusCode + "code " + responseString);
                        Snackbar.make(view, "User with this email is not registered yet", Snackbar.LENGTH_LONG).show();
                        break;

                    case 403:
                        Log.e("Bionic", statusCode + "code " + responseString);
                        activity.callFragment(new Temporary_pass_fragment());
                        Snackbar.make(view, "Password expired. Please request new temporary password", Snackbar.LENGTH_LONG).show();
                        break;

                    case 412:
                        try {
                            user = new ObjectMapper().readValue(responseString, User.class);
                            Intent intent = new Intent(activity, MainActivity.class);
                            manager.clear();
                            manager.save(user);
                            activity.startActivity(intent);
                            Log.e("Bionic", user.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("Bionic", "error parsing " + e.getMessage());
                        }
                        break;

                    case 409:
                        try {
                            user = new ObjectMapper().readValue(responseString, User.class);
                            manager.clear();
                            manager.save(user);
                            activity.callFragment(new ChangePassword_fragment());
                            Log.e("Bionic", user.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("Bionic", "error parsing " + e.getMessage());
                        }
                        Snackbar.make(view, "Please change your password", Snackbar.LENGTH_LONG).show();
                        break;

                    default:
                        Snackbar.make(view, "Server does not respond. Please check your internet connection", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString)  {
                dialog.dismiss();
                Log.e("Bionic",responseString);
                User user = null;
                try {
                    user = new ObjectMapper().readValue(responseString, User.class);
                    Intent intent = new Intent(activity, MainActivity.class);
                    manager.clear();
                    manager.save(user);
                    activity.startActivity(intent);
                    Log.e("Bionic", user.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Bionic", "error parsing " + e.getMessage());
                }
            }
        });
    }
}



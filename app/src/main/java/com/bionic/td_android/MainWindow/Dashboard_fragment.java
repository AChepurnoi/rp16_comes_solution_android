package com.bionic.td_android.MainWindow;

import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EntitySaver;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 18.03.2016.
 */
public class Dashboard_fragment extends Fragment {


    private Toolbar toolbar;
    private MainActivity activity;
    private User user;
    private TextView greeting;
    private LinearLayout alert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_dashboard, container, false);
        configurePage(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = EntitySaver.getUser();

    }

    private void configurePage(View view){

        activity = (MainActivity)getActivity();
        user = EntitySaver.getUser();


        configureToolbar(view);
        configureViews(view);

        if(!user.isVerified())alert.setVisibility(View.VISIBLE);
        else alert.setVisibility(View.GONE);

        greeting = (TextView) view.findViewById(R.id.user_name_textfield);
        if(user != null && greeting != null)greeting.setText(user.getFirstName() + " " + user.getLastName());

        Button my_account = (Button)view.findViewById(R.id.button_my_account);
        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.my_account();
            }
        });

        Button new_shift = (Button)view.findViewById(R.id.button_shift);
        new_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.new_shift();
            }
        });

        Button overview = (Button)view.findViewById(R.id.button_overview);
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.overview();
            }
        });

    }

    private void configureViews(View view){

        TextView logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        alert = (LinearLayout) view.findViewById(R.id.verification_alert);
        final TextView sendLink = (TextView) view.findViewById(R.id.button_send_link);
        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String url = API.VERIFICATION(user.getmId());
                final android.app.AlertDialog dialog = new SpotsDialog(getContext(),"Sending verification link");
                dialog.show();
                AsyncHttpClient client = new AsyncHttpClient();
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                String login = sharedPref.getString("login", "");
                String pass = sharedPref.getString("password", "");
                Log.e("Bionic", "L:" + login + "|P: " + pass + "|id: " + user.getmId());
                String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
                client.addHeader("Authorization", "Basic " + encoded);
                client.get(getContext(), url, new TextHttpResponseHandler() {
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                dialog.dismiss();
                                Snackbar.make(getView(), "Verification failed", Snackbar.LENGTH_LONG).show();
                            }
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                dialog.dismiss();
                                Snackbar.make(getView(), "Verification mail has been sent", Snackbar.LENGTH_LONG).show();
                                sendLink.setVisibility(View.GONE);
                            }
                        });

            }
        });


    }

    private void configureToolbar(View view){

        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Dashboard");

    }



}

package com.bionic.td_android.MainWindow;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.MainWindow.Account.Account_fragment;
import com.bionic.td_android.MainWindow.Overview.Overview_fragment;
import com.bionic.td_android.Networking.Requests.SendVerification;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EntitySaver;

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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        configurePage(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = EntitySaver.getUser();

    }

    private void configurePage(View view) {

        activity = (MainActivity) getActivity();
        user = EntitySaver.getUser();


        configureToolbar(view);
        configureViews(view);

        if (!user.isVerified()) alert.setVisibility(View.VISIBLE);
        else alert.setVisibility(View.GONE);

        greeting = (TextView) view.findViewById(R.id.user_name_textfield);
        if (user != null && greeting != null)
            greeting.setText(user.getFirstName() + " " + user.getLastName());

        Button my_account = (Button) view.findViewById(R.id.button_my_account);
        my_account.setOnClickListener(v -> activity.callFragment(new Account_fragment()));

        Button new_shift = (Button) view.findViewById(R.id.button_shift);
        new_shift.setOnClickListener(v -> activity.callFragment(new Shift_fragment()));

        Button overview = (Button) view.findViewById(R.id.button_overview);
        overview.setOnClickListener(v -> activity.callFragment(new Overview_fragment()));

        Button cao = (Button) view.findViewById(R.id.button_cao);
        cao.setOnClickListener(v -> activity.callFragment(new Cao_fragment()));

    }

    private void configureViews(View view) {

        TextView logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        alert = (LinearLayout) view.findViewById(R.id.verification_alert);
        final TextView sendLink = (TextView) view.findViewById(R.id.button_send_link);
        sendLink.setPaintFlags(sendLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sendLink.setOnClickListener(v -> new SendVerification(user,getView(),sendLink).execute());


    }

    private void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Dashboard");

    }


}

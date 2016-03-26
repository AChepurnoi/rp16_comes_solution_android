package com.bionic.td_android.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class Temporary_pass_fragment extends Fragment{


    private EditText tmpPass,newPass,repeatPass;
    private LoginActivity activity;
    private Toolbar toolbar;
    private String email;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_pass,container,false);
        configure(view);
        return view;
    }

    private void configure(View view){
        activity = (LoginActivity) getActivity();
        configureToolbar(view);
        configureViews(view);

    }


    private boolean validate(String tmp,String newPs,String repeatPs){

        if(newPs.equals(repeatPs))return true;
        return false;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void configureViews(final View view){
        tmpPass = (EditText) view.findViewById(R.id.input_temp_password);
        newPass = (EditText) view.findViewById(R.id.input_new_password);
        repeatPass = (EditText) view.findViewById(R.id.input_repeat_password);

        Button button = (Button) view.findViewById(R.id.button_apply_new_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    String tmp = tmpPass.getText().toString();
                    String newPassword = newPass.getText().toString();
                    String repeatPassword = repeatPass.getText().toString();
                    if (validate(tmp, newPassword, repeatPassword))
                        activity.changePassword(tmp,newPassword,email);
                    else Snackbar.make(view, "Check password mathing", Snackbar.LENGTH_LONG).show();

                }catch (Exception e){
                    Log.e("Bionic","Error with DTO object");
                }
            }
        });

    }


    private void configureToolbar(View view){


        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Password Recovery");

    }

}

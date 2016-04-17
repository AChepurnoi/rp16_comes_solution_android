package com.bionic.td_android.MainWindow.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.Requests.UpdatePersonalInfo;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EmailValidator;

/**
 * Created by user on 15.04.2016.
 */

public class PersonalInformation_fragment extends Fragment {
    private Toolbar toolbar;
    private MainActivity activity;
    private EditText name,lastname,insertion,postalCode,email;
    private Spinner gender;
    private User user;
    private DbManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new DbManager(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
        configurePage(view);
        return view;
    }

    private void configureToolbar(View view){

        toolbar = (Toolbar)view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Personal information");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                activity.onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    private void configurePage(View view){
        activity = (MainActivity) getActivity();
        configureToolbar(view);
        configureViews(view);


    }

    private boolean validate(){

        if(name.getText().toString().isEmpty() || lastname.getText().toString().isEmpty()
                || email.getText().toString().isEmpty() ){
            Snackbar.make(getView(),"Fill in necessary forms",Snackbar.LENGTH_LONG).show();
            return false;
        }

        EmailValidator validator = new EmailValidator();
        if(!validator.validate(email.getText().toString())){
            Snackbar.make(getView(),"Invalid email",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(!postalCode.getText().toString().isEmpty() && postalCode.getText().toString().length() > 6){
            Snackbar.make(getView(),"Invalid postal code",Snackbar.LENGTH_LONG).show();
            return false;
        }

//            @TODO add postal code validation


        return true;
    }


    private void updateView(){

        user = manager.loadUser();
        name.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        insertion.setText(user.getInsertion());
        postalCode.setText(user.getPostalCode());
        if(user.getSex().equals("Male"))gender.setSelection(0);
        else gender.setSelection(1);

        email.setText(user.getEmail());

    }
    private void configureViews(final View view){

        user = manager.loadUser();
        name = (EditText) view.findViewById(R.id.input_name);
        lastname = (EditText)view.findViewById(R.id.input_surname);
        insertion = (EditText)view.findViewById(R.id.input_second_name);
        postalCode = (EditText)view.findViewById(R.id.input_code_area);
        email = (EditText)view.findViewById(R.id.input_email);
        gender = (Spinner) view.findViewById(R.id.input_gender);
        updateView();

        TextView change_password = (TextView) view.findViewById(R.id.button_change_password);
        change_password.setOnClickListener(v -> activity.callFragment(new ChangePassword_fragment()));

        Button save = (Button) view.findViewById(R.id.button_save);
        save.setOnClickListener(v -> {
            if(!validate()) {
                return;
            }
            Log.e("Bionic", "User before: " + user.toString());
            user.setFirstName(name.getText().toString());
            user.setLastName(lastname.getText().toString());
            user.setInsertion(insertion.getText().toString());
            user.setEmail(email.getText().toString());
            user.setSex(gender.getSelectedItem().toString());
            user.setPostalCode(postalCode.getText().toString());

            new UpdatePersonalInfo(user,getView()).execute();
        });
    }



}

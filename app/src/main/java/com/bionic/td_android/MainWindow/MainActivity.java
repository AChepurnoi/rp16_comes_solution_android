package com.bionic.td_android.MainWindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by user on 18.03.2016.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment active;
    private User user;

    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        active = new Dashboard_fragment();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString(Intent.EXTRA_TEXT) != null) {
                try {
                    user = new ObjectMapper().readValue(bundle.getString(Intent.EXTRA_TEXT),User.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Bionic","Error parsing User in Main Activity");
                }
                getIntent().removeExtra(Intent.EXTRA_TEXT);

            }
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,active).commit();

    }



    @Override
    protected void onResume() {
        super.onResume();


    }

    public void my_account(){

        active = new Account_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
        ((Account_fragment)active).setUser(user);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(active.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);

    }

    public User getUser() {
        return user;
    }
}

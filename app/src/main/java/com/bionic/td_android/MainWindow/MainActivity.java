package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bionic.td_android.R;

/**
 * Created by user on 18.03.2016.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        active = new Dashboard_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,active).commit();

    }


    public void my_account(){

        active = new Account_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(active.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);

    }



}

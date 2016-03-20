package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.R;

/**
 * Created by user on 17.03.2016.
 */
public class RegisterActivity extends AppCompatActivity {


    private Fragment first, second;
    private Fragment active;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first = active = new First_step();
        layout = findViewById(R.id.fragment_container);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,active).commit();


    }


    private void forward(){
        second = active = new Second_step();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void nextStep(){

        if( ((First_step) active).validateForm() )
            forward();
        else Snackbar.make(layout,"Fill in necessary forms or check password match",Snackbar.LENGTH_LONG).show();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(active.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if(active instanceof Second_step)
            active = first;
        super.onBackPressed();


    }
}

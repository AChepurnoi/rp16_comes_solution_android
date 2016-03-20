package com.bionic.td_android.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.R;
import com.bionic.td_android.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {


    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        active = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,active).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(active.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);

    }

    public void forgot_password(){

        active = new Forgotten_password();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void login(){

        startActivity(new Intent(this,MainActivity.class));


    }

    public void register(){

        startActivity(new Intent(this,RegisterActivity.class));
    }


}

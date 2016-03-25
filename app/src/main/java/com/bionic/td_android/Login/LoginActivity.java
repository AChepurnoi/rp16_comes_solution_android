package com.bionic.td_android.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.R;
import com.bionic.td_android.Register.RegisterActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {


    private Fragment active;
    private View layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        active = new LoginFragment();
        layout = findViewById(R.id.fragment_container);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,active).commit();



    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString(Intent.EXTRA_TEXT) != null) {
                Snackbar.make(active.getView(), bundle.getString(Intent.EXTRA_TEXT), Snackbar.LENGTH_LONG).show();
                getIntent().removeExtra(Intent.EXTRA_TEXT);

            }
        }
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

    public void login(String login,String pass){

        Log.e("Bionic", "Start");
        String url = API.GET_USER();
        final AlertDialog dialog = new SpotsDialog(LoginActivity.this,"Loging in");
        dialog.show();
        Log.e("Bionic","Login: " + login + ". Pass: " + pass);
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        Log.e("Bionic", encoded);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization","Basic " + encoded);


        client.get(this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog.dismiss();
                Log.e("Bionic", "Fail " + statusCode);
                Snackbar.make(layout,"Unable to login.",Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dialog.dismiss();
                User user = null;
                try {
                    user = new ObjectMapper().readValue(responseString, User.class);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT,responseString);
                    startActivity(intent);
                    Log.e("Bionic", user.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Bionic","error parsing");
                }
            }
        });
    }


    public void register(){

        startActivity(new Intent(this,RegisterActivity.class));
    }


}

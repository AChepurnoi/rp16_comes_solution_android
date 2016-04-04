package com.bionic.td_android.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.Entity.PasswordsDTO;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.R;
import com.bionic.td_android.Register.RegisterActivity;
import com.bionic.td_android.Utility.EmailValidator;
import com.bionic.td_android.Utility.EntitySaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {


    private Fragment active;
    private View layout;
    private EmailValidator validator = new EmailValidator();

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

    public void change_password(String email){

        active = new Temporary_pass_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void changePassword(String temp, String newPassword, User user){

        AsyncHttpClient client = new AsyncHttpClient();
        String url = API.CHANGE_PASSWORD(user.getmId());
        String encoded = Base64.encodeToString((user.getEmail() + ":" + temp).getBytes(), 0);
        Log.e("Bionic", encoded);
        client.addHeader("Authorization", "Basic " + encoded);
        final AlertDialog dialog = new SpotsDialog(LoginActivity.this,"Changing password");
        PasswordsDTO dto = new PasswordsDTO();
        dto.setOldPassword(temp);
        dto.setNewPassword(newPassword);

        String jsonInString = null;
        try {
            jsonInString = new ObjectMapper().writeValueAsString(dto);
            Log.e("Bionic",jsonInString);
        } catch (JsonProcessingException e) {
            Snackbar.make(layout,"Server error",Snackbar.LENGTH_LONG).show();
            return;
        }
        dialog.show();

        //Дикий костыль, потому что на сервер приходит всегда сообщение без 2 последних символов, именно отсюда
        //в логах сообщение полное - приходит без двух последних символов.Нужно пофиксить как-то (??????)
        jsonInString = jsonInString + "kk";



        client.put(getApplicationContext(), url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        dialog.dismiss();
                        Snackbar.make(layout,"Cannot change password",Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic",responseString);

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        dialog.dismiss();
                        onBackPressed();
                        Snackbar.make(layout,"Password has been changed",Snackbar.LENGTH_LONG).show();
                    }
                });



    }

    public void resetPassword(String email){

        final LoginActivity activity = this;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30000);
        String url = API.RESET_PASSWORD();
        final AlertDialog dialog = new SpotsDialog(LoginActivity.this,"Sending email");
        dialog.show();
        client.put(getApplicationContext(), url, new ByteArrayEntity(email.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        dialog.dismiss();
                        Snackbar.make(layout, responseString, Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic", "Fail " + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        dialog.dismiss();
                        Snackbar.make(layout, "mail successfully sent", Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic", responseString);
                        activity.onBackPressed();
                    }
                });
    }

    public void login(final String login,String pass){



        boolean validEmail = validator.validate(login);
        if(!validEmail){
            Snackbar.make(layout,"Login should be a valid email",Snackbar.LENGTH_LONG).show();
            return;
        }



        Log.e("Bionic", "Start");
        String url = API.GET_USER();
        final AlertDialog dialog = new SpotsDialog(LoginActivity.this,"Loging in");
        dialog.show();
        Log.e("Bionic", "Login: " + login + ". Pass: " + pass);
        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        Log.e("Bionic", encoded);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Basic " + encoded);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login",login);
        editor.putString("password",pass);
        editor.commit();

        client.get(this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                dialog.dismiss();
                Log.e("Bionic", "Fail " + statusCode + " " + responseString);

                if (responseString.contains("User account is locked")) {
                    Snackbar.make(layout, "You have input wrong password 5 times. Your account was blocked for 30 minutes", Snackbar.LENGTH_LONG).show();
                } else {

                    User user = null;
                    switch (statusCode) {

                        case 404:
                            Snackbar.make(layout, "User with this email is not registered", Snackbar.LENGTH_LONG).show();
                            break;

                        case 403:
                            Log.e("Bionic", statusCode + "code " + responseString);
//                        change_password(login);
                            forgot_password();
                            Snackbar.make(layout, "Password expired. Please request new temporary password", Snackbar.LENGTH_LONG).show();
                            break;
                        case 412:


                            try {
                                user = new ObjectMapper().readValue(responseString, User.class);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                EntitySaver.save(user);

                                startActivity(intent);
                                Log.e("Bionic", user.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("Bionic", "error parsing");
                            }
                            break;

                        case 409:
//                        forgot_password();

                            try {
                                user = new ObjectMapper().readValue(responseString, User.class);
                                EntitySaver.save(user);
                                change_password(login);
                                Log.e("Bionic", user.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("Bionic", "error parsing");
                            }
                            Snackbar.make(layout, "Please change your password", Snackbar.LENGTH_LONG).show();
                            break;

                        default:
                            Snackbar.make(layout, "Unable to login.", Snackbar.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                dialog.dismiss();
                User user = null;
                try {
                    user = new ObjectMapper().readValue(responseString, User.class);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra(Intent.EXTRA_TEXT, responseString);

                    EntitySaver.save(user);
                    startActivity(intent);
                    Log.e("Bionic", user.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Bionic", "error parsing");
                }
            }
        });
    }


    public void register(){

        startActivity(new Intent(this,RegisterActivity.class));
    }


}

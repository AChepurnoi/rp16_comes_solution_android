package com.bionic.td_android.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.Requests.ChangePassword;
import com.bionic.td_android.Networking.Requests.Login;
import com.bionic.td_android.Networking.Requests.ResetPassword;
import com.bionic.td_android.R;
import com.bionic.td_android.Register.RegisterActivity;
import com.bionic.td_android.Utility.EmailValidator;

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


    public void callFragment(Fragment fragment){

        active = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void changePassword(String temp, String newPassword, User user){
        new ChangePassword(temp,newPassword,user,layout,this).execute();
    }

    public void resetPassword(String email){
        new ResetPassword(this,email,layout).execute();
    }

    public void login(final String login,String pass){
        boolean validEmail = validator.validate(login);
        if(!validEmail){
            Snackbar.make(layout,"Login should be a valid email",Snackbar.LENGTH_LONG).show();
            return;
        }
        new Login(login,pass,this,layout).execute();
    }

    public void register(){
        startActivity(new Intent(this,RegisterActivity.class));
    }


}

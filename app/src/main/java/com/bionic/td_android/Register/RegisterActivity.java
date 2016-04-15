package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.Requests.RegisterFirstStep;
import com.bionic.td_android.Networking.Requests.RegisterSecondStep;
import com.bionic.td_android.R;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by user on 17.03.2016.
 */
public class RegisterActivity extends AppCompatActivity {


    private User user;
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


    public void forward(User user){
        this.user = user;
        second = active = new Second_step();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public Fragment getActive() {
        return active;
    }

    public void nextStep(User user,String email) {
        new RegisterFirstStep(email,user,layout,this).execute();
    }

    private void concatUsers(User user){

        User resultive = user;
        resultive.setFirstName(this.user.getFirstName());
        resultive.setLastName(this.user.getLastName());
        resultive.setInsertion(this.user.getInsertion());
        resultive.setSex(this.user.getSex());
        resultive.setEmail(this.user.getEmail());
        resultive.setPassword(this.user.getPassword());
        resultive.setPasswordExpire(this.user.getPasswordExpire());
        resultive.setPostalCode(this.user.getPostalCode());

        this.user = resultive;

    }

    public void secondStedRegister(User user){

        if( ((Second_step) active).validateForm(layout) ) {
            concatUsers(user);
            try {
                Log.e("Bionic",new ObjectMapper().writeValueAsString(this.user));
                registration();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Bionic",e.getMessage());
            }
        }
    }

    private void registration() throws Exception{
        new RegisterSecondStep(user,layout,this).execute();
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

package com.bionic.td_android.Register;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;

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


    private void forward(){
        second = active = new Second_step();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void nextStep(User user){

        if( ((First_step) active).validateForm() ) {
            this.user = user;
            forward();
        }
        else Snackbar.make(layout,"Fill in necessary forms or check password match",Snackbar.LENGTH_LONG).show();
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

        this.user = resultive;

    }

    public void secondStedRegister(User user){

        concatUsers(user);
        try {
            Log.e("Bionic",new ObjectMapper().writeValueAsString(this.user));
            registration();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Bionic",e.getMessage());
        }


    }

    private void registration() throws Exception{
        Log.e("Bionic", "Start");
        String url = "http://77.47.204.138:8080/rest/api/user/";

        AsyncHttpClient client = new AsyncHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);
        Log.e("Bionic", jsonInString);
        ByteArrayEntity be = new ByteArrayEntity(jsonInString.getBytes());
        client.post(getApplicationContext(), url, be,"application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Bionic","Fail");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("Bionic","Success");
            }
        });
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

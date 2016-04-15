package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.bionic.td_android.Register.First_step;
import com.bionic.td_android.Register.RegisterActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class RegisterFirstStep implements IRequest {


    String email;
    User user;
    View view;
    RegisterActivity activity;

    public RegisterFirstStep(String email, User user, View view, RegisterActivity activity) {
        this.email = email;
        this.user = user;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void execute() {

        if( ((First_step) activity.getActive()).validateForm() ) {
            String url = API.IS_EXIST();
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("email",email);
            final AlertDialog dialog = new SpotsDialog(activity,"Please wait");
            dialog.show();
            client.get(activity, url,params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("Bionic", "Fail " + statusCode + responseString);
                    dialog.cancel();
                    Snackbar.make(view, "User already exist.", Snackbar.LENGTH_LONG).show();
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    dialog.cancel();
                    activity.forward(user);
                }
            });
        }
        else Snackbar.make(view,"Fill in necessary forms or check password match",Snackbar.LENGTH_LONG).show();

    }
}

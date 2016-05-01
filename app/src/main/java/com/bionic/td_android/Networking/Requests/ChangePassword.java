package com.bionic.td_android.Networking.Requests;

import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bionic.td_android.Entity.PasswordsDTO;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Login.LoginActivity;
import com.bionic.td_android.Networking.API;
import com.bionic.td_android.Networking.IRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import dmax.dialog.SpotsDialog;

/**
 * Created by user on 15.04.2016.
 */
public class ChangePassword implements IRequest {


    private String temp,newPassword;
    private  User user;
    private View view;
    private LoginActivity activity;

    public ChangePassword(String temp, String newPassword, User user, View view, LoginActivity activity) {
        this.temp = temp;
        this.newPassword = newPassword;
        this.user = user;
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void execute() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = API.CHANGE_PASSWORD(user.getmId());
        String encoded = Base64.encodeToString((user.getEmail() + ":" + temp).getBytes(), 0);
        Log.e("Bionic", encoded);
        client.addHeader("Authorization", "Basic " + encoded);
        final AlertDialog dialog = new SpotsDialog(activity,"Changing password");
        PasswordsDTO dto = new PasswordsDTO();
        dto.setOldPassword(temp);
        dto.setNewPassword(newPassword);

        String jsonInString = null;
        try {
            jsonInString = new ObjectMapper().writeValueAsString(dto);
            Log.e("Bionic",jsonInString);
        } catch (JsonProcessingException e) {
            Snackbar.make(view, "Server error", Snackbar.LENGTH_LONG).show();
            return;
        }
        dialog.show();

        //Дикий костыль, потому что на сервер приходит всегда сообщение без 2 последних символов, именно отсюда
        //в логах сообщение полное - приходит без двух последних символов.Нужно пофиксить как-то (??????)
        jsonInString = jsonInString + "kk";



        client.put(activity, url, new ByteArrayEntity(jsonInString.getBytes()),
                "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        dialog.dismiss();
                        Snackbar.make(view,"Cannot change password",Snackbar.LENGTH_LONG).show();
                        Log.e("Bionic",responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        dialog.dismiss();
                        activity.onBackPressed();
                        Snackbar.make(view,"Password has been changed",Snackbar.LENGTH_LONG).show();
                    }
                });

    }
}

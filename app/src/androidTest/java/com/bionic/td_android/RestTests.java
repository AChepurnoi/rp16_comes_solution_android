package com.bionic.td_android;

import android.test.AndroidTestCase;
import android.util.Base64;
import android.util.Log;

import com.bionic.td_android.Data.DbManager;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Networking.API;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 19.03.2016.
 */

public class RestTests extends AndroidTestCase {

/*
    public void test(){
        Log.e("Bionic", "Start");
        String url = "http://77.47.204.138:8080/rest/api/user/4";
        SyncHttpClient client = new SyncHttpClient();
        client.get(mContext, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Bionic", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("Bionic", responseString);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    User user = mapper.readValue(responseString,User.class);
                    Log.e("Bionic",user.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void testPost() throws JsonProcessingException {





        Log.e("Bionic", "Start");
        String url = "http://77.47.204.138:8080/rest/api/user/";

        User user = foo();
        SyncHttpClient client = new SyncHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);
        Log.e("Bionic", jsonInString);
        ByteArrayEntity be = new ByteArrayEntity(jsonInString.getBytes());
        client.post(mContext, url, be,"application/json", new TextHttpResponseHandler() {
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

    public User foo(){
        final List<User> users = new ArrayList<>();
        Log.e("Bionic", "Start");
        String url = "http://77.47.204.138:8080/rest/api/user/4";
        SyncHttpClient client = new SyncHttpClient();
        client.get(mContext, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Bionic", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("Bionic", responseString);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    User user = mapper.readValue(responseString,User.class);
                    Log.e("Bionic",user.toString());
                    users.add(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return users.get(0);
    }
    */

    public void testLogin(){



//
//        Log.e("Bionic", "Start");
//        String url = "http://77.47.204.138:8080/rest/api/users/login";
//        String login = "test@test.com";
//        String pass = "12345";
//
//        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
//        Log.e("Bionic", encoded);
//        SyncHttpClient client = new SyncHttpClient();
//        client.addHeader("Authorization","Basic " + encoded);
//
//
//        client.get(mContext, url, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.e("Bionic", "Fail " + statusCode);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                User user = null;
//                try {
//                    user = new ObjectMapper().readValue(responseString,User.class);
//                    Log.e("Bionic",user.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void test(){

        Log.e("Bionic", "Start");
        DbManager manager = new DbManager(getContext());
        User user = manager.loadUser();
        String url = API.GET_SUMMARY(user.getmId(),2016,0);
        String login = "granium90@gmail.com";
        String pass = "qwerty";

        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
        Log.e("Bionic", encoded);
        SyncHttpClient client = new SyncHttpClient();
        client.addHeader("Authorization","Basic " + encoded);
        client.get(mContext, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Bionic", "Fail " + statusCode + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Log.e("Bionic", statusCode + " " + responseString);
            }
        });
    }



}

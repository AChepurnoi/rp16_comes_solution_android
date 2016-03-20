package com.bionic.td_android;

import android.test.AndroidTestCase;

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
}

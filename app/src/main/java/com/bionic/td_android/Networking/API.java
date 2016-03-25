package com.bionic.td_android.Networking;

/**
 * Created by user on 21.03.2016.
 */
public class API {

    private static String SERVER_ADDRESS = "http://77.47.204.138:8080/";
    private static String GET_USER = SERVER_ADDRESS + "rest/api/users/login";
    private static String REGISTER = SERVER_ADDRESS + "rest/api/auth";
    private static String RESET_PASSWORD = "";


    public static String GET_USER() {
        return GET_USER;
    }

    public static String REGISTER() {
        return REGISTER;
    }

    public static String RESET_PASSWORD() {
        return RESET_PASSWORD;
    }
}
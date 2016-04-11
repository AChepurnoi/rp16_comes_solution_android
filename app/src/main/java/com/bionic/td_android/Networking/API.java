package com.bionic.td_android.Networking;

/**
 * Created by user on 21.03.2016.
 */
public class API {



//    private static String SERVER_ADDRESS = "http://10.0.3.2:8080/";
    private static String SERVER_ADDRESS = "http://77.47.204.138:8080/";
    private static String LOG_IN = SERVER_ADDRESS + "rest/api/auth/login";
    private static String GET_USER = SERVER_ADDRESS + "rest/api/users/login";
    private static String REGISTER = SERVER_ADDRESS + "rest/api/auth";
    private static String CHANGE_PASSWORD = SERVER_ADDRESS + "rest/api/users/";
    private static String RESET_PASSWORD = SERVER_ADDRESS + "rest/api/auth/password";
    private static String IS_EXIST = SERVER_ADDRESS + "rest/api/auth/exist";
    private static String UPDATE_USER = SERVER_ADDRESS + "rest/api/users/";
    private static String VERIFICATION = SERVER_ADDRESS + "rest/api/users/";
    private static String USERS = SERVER_ADDRESS + "rest/api/users/";
    public static String ADD_SHIFT(long userId){
        return USERS + userId +"/shifts";
    }
    public static String GET_USER() {
        return GET_USER;
    }

    public static String LOG_IN() { return LOG_IN; }

    public static String REGISTER() {
        return REGISTER;
    }

    public static String RESET_PASSWORD() {
        return RESET_PASSWORD;
    }

    public static String CHANGE_PASSWORD(long userId){
        return CHANGE_PASSWORD + userId + "/password";
    }

    public static String IS_EXIST() {
        return IS_EXIST;
    }

    public static String UPDATE_USER(long userId){
        return UPDATE_USER + userId;
    }
    public static String VERIFICATION(long userId){
        return VERIFICATION + userId + "/verify";
    }


}
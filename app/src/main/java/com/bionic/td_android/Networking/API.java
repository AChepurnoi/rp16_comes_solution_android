package com.bionic.td_android.Networking;

/**
 * Created by user on 21.03.2016.
 */
public class API {



//    private static String SERVER_ADDRESS = "http://10.0.3.2:8080/"; //Localhost
//    private static String SERVER_ADDRESS = "http://77.47.204.138:8080/"; //Sasha
//    private static String SERVER_ADDRESS = "http://46.46.65.130:8080/";//Sasha2
//    private static String SERVER_ADDRESS = "http://128.0.169.5:808/tda/"; //Bionic
//    private static String SERVER_ADDRESS = "http://77.47.231.38:8080/"; //Dima
    private static String SERVER_ADDRESS = "http://213.163.93.170:8080/tda-0.4.1/"; //Peter
    private static String LOG_IN = SERVER_ADDRESS + "rest/api/auth/login";
    private static String GET_USER = SERVER_ADDRESS + "rest/api/users/login";
    private static String REGISTER = SERVER_ADDRESS + "rest/api/auth";
    private static String CHANGE_PASSWORD = SERVER_ADDRESS + "rest/api/users/";
    private static String RESET_PASSWORD = SERVER_ADDRESS + "rest/api/auth/password";
    private static String IS_EXIST = SERVER_ADDRESS + "rest/api/auth/exist";
    private static String UPDATE_USER = SERVER_ADDRESS + "rest/api/users/";
    private static String VERIFICATION = SERVER_ADDRESS + "rest/api/users/";
    private static String USERS = SERVER_ADDRESS + "rest/api/users/";
    private static String SUMMARY = SERVER_ADDRESS + "/rest/api/users/";

    public static String GET_SUMMARY(long userId, long year, long period){
        return USERS + userId + "/summary/" + year + "/" + period + "";
    }
    public static String SHIFT_API(long userId){
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
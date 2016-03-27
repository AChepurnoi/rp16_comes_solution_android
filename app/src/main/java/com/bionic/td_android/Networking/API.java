package com.bionic.td_android.Networking;

/**
 * Created by user on 21.03.2016.
 */
public class API {


    /*

    [16:45:52] Taras Yaroshchuk: Change password
Password Incorrect - BAD_REQUEST(400, "Bad Request"),
User not found - NOT_FOUND(404, "Not Found"),
[16:54:50] Taras Yaroshchuk: PUT /rest/api/users/password with Basic auth
[16:55:03] Taras Yaroshchuk: (@RequestBody PasswordsDTO passwordsDTO)


     */
//    private static String SERVER_ADDRESS = "http://10.0.3.2:8080/";
    private static String SERVER_ADDRESS = "http://77.47.204.138:8080/";
    private static String LOG_IN = SERVER_ADDRESS + "rest/api/auth/login";
    private static String GET_USER = SERVER_ADDRESS + "rest/api/users/login";
    private static String REGISTER = SERVER_ADDRESS + "rest/api/auth";
    private static String CHANGE_PASSWORD = SERVER_ADDRESS + "rest/api/users/password";
    private static String RESET_PASSWORD = SERVER_ADDRESS + "rest/api/auth/password";
    private static String IS_EXIST = SERVER_ADDRESS + "rest/api/auth/exist";

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

    public static String CHANGE_PASSWORD(){
        return CHANGE_PASSWORD;
    }

    public static String IS_EXIST() {
        return IS_EXIST;
    }

}
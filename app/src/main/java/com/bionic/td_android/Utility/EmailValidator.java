package com.bionic.td_android.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 25.03.2016.
 */
public class EmailValidator{

    private Pattern pattern;

    public EmailValidator() {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {

        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
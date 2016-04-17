package com.bionic.td_android.Data.Provider.user;

import android.net.Uri;
import android.provider.BaseColumns;

import com.bionic.td_android.Data.Provider.TDAProvider;

/**
 * User entity
 */
public class UserColumns implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final Uri CONTENT_URI = Uri.parse(TDAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SERVERID = "serverId";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String FIRSTNAME = "firstName";

    public static final String LASTNAME = "lastName";

    public static final String INSERTION = "insertion";

    public static final String SEX = "sex";

    public static final String FOURWEEKPAYOFF = "fourWeekPayoff";

    public static final String ZEROHOURS = "zeroHours";

    public static final String CONTRACTHOURS = "contractHours";

    public static final String ENABLED = "enabled";

    public static final String VERIFIED = "verified";

    public static final String POSTALCODE = "postalCode";

    public static final String PASSWORDEXPIRE = "passwordExpire";

    public static final String WORKSCHEDULE_ID = "workschedule_id";

    public static final String EMPLOYER_ID = "employer_id";

    public static final String ROLE = "role";

    public static final String SYNCHRONIZE = "synchronize";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SERVERID,
            EMAIL,
            PASSWORD,
            FIRSTNAME,
            LASTNAME,
            INSERTION,
            SEX,
            FOURWEEKPAYOFF,
            ZEROHOURS,
            CONTRACTHOURS,
            ENABLED,
            VERIFIED,
            POSTALCODE,
            PASSWORDEXPIRE,
            WORKSCHEDULE_ID,
            EMPLOYER_ID,
            ROLE,
            SYNCHRONIZE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SERVERID) || c.contains("." + SERVERID)) return true;
            if (c.equals(EMAIL) || c.contains("." + EMAIL)) return true;
            if (c.equals(PASSWORD) || c.contains("." + PASSWORD)) return true;
            if (c.equals(FIRSTNAME) || c.contains("." + FIRSTNAME)) return true;
            if (c.equals(LASTNAME) || c.contains("." + LASTNAME)) return true;
            if (c.equals(INSERTION) || c.contains("." + INSERTION)) return true;
            if (c.equals(SEX) || c.contains("." + SEX)) return true;
            if (c.equals(FOURWEEKPAYOFF) || c.contains("." + FOURWEEKPAYOFF)) return true;
            if (c.equals(ZEROHOURS) || c.contains("." + ZEROHOURS)) return true;
            if (c.equals(CONTRACTHOURS) || c.contains("." + CONTRACTHOURS)) return true;
            if (c.equals(ENABLED) || c.contains("." + ENABLED)) return true;
            if (c.equals(VERIFIED) || c.contains("." + VERIFIED)) return true;
            if (c.equals(POSTALCODE) || c.contains("." + POSTALCODE)) return true;
            if (c.equals(PASSWORDEXPIRE) || c.contains("." + PASSWORDEXPIRE)) return true;
            if (c.equals(WORKSCHEDULE_ID) || c.contains("." + WORKSCHEDULE_ID)) return true;
            if (c.equals(EMPLOYER_ID) || c.contains("." + EMPLOYER_ID)) return true;
            if (c.equals(ROLE) || c.contains("." + ROLE)) return true;
            if (c.equals(SYNCHRONIZE) || c.contains("." + SYNCHRONIZE)) return true;
        }
        return false;
    }

}

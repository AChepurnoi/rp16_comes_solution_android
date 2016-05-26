package com.bionic.td_android.Data.Provider.workschedule;

import android.net.Uri;
import android.provider.BaseColumns;

import com.bionic.td_android.Data.Provider.TDAProvider;
import com.bionic.td_android.Data.Provider.job.JobColumns;
import com.bionic.td_android.Data.Provider.ride.RideColumns;
import com.bionic.td_android.Data.Provider.shift.ShiftColumns;
import com.bionic.td_android.Data.Provider.user.UserColumns;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleColumns;

/**
 * WorkSchedule entity
 */
public class WorkscheduleColumns implements BaseColumns {
    public static final String TABLE_NAME = "workschedule";
    public static final Uri CONTENT_URI = Uri.parse(TDAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SERVERID = "serverId";

    public static final String CREATIONTIME = "creationTime";

    public static final String SUNDAY = "Sunday";

    public static final String MONDAY = "Monday";

    public static final String TUESDAY = "Tuesday";

    public static final String WEDNESDAY = "Wednesday";

    public static final String THURSDAY = "Thursday";

    public static final String FRIDAY = "Friday";

    public static final String SATURDAY = "Saturday";

    public static final String SYNCHRONIZE = "synchronize";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SERVERID,
            CREATIONTIME,
            SUNDAY,
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
            SATURDAY,
            SYNCHRONIZE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SERVERID) || c.contains("." + SERVERID)) return true;
            if (c.equals(CREATIONTIME) || c.contains("." + CREATIONTIME)) return true;
            if (c.equals(SUNDAY) || c.contains("." + SUNDAY)) return true;
            if (c.equals(MONDAY) || c.contains("." + MONDAY)) return true;
            if (c.equals(TUESDAY) || c.contains("." + TUESDAY)) return true;
            if (c.equals(WEDNESDAY) || c.contains("." + WEDNESDAY)) return true;
            if (c.equals(THURSDAY) || c.contains("." + THURSDAY)) return true;
            if (c.equals(FRIDAY) || c.contains("." + FRIDAY)) return true;
            if (c.equals(SATURDAY) || c.contains("." + SATURDAY)) return true;
            if (c.equals(SYNCHRONIZE) || c.contains("." + SYNCHRONIZE)) return true;
        }
        return false;
    }

}

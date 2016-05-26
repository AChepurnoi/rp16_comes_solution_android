package com.bionic.td_android.Data.Provider.ride;

import android.net.Uri;
import android.provider.BaseColumns;

import com.bionic.td_android.Data.Provider.TDAProvider;
import com.bionic.td_android.Data.Provider.job.JobColumns;
import com.bionic.td_android.Data.Provider.ride.RideColumns;
import com.bionic.td_android.Data.Provider.shift.ShiftColumns;
import com.bionic.td_android.Data.Provider.user.UserColumns;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleColumns;

/**
 * Ride entity
 */
public class RideColumns implements BaseColumns {
    public static final String TABLE_NAME = "ride";
    public static final Uri CONTENT_URI = Uri.parse(TDAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SERVERID = "serverId";

    public static final String STARTTIME = "startTime";

    public static final String ENDTIME = "endTime";

    public static final String SHIFTID = "shiftId";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SERVERID,
            STARTTIME,
            ENDTIME,
            SHIFTID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SERVERID) || c.contains("." + SERVERID)) return true;
            if (c.equals(STARTTIME) || c.contains("." + STARTTIME)) return true;
            if (c.equals(ENDTIME) || c.contains("." + ENDTIME)) return true;
            if (c.equals(SHIFTID) || c.contains("." + SHIFTID)) return true;
        }
        return false;
    }

}

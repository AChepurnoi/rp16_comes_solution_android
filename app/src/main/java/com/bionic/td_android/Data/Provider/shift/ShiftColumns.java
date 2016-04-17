package com.bionic.td_android.Data.Provider.shift;

import android.net.Uri;
import android.provider.BaseColumns;

import com.bionic.td_android.Data.Provider.TDAProvider;
import com.bionic.td_android.Data.Provider.job.JobColumns;
import com.bionic.td_android.Data.Provider.ride.RideColumns;
import com.bionic.td_android.Data.Provider.shift.ShiftColumns;
import com.bionic.td_android.Data.Provider.user.UserColumns;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleColumns;

/**
 * Shift entity
 */
public class ShiftColumns implements BaseColumns {
    public static final String TABLE_NAME = "shift";
    public static final Uri CONTENT_URI = Uri.parse(TDAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SERVERID = "serverId";

    public static final String STARTTIME = "startTime";

    public static final String ENDTIME = "endTime";

    public static final String PAUSE = "pause";

    public static final String USERID = "userId";

    public static final String SYNCHRONIZE = "synchronize";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SERVERID,
            STARTTIME,
            ENDTIME,
            PAUSE,
            USERID,
            SYNCHRONIZE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SERVERID) || c.contains("." + SERVERID)) return true;
            if (c.equals(STARTTIME) || c.contains("." + STARTTIME)) return true;
            if (c.equals(ENDTIME) || c.contains("." + ENDTIME)) return true;
            if (c.equals(PAUSE) || c.contains("." + PAUSE)) return true;
            if (c.equals(USERID) || c.contains("." + USERID)) return true;
            if (c.equals(SYNCHRONIZE) || c.contains("." + SYNCHRONIZE)) return true;
        }
        return false;
    }

}

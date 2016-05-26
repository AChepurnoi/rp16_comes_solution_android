package com.bionic.td_android.Data.Provider.job;

import android.net.Uri;
import android.provider.BaseColumns;

import com.bionic.td_android.Data.Provider.TDAProvider;
import com.bionic.td_android.Data.Provider.job.JobColumns;
import com.bionic.td_android.Data.Provider.ride.RideColumns;
import com.bionic.td_android.Data.Provider.shift.ShiftColumns;
import com.bionic.td_android.Data.Provider.user.UserColumns;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleColumns;

/**
 * Job entity
 */
public class JobColumns implements BaseColumns {
    public static final String TABLE_NAME = "job";
    public static final Uri CONTENT_URI = Uri.parse(TDAProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USERID = "userId";

    public static final String JOBID = "jobId";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USERID,
            JOBID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USERID) || c.contains("." + USERID)) return true;
            if (c.equals(JOBID) || c.contains("." + JOBID)) return true;
        }
        return false;
    }

}

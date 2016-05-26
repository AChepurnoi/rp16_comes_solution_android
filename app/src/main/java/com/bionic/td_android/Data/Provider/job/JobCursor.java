package com.bionic.td_android.Data.Provider.job;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code job} table.
 */
public class JobCursor extends AbstractCursor implements JobModel {
    public JobCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(JobColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code userid} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getUserid() {
        Long res = getLongOrNull(JobColumns.USERID);
        return res;
    }

    /**
     * Get the {@code jobid} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getJobid() {
        Long res = getLongOrNull(JobColumns.JOBID);
        return res;
    }
}

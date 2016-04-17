package com.bionic.td_android.Data.Provider.job;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code job} table.
 */
public class JobContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return JobColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable JobSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable JobSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public JobContentValues putUserid(@Nullable Long value) {
        mContentValues.put(JobColumns.USERID, value);
        return this;
    }

    public JobContentValues putUseridNull() {
        mContentValues.putNull(JobColumns.USERID);
        return this;
    }

    public JobContentValues putJobid(@Nullable Integer value) {
        mContentValues.put(JobColumns.JOBID, value);
        return this;
    }

    public JobContentValues putJobidNull() {
        mContentValues.putNull(JobColumns.JOBID);
        return this;
    }
}

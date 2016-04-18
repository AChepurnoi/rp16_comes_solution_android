package com.bionic.td_android.Data.Provider.ride;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bionic.td_android.Data.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code ride} table.
 */
public class RideContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return RideColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable RideSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable RideSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public RideContentValues putServerid(@Nullable Long value) {
        mContentValues.put(RideColumns.SERVERID, value);
        return this;
    }

    public RideContentValues putServeridNull() {
        mContentValues.putNull(RideColumns.SERVERID);
        return this;
    }

    public RideContentValues putStarttime(@Nullable Long value) {
        mContentValues.put(RideColumns.STARTTIME, value);
        return this;
    }

    public RideContentValues putStarttimeNull() {
        mContentValues.putNull(RideColumns.STARTTIME);
        return this;
    }

    public RideContentValues putEndtime(@Nullable Long value) {
        mContentValues.put(RideColumns.ENDTIME, value);
        return this;
    }

    public RideContentValues putEndtimeNull() {
        mContentValues.putNull(RideColumns.ENDTIME);
        return this;
    }

    public RideContentValues putShiftid(@Nullable Long value) {
        mContentValues.put(RideColumns.SHIFTID, value);
        return this;
    }

    public RideContentValues putShiftidNull() {
        mContentValues.putNull(RideColumns.SHIFTID);
        return this;
    }
}

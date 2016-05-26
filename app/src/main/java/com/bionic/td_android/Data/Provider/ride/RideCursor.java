package com.bionic.td_android.Data.Provider.ride;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code ride} table.
 */
public class RideCursor extends AbstractCursor implements RideModel {
    public RideCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(RideColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code serverid} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getServerid() {
        Long res = getLongOrNull(RideColumns.SERVERID);
        return res;
    }

    /**
     * Get the {@code starttime} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getStarttime() {
        Long res = getLongOrNull(RideColumns.STARTTIME);
        return res;
    }

    /**
     * Get the {@code endtime} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getEndtime() {
        Long res = getLongOrNull(RideColumns.ENDTIME);
        return res;
    }

    /**
     * Get the {@code shiftid} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getShiftid() {
        Long res = getLongOrNull(RideColumns.SHIFTID);
        return res;
    }
}

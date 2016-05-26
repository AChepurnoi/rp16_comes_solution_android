package com.bionic.td_android.Data.Provider.shift;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code shift} table.
 */
public class ShiftCursor extends AbstractCursor implements ShiftModel {
    public ShiftCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ShiftColumns._ID);
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
        Long res = getLongOrNull(ShiftColumns.SERVERID);
        return res;
    }

    /**
     * Get the {@code starttime} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getStarttime() {
        Long res = getLongOrNull(ShiftColumns.STARTTIME);
        return res;
    }

    /**
     * Get the {@code endtime} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getEndtime() {
        Long res = getLongOrNull(ShiftColumns.ENDTIME);
        return res;
    }

    /**
     * Get the {@code pause} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getPause() {
        Long res = getLongOrNull(ShiftColumns.PAUSE);
        return res;
    }

    /**
     * Get the {@code userid} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getUserid() {
        Long res = getLongOrNull(ShiftColumns.USERID);
        return res;
    }

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSynchronize() {
        Integer res = getIntegerOrNull(ShiftColumns.SYNCHRONIZE);
        return res;
    }
}

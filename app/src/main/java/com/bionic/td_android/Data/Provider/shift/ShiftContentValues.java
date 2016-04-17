package com.bionic.td_android.Data.Provider.shift;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shift} table.
 */
public class ShiftContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShiftColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ShiftSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ShiftSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShiftContentValues putServerid(@Nullable Long value) {
        mContentValues.put(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftContentValues putServeridNull() {
        mContentValues.putNull(ShiftColumns.SERVERID);
        return this;
    }

    public ShiftContentValues putStarttime(@Nullable Integer value) {
        mContentValues.put(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftContentValues putStarttimeNull() {
        mContentValues.putNull(ShiftColumns.STARTTIME);
        return this;
    }

    public ShiftContentValues putEndtime(@Nullable Integer value) {
        mContentValues.put(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftContentValues putEndtimeNull() {
        mContentValues.putNull(ShiftColumns.ENDTIME);
        return this;
    }

    public ShiftContentValues putPause(@Nullable Integer value) {
        mContentValues.put(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftContentValues putPauseNull() {
        mContentValues.putNull(ShiftColumns.PAUSE);
        return this;
    }

    public ShiftContentValues putUserid(@Nullable Long value) {
        mContentValues.put(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftContentValues putUseridNull() {
        mContentValues.putNull(ShiftColumns.USERID);
        return this;
    }

    public ShiftContentValues putSynchronize(@Nullable Integer value) {
        mContentValues.put(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftContentValues putSynchronizeNull() {
        mContentValues.putNull(ShiftColumns.SYNCHRONIZE);
        return this;
    }
}

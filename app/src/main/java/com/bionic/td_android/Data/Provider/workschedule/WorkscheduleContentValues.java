package com.bionic.td_android.Data.Provider.workschedule;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code workschedule} table.
 */
public class WorkscheduleContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WorkscheduleColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable WorkscheduleSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable WorkscheduleSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public WorkscheduleContentValues putServerid(@Nullable Long value) {
        mContentValues.put(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleContentValues putServeridNull() {
        mContentValues.putNull(WorkscheduleColumns.SERVERID);
        return this;
    }

    public WorkscheduleContentValues putCreationtime(@Nullable Long value) {
        mContentValues.put(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleContentValues putCreationtimeNull() {
        mContentValues.putNull(WorkscheduleColumns.CREATIONTIME);
        return this;
    }

    public WorkscheduleContentValues putSunday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleContentValues putSundayNull() {
        mContentValues.putNull(WorkscheduleColumns.SUNDAY);
        return this;
    }

    public WorkscheduleContentValues putMonday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleContentValues putMondayNull() {
        mContentValues.putNull(WorkscheduleColumns.MONDAY);
        return this;
    }

    public WorkscheduleContentValues putTuesday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleContentValues putTuesdayNull() {
        mContentValues.putNull(WorkscheduleColumns.TUESDAY);
        return this;
    }

    public WorkscheduleContentValues putWednesday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleContentValues putWednesdayNull() {
        mContentValues.putNull(WorkscheduleColumns.WEDNESDAY);
        return this;
    }

    public WorkscheduleContentValues putThursday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleContentValues putThursdayNull() {
        mContentValues.putNull(WorkscheduleColumns.THURSDAY);
        return this;
    }

    public WorkscheduleContentValues putFriday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleContentValues putFridayNull() {
        mContentValues.putNull(WorkscheduleColumns.FRIDAY);
        return this;
    }

    public WorkscheduleContentValues putSaturday(@Nullable String value) {
        mContentValues.put(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleContentValues putSaturdayNull() {
        mContentValues.putNull(WorkscheduleColumns.SATURDAY);
        return this;
    }

    public WorkscheduleContentValues putSynchronize(@Nullable Integer value) {
        mContentValues.put(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleContentValues putSynchronizeNull() {
        mContentValues.putNull(WorkscheduleColumns.SYNCHRONIZE);
        return this;
    }
}

package com.bionic.td_android.Data.Provider.workschedule;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code workschedule} table.
 */
public class WorkscheduleCursor extends AbstractCursor implements WorkscheduleModel {
    public WorkscheduleCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WorkscheduleColumns._ID);
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
        Long res = getLongOrNull(WorkscheduleColumns.SERVERID);
        return res;
    }

    /**
     * Get the {@code creationtime} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getCreationtime() {
        Long res = getLongOrNull(WorkscheduleColumns.CREATIONTIME);
        return res;
    }

    /**
     * Get the {@code sunday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSunday() {
        String res = getStringOrNull(WorkscheduleColumns.SUNDAY);
        return res;
    }

    /**
     * Get the {@code monday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMonday() {
        String res = getStringOrNull(WorkscheduleColumns.MONDAY);
        return res;
    }

    /**
     * Get the {@code tuesday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTuesday() {
        String res = getStringOrNull(WorkscheduleColumns.TUESDAY);
        return res;
    }

    /**
     * Get the {@code wednesday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getWednesday() {
        String res = getStringOrNull(WorkscheduleColumns.WEDNESDAY);
        return res;
    }

    /**
     * Get the {@code thursday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getThursday() {
        String res = getStringOrNull(WorkscheduleColumns.THURSDAY);
        return res;
    }

    /**
     * Get the {@code friday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getFriday() {
        String res = getStringOrNull(WorkscheduleColumns.FRIDAY);
        return res;
    }

    /**
     * Get the {@code saturday} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSaturday() {
        String res = getStringOrNull(WorkscheduleColumns.SATURDAY);
        return res;
    }

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSynchronize() {
        Integer res = getIntegerOrNull(WorkscheduleColumns.SYNCHRONIZE);
        return res;
    }
}

package com.bionic.td_android.Data.Provider.ride;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.bionic.td_android.Data.Provider.base.AbstractSelection;

/**
 * Selection for the {@code ride} table.
 */
public class RideSelection extends AbstractSelection<RideSelection> {
    @Override
    protected Uri baseUri() {
        return RideColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code RideCursor} object, which is positioned before the first entry, or null.
     */
    public RideCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new RideCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public RideCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code RideCursor} object, which is positioned before the first entry, or null.
     */
    public RideCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new RideCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public RideCursor query(Context context) {
        return query(context, null);
    }


    public RideSelection id(long... value) {
        addEquals("ride." + RideColumns._ID, toObjectArray(value));
        return this;
    }

    public RideSelection idNot(long... value) {
        addNotEquals("ride." + RideColumns._ID, toObjectArray(value));
        return this;
    }

    public RideSelection orderById(boolean desc) {
        orderBy("ride." + RideColumns._ID, desc);
        return this;
    }

    public RideSelection orderById() {
        return orderById(false);
    }

    public RideSelection serverid(Long... value) {
        addEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridNot(Long... value) {
        addNotEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridGt(long value) {
        addGreaterThan(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridGtEq(long value) {
        addGreaterThanOrEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridLt(long value) {
        addLessThan(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridLtEq(long value) {
        addLessThanOrEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection orderByServerid(boolean desc) {
        orderBy(RideColumns.SERVERID, desc);
        return this;
    }

    public RideSelection orderByServerid() {
        orderBy(RideColumns.SERVERID, false);
        return this;
    }

    public RideSelection starttime(Long... value) {
        addEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeNot(Long... value) {
        addNotEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeGt(long value) {
        addGreaterThan(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeGtEq(long value) {
        addGreaterThanOrEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeLt(long value) {
        addLessThan(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeLtEq(long value) {
        addLessThanOrEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection orderByStarttime(boolean desc) {
        orderBy(RideColumns.STARTTIME, desc);
        return this;
    }

    public RideSelection orderByStarttime() {
        orderBy(RideColumns.STARTTIME, false);
        return this;
    }

    public RideSelection endtime(Long... value) {
        addEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeNot(Long... value) {
        addNotEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeGt(long value) {
        addGreaterThan(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeGtEq(long value) {
        addGreaterThanOrEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeLt(long value) {
        addLessThan(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeLtEq(long value) {
        addLessThanOrEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection orderByEndtime(boolean desc) {
        orderBy(RideColumns.ENDTIME, desc);
        return this;
    }

    public RideSelection orderByEndtime() {
        orderBy(RideColumns.ENDTIME, false);
        return this;
    }

    public RideSelection shiftid(Long... value) {
        addEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidNot(Long... value) {
        addNotEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidGt(long value) {
        addGreaterThan(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidGtEq(long value) {
        addGreaterThanOrEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidLt(long value) {
        addLessThan(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidLtEq(long value) {
        addLessThanOrEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection orderByShiftid(boolean desc) {
        orderBy(RideColumns.SHIFTID, desc);
        return this;
    }

    public RideSelection orderByShiftid() {
        orderBy(RideColumns.SHIFTID, false);
        return this;
    }
}

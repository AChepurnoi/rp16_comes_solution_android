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

    public RideSelection serverid(Integer... value) {
        addEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridNot(Integer... value) {
        addNotEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridGt(int value) {
        addGreaterThan(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridGtEq(int value) {
        addGreaterThanOrEquals(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridLt(int value) {
        addLessThan(RideColumns.SERVERID, value);
        return this;
    }

    public RideSelection serveridLtEq(int value) {
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

    public RideSelection starttime(Integer... value) {
        addEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeNot(Integer... value) {
        addNotEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeGt(int value) {
        addGreaterThan(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeGtEq(int value) {
        addGreaterThanOrEquals(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeLt(int value) {
        addLessThan(RideColumns.STARTTIME, value);
        return this;
    }

    public RideSelection starttimeLtEq(int value) {
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

    public RideSelection endtime(Integer... value) {
        addEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeNot(Integer... value) {
        addNotEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeGt(int value) {
        addGreaterThan(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeGtEq(int value) {
        addGreaterThanOrEquals(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeLt(int value) {
        addLessThan(RideColumns.ENDTIME, value);
        return this;
    }

    public RideSelection endtimeLtEq(int value) {
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

    public RideSelection shiftid(Integer... value) {
        addEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidNot(Integer... value) {
        addNotEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidGt(int value) {
        addGreaterThan(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidGtEq(int value) {
        addGreaterThanOrEquals(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidLt(int value) {
        addLessThan(RideColumns.SHIFTID, value);
        return this;
    }

    public RideSelection shiftidLtEq(int value) {
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

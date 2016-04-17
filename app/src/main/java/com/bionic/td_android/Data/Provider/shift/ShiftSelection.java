package com.bionic.td_android.Data.Provider.shift;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bionic.td_android.Data.Provider.base.AbstractSelection;

/**
 * Selection for the {@code shift} table.
 */
public class ShiftSelection extends AbstractSelection<ShiftSelection> {
    @Override
    protected Uri baseUri() {
        return ShiftColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ShiftCursor} object, which is positioned before the first entry, or null.
     */
    public ShiftCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ShiftCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ShiftCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ShiftCursor} object, which is positioned before the first entry, or null.
     */
    public ShiftCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ShiftCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ShiftCursor query(Context context) {
        return query(context, null);
    }


    public ShiftSelection id(long... value) {
        addEquals("shift." + ShiftColumns._ID, toObjectArray(value));
        return this;
    }

    public ShiftSelection idNot(long... value) {
        addNotEquals("shift." + ShiftColumns._ID, toObjectArray(value));
        return this;
    }

    public ShiftSelection orderById(boolean desc) {
        orderBy("shift." + ShiftColumns._ID, desc);
        return this;
    }

    public ShiftSelection orderById() {
        return orderById(false);
    }

    public ShiftSelection serverid(Integer... value) {
        addEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridNot(Integer... value) {
        addNotEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridGt(int value) {
        addGreaterThan(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridLt(int value) {
        addLessThan(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection orderByServerid(boolean desc) {
        orderBy(ShiftColumns.SERVERID, desc);
        return this;
    }

    public ShiftSelection orderByServerid() {
        orderBy(ShiftColumns.SERVERID, false);
        return this;
    }

    public ShiftSelection starttime(Integer... value) {
        addEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeNot(Integer... value) {
        addNotEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeGt(int value) {
        addGreaterThan(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeLt(int value) {
        addLessThan(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection orderByStarttime(boolean desc) {
        orderBy(ShiftColumns.STARTTIME, desc);
        return this;
    }

    public ShiftSelection orderByStarttime() {
        orderBy(ShiftColumns.STARTTIME, false);
        return this;
    }

    public ShiftSelection endtime(Integer... value) {
        addEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeNot(Integer... value) {
        addNotEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeGt(int value) {
        addGreaterThan(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeLt(int value) {
        addLessThan(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection orderByEndtime(boolean desc) {
        orderBy(ShiftColumns.ENDTIME, desc);
        return this;
    }

    public ShiftSelection orderByEndtime() {
        orderBy(ShiftColumns.ENDTIME, false);
        return this;
    }

    public ShiftSelection pause(Integer... value) {
        addEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseNot(Integer... value) {
        addNotEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseGt(int value) {
        addGreaterThan(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseLt(int value) {
        addLessThan(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection orderByPause(boolean desc) {
        orderBy(ShiftColumns.PAUSE, desc);
        return this;
    }

    public ShiftSelection orderByPause() {
        orderBy(ShiftColumns.PAUSE, false);
        return this;
    }

    public ShiftSelection userid(Integer... value) {
        addEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridNot(Integer... value) {
        addNotEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridGt(int value) {
        addGreaterThan(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridLt(int value) {
        addLessThan(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection orderByUserid(boolean desc) {
        orderBy(ShiftColumns.USERID, desc);
        return this;
    }

    public ShiftSelection orderByUserid() {
        orderBy(ShiftColumns.USERID, false);
        return this;
    }

    public ShiftSelection synchronize(Integer... value) {
        addEquals(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection synchronizeNot(Integer... value) {
        addNotEquals(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection synchronizeGt(int value) {
        addGreaterThan(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection synchronizeGtEq(int value) {
        addGreaterThanOrEquals(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection synchronizeLt(int value) {
        addLessThan(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection synchronizeLtEq(int value) {
        addLessThanOrEquals(ShiftColumns.SYNCHRONIZE, value);
        return this;
    }

    public ShiftSelection orderBySynchronize(boolean desc) {
        orderBy(ShiftColumns.SYNCHRONIZE, desc);
        return this;
    }

    public ShiftSelection orderBySynchronize() {
        orderBy(ShiftColumns.SYNCHRONIZE, false);
        return this;
    }
}

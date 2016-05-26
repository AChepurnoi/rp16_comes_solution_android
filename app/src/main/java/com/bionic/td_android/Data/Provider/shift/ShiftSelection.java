package com.bionic.td_android.Data.Provider.shift;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
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

    public ShiftSelection serverid(Long... value) {
        addEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridNot(Long... value) {
        addNotEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridGt(long value) {
        addGreaterThan(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridGtEq(long value) {
        addGreaterThanOrEquals(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridLt(long value) {
        addLessThan(ShiftColumns.SERVERID, value);
        return this;
    }

    public ShiftSelection serveridLtEq(long value) {
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

    public ShiftSelection starttime(Long... value) {
        addEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeNot(Long... value) {
        addNotEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeGt(long value) {
        addGreaterThan(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeGtEq(long value) {
        addGreaterThanOrEquals(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeLt(long value) {
        addLessThan(ShiftColumns.STARTTIME, value);
        return this;
    }

    public ShiftSelection starttimeLtEq(long value) {
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

    public ShiftSelection endtime(Long... value) {
        addEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeNot(Long... value) {
        addNotEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeGt(long value) {
        addGreaterThan(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeGtEq(long value) {
        addGreaterThanOrEquals(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeLt(long value) {
        addLessThan(ShiftColumns.ENDTIME, value);
        return this;
    }

    public ShiftSelection endtimeLtEq(long value) {
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

    public ShiftSelection pause(Long... value) {
        addEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseNot(Long... value) {
        addNotEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseGt(long value) {
        addGreaterThan(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseGtEq(long value) {
        addGreaterThanOrEquals(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseLt(long value) {
        addLessThan(ShiftColumns.PAUSE, value);
        return this;
    }

    public ShiftSelection pauseLtEq(long value) {
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

    public ShiftSelection userid(Long... value) {
        addEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridNot(Long... value) {
        addNotEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridGt(long value) {
        addGreaterThan(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridGtEq(long value) {
        addGreaterThanOrEquals(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridLt(long value) {
        addLessThan(ShiftColumns.USERID, value);
        return this;
    }

    public ShiftSelection useridLtEq(long value) {
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

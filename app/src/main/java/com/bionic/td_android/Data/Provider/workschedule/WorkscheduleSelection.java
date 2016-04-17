package com.bionic.td_android.Data.Provider.workschedule;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bionic.td_android.Data.Provider.base.AbstractSelection;

/**
 * Selection for the {@code workschedule} table.
 */
public class WorkscheduleSelection extends AbstractSelection<WorkscheduleSelection> {
    @Override
    protected Uri baseUri() {
        return WorkscheduleColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WorkscheduleCursor} object, which is positioned before the first entry, or null.
     */
    public WorkscheduleCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WorkscheduleCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public WorkscheduleCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WorkscheduleCursor} object, which is positioned before the first entry, or null.
     */
    public WorkscheduleCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WorkscheduleCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public WorkscheduleCursor query(Context context) {
        return query(context, null);
    }


    public WorkscheduleSelection id(long... value) {
        addEquals("workschedule." + WorkscheduleColumns._ID, toObjectArray(value));
        return this;
    }

    public WorkscheduleSelection idNot(long... value) {
        addNotEquals("workschedule." + WorkscheduleColumns._ID, toObjectArray(value));
        return this;
    }

    public WorkscheduleSelection orderById(boolean desc) {
        orderBy("workschedule." + WorkscheduleColumns._ID, desc);
        return this;
    }

    public WorkscheduleSelection orderById() {
        return orderById(false);
    }

    public WorkscheduleSelection serverid(Long... value) {
        addEquals(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection serveridNot(Long... value) {
        addNotEquals(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection serveridGt(int value) {
        addGreaterThan(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection serveridGtEq(int value) {
        addGreaterThanOrEquals(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection serveridLt(int value) {
        addLessThan(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection serveridLtEq(int value) {
        addLessThanOrEquals(WorkscheduleColumns.SERVERID, value);
        return this;
    }

    public WorkscheduleSelection orderByServerid(boolean desc) {
        orderBy(WorkscheduleColumns.SERVERID, desc);
        return this;
    }

    public WorkscheduleSelection orderByServerid() {
        orderBy(WorkscheduleColumns.SERVERID, false);
        return this;
    }

    public WorkscheduleSelection creationtime(Integer... value) {
        addEquals(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection creationtimeNot(Integer... value) {
        addNotEquals(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection creationtimeGt(int value) {
        addGreaterThan(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection creationtimeGtEq(int value) {
        addGreaterThanOrEquals(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection creationtimeLt(int value) {
        addLessThan(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection creationtimeLtEq(int value) {
        addLessThanOrEquals(WorkscheduleColumns.CREATIONTIME, value);
        return this;
    }

    public WorkscheduleSelection orderByCreationtime(boolean desc) {
        orderBy(WorkscheduleColumns.CREATIONTIME, desc);
        return this;
    }

    public WorkscheduleSelection orderByCreationtime() {
        orderBy(WorkscheduleColumns.CREATIONTIME, false);
        return this;
    }

    public WorkscheduleSelection sunday(String... value) {
        addEquals(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection sundayNot(String... value) {
        addNotEquals(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection sundayLike(String... value) {
        addLike(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection sundayContains(String... value) {
        addContains(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection sundayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection sundayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.SUNDAY, value);
        return this;
    }

    public WorkscheduleSelection orderBySunday(boolean desc) {
        orderBy(WorkscheduleColumns.SUNDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderBySunday() {
        orderBy(WorkscheduleColumns.SUNDAY, false);
        return this;
    }

    public WorkscheduleSelection monday(String... value) {
        addEquals(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection mondayNot(String... value) {
        addNotEquals(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection mondayLike(String... value) {
        addLike(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection mondayContains(String... value) {
        addContains(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection mondayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection mondayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.MONDAY, value);
        return this;
    }

    public WorkscheduleSelection orderByMonday(boolean desc) {
        orderBy(WorkscheduleColumns.MONDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderByMonday() {
        orderBy(WorkscheduleColumns.MONDAY, false);
        return this;
    }

    public WorkscheduleSelection tuesday(String... value) {
        addEquals(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection tuesdayNot(String... value) {
        addNotEquals(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection tuesdayLike(String... value) {
        addLike(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection tuesdayContains(String... value) {
        addContains(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection tuesdayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection tuesdayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.TUESDAY, value);
        return this;
    }

    public WorkscheduleSelection orderByTuesday(boolean desc) {
        orderBy(WorkscheduleColumns.TUESDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderByTuesday() {
        orderBy(WorkscheduleColumns.TUESDAY, false);
        return this;
    }

    public WorkscheduleSelection wednesday(String... value) {
        addEquals(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection wednesdayNot(String... value) {
        addNotEquals(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection wednesdayLike(String... value) {
        addLike(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection wednesdayContains(String... value) {
        addContains(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection wednesdayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection wednesdayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.WEDNESDAY, value);
        return this;
    }

    public WorkscheduleSelection orderByWednesday(boolean desc) {
        orderBy(WorkscheduleColumns.WEDNESDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderByWednesday() {
        orderBy(WorkscheduleColumns.WEDNESDAY, false);
        return this;
    }

    public WorkscheduleSelection thursday(String... value) {
        addEquals(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection thursdayNot(String... value) {
        addNotEquals(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection thursdayLike(String... value) {
        addLike(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection thursdayContains(String... value) {
        addContains(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection thursdayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection thursdayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.THURSDAY, value);
        return this;
    }

    public WorkscheduleSelection orderByThursday(boolean desc) {
        orderBy(WorkscheduleColumns.THURSDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderByThursday() {
        orderBy(WorkscheduleColumns.THURSDAY, false);
        return this;
    }

    public WorkscheduleSelection friday(String... value) {
        addEquals(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection fridayNot(String... value) {
        addNotEquals(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection fridayLike(String... value) {
        addLike(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection fridayContains(String... value) {
        addContains(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection fridayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection fridayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.FRIDAY, value);
        return this;
    }

    public WorkscheduleSelection orderByFriday(boolean desc) {
        orderBy(WorkscheduleColumns.FRIDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderByFriday() {
        orderBy(WorkscheduleColumns.FRIDAY, false);
        return this;
    }

    public WorkscheduleSelection saturday(String... value) {
        addEquals(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection saturdayNot(String... value) {
        addNotEquals(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection saturdayLike(String... value) {
        addLike(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection saturdayContains(String... value) {
        addContains(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection saturdayStartsWith(String... value) {
        addStartsWith(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection saturdayEndsWith(String... value) {
        addEndsWith(WorkscheduleColumns.SATURDAY, value);
        return this;
    }

    public WorkscheduleSelection orderBySaturday(boolean desc) {
        orderBy(WorkscheduleColumns.SATURDAY, desc);
        return this;
    }

    public WorkscheduleSelection orderBySaturday() {
        orderBy(WorkscheduleColumns.SATURDAY, false);
        return this;
    }

    public WorkscheduleSelection synchronize(Integer... value) {
        addEquals(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection synchronizeNot(Integer... value) {
        addNotEquals(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection synchronizeGt(int value) {
        addGreaterThan(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection synchronizeGtEq(int value) {
        addGreaterThanOrEquals(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection synchronizeLt(int value) {
        addLessThan(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection synchronizeLtEq(int value) {
        addLessThanOrEquals(WorkscheduleColumns.SYNCHRONIZE, value);
        return this;
    }

    public WorkscheduleSelection orderBySynchronize(boolean desc) {
        orderBy(WorkscheduleColumns.SYNCHRONIZE, desc);
        return this;
    }

    public WorkscheduleSelection orderBySynchronize() {
        orderBy(WorkscheduleColumns.SYNCHRONIZE, false);
        return this;
    }
}

package com.bionic.td_android.Data.Provider.user;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code user} table.
 */
public class UserContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable UserSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable UserSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public UserContentValues putServerid(@Nullable Long value) {
        mContentValues.put(UserColumns.SERVERID, value);
        return this;
    }

    public UserContentValues putServeridNull() {
        mContentValues.putNull(UserColumns.SERVERID);
        return this;
    }

    public UserContentValues putEmail(@Nullable String value) {
        mContentValues.put(UserColumns.EMAIL, value);
        return this;
    }

    public UserContentValues putEmailNull() {
        mContentValues.putNull(UserColumns.EMAIL);
        return this;
    }

    public UserContentValues putPassword(@Nullable String value) {
        mContentValues.put(UserColumns.PASSWORD, value);
        return this;
    }

    public UserContentValues putPasswordNull() {
        mContentValues.putNull(UserColumns.PASSWORD);
        return this;
    }

    public UserContentValues putFirstname(@Nullable String value) {
        mContentValues.put(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserContentValues putFirstnameNull() {
        mContentValues.putNull(UserColumns.FIRSTNAME);
        return this;
    }

    public UserContentValues putLastname(@Nullable String value) {
        mContentValues.put(UserColumns.LASTNAME, value);
        return this;
    }

    public UserContentValues putLastnameNull() {
        mContentValues.putNull(UserColumns.LASTNAME);
        return this;
    }

    public UserContentValues putInsertion(@Nullable String value) {
        mContentValues.put(UserColumns.INSERTION, value);
        return this;
    }

    public UserContentValues putInsertionNull() {
        mContentValues.putNull(UserColumns.INSERTION);
        return this;
    }

    public UserContentValues putSex(@Nullable String value) {
        mContentValues.put(UserColumns.SEX, value);
        return this;
    }

    public UserContentValues putSexNull() {
        mContentValues.putNull(UserColumns.SEX);
        return this;
    }

    public UserContentValues putTvt(@Nullable Integer value) {
        mContentValues.put(UserColumns.TVT, value);
        return this;
    }

    public UserContentValues putTvtNull() {
        mContentValues.putNull(UserColumns.TVT);
        return this;
    }

    public UserContentValues putPaidtimefortime(@Nullable Integer value) {
        mContentValues.put(UserColumns.PAIDTIMEFORTIME, value);
        return this;
    }

    public UserContentValues putPaidtimefortimeNull() {
        mContentValues.putNull(UserColumns.PAIDTIMEFORTIME);
        return this;
    }

    public UserContentValues putFourweekpayoff(@Nullable Integer value) {
        mContentValues.put(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserContentValues putFourweekpayoffNull() {
        mContentValues.putNull(UserColumns.FOURWEEKPAYOFF);
        return this;
    }

    public UserContentValues putZerohours(@Nullable Integer value) {
        mContentValues.put(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserContentValues putZerohoursNull() {
        mContentValues.putNull(UserColumns.ZEROHOURS);
        return this;
    }

    public UserContentValues putContracthours(@Nullable Integer value) {
        mContentValues.put(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserContentValues putContracthoursNull() {
        mContentValues.putNull(UserColumns.CONTRACTHOURS);
        return this;
    }

    public UserContentValues putEnabled(@Nullable Integer value) {
        mContentValues.put(UserColumns.ENABLED, value);
        return this;
    }

    public UserContentValues putEnabledNull() {
        mContentValues.putNull(UserColumns.ENABLED);
        return this;
    }

    public UserContentValues putVerified(@Nullable Integer value) {
        mContentValues.put(UserColumns.VERIFIED, value);
        return this;
    }

    public UserContentValues putVerifiedNull() {
        mContentValues.putNull(UserColumns.VERIFIED);
        return this;
    }

    public UserContentValues putPostalcode(@Nullable String value) {
        mContentValues.put(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserContentValues putPostalcodeNull() {
        mContentValues.putNull(UserColumns.POSTALCODE);
        return this;
    }

    public UserContentValues putPasswordexpire(@Nullable Long value) {
        mContentValues.put(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserContentValues putPasswordexpireNull() {
        mContentValues.putNull(UserColumns.PASSWORDEXPIRE);
        return this;
    }

    public UserContentValues putWorkscheduleId(@Nullable Long value) {
        mContentValues.put(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserContentValues putWorkscheduleIdNull() {
        mContentValues.putNull(UserColumns.WORKSCHEDULE_ID);
        return this;
    }

    public UserContentValues putEmployerId(@Nullable Long value) {
        mContentValues.put(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserContentValues putEmployerIdNull() {
        mContentValues.putNull(UserColumns.EMPLOYER_ID);
        return this;
    }

    public UserContentValues putRole(@Nullable Integer value) {
        mContentValues.put(UserColumns.ROLE, value);
        return this;
    }

    public UserContentValues putRoleNull() {
        mContentValues.putNull(UserColumns.ROLE);
        return this;
    }

    public UserContentValues putSynchronize(@Nullable Integer value) {
        mContentValues.put(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserContentValues putSynchronizeNull() {
        mContentValues.putNull(UserColumns.SYNCHRONIZE);
        return this;
    }
}

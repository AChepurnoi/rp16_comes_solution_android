package com.bionic.td_android.Data.Provider.user;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code user} table.
 */
public class UserCursor extends AbstractCursor implements UserModel {
    public UserCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(UserColumns._ID);
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
        Long res = getLongOrNull(UserColumns.SERVERID);
        return res;
    }

    /**
     * Get the {@code email} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getEmail() {
        String res = getStringOrNull(UserColumns.EMAIL);
        return res;
    }

    /**
     * Get the {@code password} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPassword() {
        String res = getStringOrNull(UserColumns.PASSWORD);
        return res;
    }

    /**
     * Get the {@code firstname} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getFirstname() {
        String res = getStringOrNull(UserColumns.FIRSTNAME);
        return res;
    }

    /**
     * Get the {@code lastname} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLastname() {
        String res = getStringOrNull(UserColumns.LASTNAME);
        return res;
    }

    /**
     * Get the {@code insertion} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getInsertion() {
        String res = getStringOrNull(UserColumns.INSERTION);
        return res;
    }

    /**
     * Get the {@code sex} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSex() {
        String res = getStringOrNull(UserColumns.SEX);
        return res;
    }

    /**
     * Get the {@code fourweekpayoff} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getFourweekpayoff() {
        Integer res = getIntegerOrNull(UserColumns.FOURWEEKPAYOFF);
        return res;
    }

    /**
     * Get the {@code zerohours} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getZerohours() {
        Integer res = getIntegerOrNull(UserColumns.ZEROHOURS);
        return res;
    }

    /**
     * Get the {@code contracthours} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getContracthours() {
        Integer res = getIntegerOrNull(UserColumns.CONTRACTHOURS);
        return res;
    }

    /**
     * Get the {@code enabled} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getEnabled() {
        Integer res = getIntegerOrNull(UserColumns.ENABLED);
        return res;
    }

    /**
     * Get the {@code verified} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getVerified() {
        Integer res = getIntegerOrNull(UserColumns.VERIFIED);
        return res;
    }

    /**
     * Get the {@code postalcode} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPostalcode() {
        String res = getStringOrNull(UserColumns.POSTALCODE);
        return res;
    }

    /**
     * Get the {@code passwordexpire} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getPasswordexpire() {
        Long res = getLongOrNull(UserColumns.PASSWORDEXPIRE);
        return res;
    }

    /**
     * Get the {@code workschedule_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getWorkscheduleId() {
        Long res = getLongOrNull(UserColumns.WORKSCHEDULE_ID);
        return res;
    }

    /**
     * Get the {@code employer_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getEmployerId() {
        String res = getStringOrNull(UserColumns.EMPLOYER_ID);
        return res;
    }

    /**
     * Get the {@code role} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getRole() {
        Integer res = getIntegerOrNull(UserColumns.ROLE);
        return res;
    }

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSynchronize() {
        Integer res = getIntegerOrNull(UserColumns.SYNCHRONIZE);
        return res;
    }
}

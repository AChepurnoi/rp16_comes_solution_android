package com.bionic.td_android.Data.Provider.user;

import com.bionic.td_android.Data.Provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * User entity
 */
public interface UserModel extends BaseModel {

    /**
     * Get the {@code serverid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getServerid();

    /**
     * Get the {@code email} value.
     * Can be {@code null}.
     */
    @Nullable
    String getEmail();

    /**
     * Get the {@code password} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPassword();

    /**
     * Get the {@code firstname} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFirstname();

    /**
     * Get the {@code lastname} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLastname();

    /**
     * Get the {@code insertion} value.
     * Can be {@code null}.
     */
    @Nullable
    String getInsertion();

    /**
     * Get the {@code sex} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSex();

    /**
     * Get the {@code tvt} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getTvt();

    /**
     * Get the {@code paidtimefortime} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getPaidtimefortime();

    /**
     * Get the {@code fourweekpayoff} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getFourweekpayoff();

    /**
     * Get the {@code zerohours} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getZerohours();

    /**
     * Get the {@code contracthours} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getContracthours();

    /**
     * Get the {@code enabled} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getEnabled();

    /**
     * Get the {@code verified} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getVerified();

    /**
     * Get the {@code postalcode} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPostalcode();

    /**
     * Get the {@code passwordexpire} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getPasswordexpire();

    /**
     * Get the {@code workschedule_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getWorkscheduleId();

    /**
     * Get the {@code employer_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getEmployerId();

    /**
     * Get the {@code role} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getRole();

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSynchronize();
}

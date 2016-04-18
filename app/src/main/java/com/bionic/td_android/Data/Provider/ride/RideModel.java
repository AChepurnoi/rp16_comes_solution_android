package com.bionic.td_android.Data.Provider.ride;

import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.BaseModel;

/**
 * Ride entity
 */
public interface RideModel extends BaseModel {

    /**
     * Get the {@code serverid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getServerid();

    /**
     * Get the {@code starttime} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getStarttime();

    /**
     * Get the {@code endtime} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getEndtime();

    /**
     * Get the {@code shiftid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getShiftid();
}

package com.bionic.td_android.Data.Provider.shift;

import com.bionic.td_android.Data.Provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Shift entity
 */
public interface ShiftModel extends BaseModel {

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
     * Get the {@code pause} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getPause();

    /**
     * Get the {@code userid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getUserid();

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSynchronize();
}

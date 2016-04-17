package com.bionic.td_android.Data.Provider.workschedule;

import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.BaseModel;

/**
 * WorkSchedule entity
 */
public interface WorkscheduleModel extends BaseModel {

    /**
     * Get the {@code serverid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getServerid();

    /**
     * Get the {@code creationtime} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getCreationtime();

    /**
     * Get the {@code sunday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSunday();

    /**
     * Get the {@code monday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMonday();

    /**
     * Get the {@code tuesday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTuesday();

    /**
     * Get the {@code wednesday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getWednesday();

    /**
     * Get the {@code thursday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getThursday();

    /**
     * Get the {@code friday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFriday();

    /**
     * Get the {@code saturday} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSaturday();

    /**
     * Get the {@code synchronize} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSynchronize();
}

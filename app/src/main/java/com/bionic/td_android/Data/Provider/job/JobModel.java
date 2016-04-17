package com.bionic.td_android.Data.Provider.job;

import android.support.annotation.Nullable;

import com.bionic.td_android.Data.Provider.base.BaseModel;

/**
 * Job entity
 */
public interface JobModel extends BaseModel {

    /**
     * Get the {@code userid} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getUserid();

    /**
     * Get the {@code jobid} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getJobid();
}

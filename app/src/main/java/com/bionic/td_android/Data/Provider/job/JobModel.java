package com.bionic.td_android.Data.Provider.job;

import com.bionic.td_android.Data.Provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    Long getJobid();
}

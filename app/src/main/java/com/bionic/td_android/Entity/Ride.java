package com.bionic.td_android.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Ride{

    @JsonProperty("id")
    private Long mId;
    private Date startTime;
    private Date endTime;
    @JsonIgnore
    private Shift shift;

    public Ride() {
        mId = 0l;
    }

    @JsonProperty("id")
    public Long getmId() {
        return mId;
    }

    @JsonProperty("id")
    public void setmId(Long id) {
        this.mId = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "Ride{" +
                " mId=" + mId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

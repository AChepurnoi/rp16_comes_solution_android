package com.bionic.td_android.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by user on 09.04.2016.
 */
public class Shift {
    @JsonProperty("id")
    private Long mId;
    private Date startTime;
    private Date endTime;
    @JsonIgnore
    private User user;
    private long pause;
    private List<Ride> rides;

    public Shift() {
        rides = new ArrayList<>();
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

    public long getPause() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause = pause;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "mId=" + mId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                ", rides=" + rides +
                ", pause=" + pause +
                '}';
    }
}


package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class WorkSchedule extends SugarRecord {
    @JsonProperty("id")
    private Long mId;


    private Date creationTime;

    private String sunday;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;



    @JsonIgnore
    @Override
    public List<Field> getTableFields() {
        return super.getTableFields();
    }

    @JsonIgnore
    @Override
    public String getSqlName() {
        return super.getSqlName();
    }

    public WorkSchedule() {
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


    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thurthday) {
        this.thursday = thurthday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "id=" + mId +
                ", creationTime=" + creationTime +
                ", sunday='" + sunday + '\'' +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                '}';
    }
}
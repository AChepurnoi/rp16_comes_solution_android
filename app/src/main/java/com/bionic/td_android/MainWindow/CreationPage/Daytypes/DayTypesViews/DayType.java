package com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews;

import com.bionic.td_android.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by Granium on 02.06.16.
 */
public class DayType {
    private Integer id;
    @JsonIgnore
    private User user;
    private DayTypeEnum dayTypeName;
    private Date startTime;
    private Date endTime;

    public DayType() {
        id = 0;

    }

    public enum DayTypeEnum {
        SICK_DAY,
        HOLIDAY,
        ATV_DAY,
        PAID_LEAVE_OF_ABSENCE,
        NON_PAID_LEAVE_OF_ABSENCE,
        STAND_TIME,
        CONSIGNMENT_FEE,
        STAND_OVER_ALLOWANCE,
        TIME_FOR_TIME_DAY

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DayTypeEnum getDayTypeName() {
        return dayTypeName;
    }

    public void setDayTypeName(DayTypeEnum dayTypeName) {
        this.dayTypeName = dayTypeName;
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


    @Override
    public String toString() {
        return "DayType{" +
                "id=" + id +
                ", user=" + user +
                ", dayTypeName=" + dayTypeName +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
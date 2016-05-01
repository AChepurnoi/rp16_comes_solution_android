package com.bionic.td_android.MainWindow.Overview.Utility;

import com.bionic.td_android.Entity.Shift;

import java.util.List;

/**
 * Created by user on 01.05.2016.
 */
public class WorkingWeekDTO {

    private int weekNumber;

    private int contractTime;

    private int overTime;

    private List<Shift> shiftList;

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getContractTime() {
        return contractTime;
    }

    public void setContractTime(int contractTime) {
        this.contractTime = contractTime;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    public List<Shift> getShiftList() { return shiftList; }

    public void setShiftList(List<Shift> shiftList) { this.shiftList = shiftList; }

    public String toString() {
        return "weekNumber = " + weekNumber + ", contractTime = " +
                contractTime + ", overTime = " + overTime + ", shiftList = " + shiftList;
    }
}

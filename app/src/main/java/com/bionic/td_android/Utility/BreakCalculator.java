package com.bionic.td_android.Utility;

import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 17.04.2016.
 */
public class BreakCalculator {

    private class SequentialWorkPeriod{
        private long start;
        private long end;

        public SequentialWorkPeriod(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public long getInterval(){return end - start;}
    }

    private Shift shift;
    private Date startTime;
    private Date endTime;

    private List<Ride> rides;

    public BreakCalculator(Shift shift) {
        this.shift = shift;
        startTime = shift.getStartTime();
        endTime = shift.getEndTime();
        rides = new ArrayList<>(shift.getRides());
        //Working time logically cant be more the INTEGER value
        Collections.sort(rides,(l,r) -> (int)(l.getStartTime().getTime() - r.getStartTime().getTime()));
    }


    private long getSequentialTimeFirstRide(){
        long period = 0;
        Date start = startTime;
        Date end = rides.get(0).getEndTime();
        int i = 1;
        for (; i <= rides.size(); i++) {
            if(i == rides.size() ){
                end = endTime;
                break;
            }
            if(rides.get(i).getStartTime().equals(end)){
                end = rides.get(i).getEndTime();
            }else break;
        }

        while (i > 0){
            rides.remove(0);
            i--;
        }

        period = end.getTime() - start.getTime();
        return period;
    }

    private long getSequentialTimeNextRide(){
        long period = 0;
        if(rides.size() == 0)return 0;
        Date start = rides.get(0).getStartTime();
        Date end = rides.get(0).getEndTime();
        int i = 0;
        for (;  i <= rides.size() ; i++) {
            if(i == rides.size() -1 ){
                end = endTime;
                break;
            }
            if(rides.get(i).getStartTime().equals(end)){
                end = rides.get(i).getEndTime();
            }else break;
        }

        while (i >= 0){
            rides.remove(0);
            i--;
        }

        period = end.getTime() - start.getTime();
        return period;



    }

    private long getPauseTime(long duration){

        if(duration >= 270 && duration <  450)return 30;
        if(duration >= 450 && duration <  630)return 60;
        if(duration >= 630 && duration <  810)return 90;
        if(duration >= 810 && duration <  990)return 120;
        if(duration >= 990)return 150;
        return 0;
    }

    private long getMinutes(long time){
        return time / (60 * 1000);
    }
    private long getHours(long time){
        return  time / (60 * 60 * 1000);
    }

    public long calculate(){
        long res = 0;
        res += getPauseTime(getMinutes(getSequentialTimeFirstRide()));

        long tmp = 0;
        do{
            tmp = getPauseTime(getMinutes(getSequentialTimeNextRide()));
            res += tmp;
        }while (tmp != 0);


        return res;
    }



}

package com.bionic.td_android.Data.Utility;

import com.bionic.td_android.Data.Provider.job.JobContentValues;
import com.bionic.td_android.Data.Provider.ride.RideContentValues;
import com.bionic.td_android.Data.Provider.ride.RideModel;
import com.bionic.td_android.Data.Provider.shift.ShiftContentValues;
import com.bionic.td_android.Data.Provider.shift.ShiftModel;
import com.bionic.td_android.Data.Provider.user.UserContentValues;
import com.bionic.td_android.Data.Provider.user.UserModel;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleContentValues;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleModel;
import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.UserRoleEnum;
import com.bionic.td_android.Entity.WorkSchedule;

import java.util.Date;

/**
 * Created by user on 16.04.2016.
 */
public class CVHelper {


    public UserContentValues build(User user){
        UserContentValues values = new UserContentValues();
        values.putServerid(user.getmId());
        values.putEmail(user.getEmail());
        values.putInsertion(user.getInsertion());
        values.putPassword(user.getPassword());
        values.putLastname(user.getLastName());
        values.putFirstname(user.getFirstName());
        values.putSex(user.getSex());
        values.putFourweekpayoff(user.isFourWeekPayOff() ? 1 : 0);
        values.putZerohours(user.isZeroHours() ? 1 : 0);
        values.putContracthours(user.getContractHours());
        values.putTvt(user.getTvt());
        values.putPaidtimefortime(user.isPaidTimeForTime() ? 1 : 0);
        values.putEnabled(user.isEnabled() ? 1 : 0);
        values.putVerified(user.isVerified() ? 1 : 0);
        values.putPostalcode(user.getPostalCode());
        if(user.getPasswordExpire() != null)
            values.putPasswordexpire(user.getPasswordExpire().getTime());

        return values;
    }

    public WorkscheduleContentValues build(WorkSchedule schedule){
        WorkscheduleContentValues values = new WorkscheduleContentValues();
        values.putServerid(schedule.getmId());
        if(schedule.getCreationTime() != null)
            values.putCreationtime(schedule.getCreationTime().getTime());
        values.putMonday(schedule.getMonday());
        values.putTuesday(schedule.getTuesday());
        values.putWednesday(schedule.getWednesday());
        values.putThursday(schedule.getThursday());
        values.putFriday(schedule.getFriday());
        values.putSaturday(schedule.getSaturday());
        values.putSunday(schedule.getSunday());


        return values;


    }

    public User buildUser(UserModel userModel){
        User user = new User();
        user.setmId(userModel.getServerid());
        user.setFirstName(userModel.getFirstname());
        user.setInsertion(userModel.getInsertion());
        user.setLastName(userModel.getLastname());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setSex(userModel.getSex());
        user.setPostalCode(userModel.getPostalcode());
        user.setFourWeekPayOff(userModel.getFourweekpayoff() > 0 ? true : false);
        user.setZeroHours(userModel.getZerohours() > 0 ? true : false);
        user.setContractHours(userModel.getContracthours());
        user.setEnabled(userModel.getEnabled() > 0 ? true : false);
        user.setPostalCode(userModel.getPostalcode());
        user.setTvt(userModel.getTvt());
        user.setPaidTimeForTime(userModel.getPaidtimefortime() > 0 ? true : false);
        user.setPasswordExpire(new Date(userModel.getPasswordexpire()));
        user.setVerified(userModel.getVerified() > 0 ? true : false);
        user.setRole(UserRoleEnum.ADMIN);
        return user;

    }
    public WorkSchedule buildWorkschedule(WorkscheduleModel model){

        WorkSchedule schedule = new WorkSchedule();
        schedule.setmId(model.getServerid());
        schedule.setCreationTime(new Date(model.getCreationtime()));
        schedule.setMonday(model.getMonday());
        schedule.setTuesday(model.getTuesday());
        schedule.setWednesday(model.getWednesday());
        schedule.setThursday(model.getThursday());
        schedule.setFriday(model.getFriday());
        schedule.setSaturday(model.getSaturday());
        schedule.setSunday(model.getSunday());

        return schedule;

    }


    public JobContentValues build(Integer job, Long userId){
        JobContentValues values = new JobContentValues();
        values.putUserid(userId);
        values.putJobid(Long.valueOf(job));
        return values;


    }


    public ShiftContentValues build(long userId,Shift shift, boolean isLocal){
        ShiftContentValues values = new ShiftContentValues();
        values.putUserid(userId);
        values.putStarttime(shift.getStartTime().getTime());
        values.putEndtime(shift.getEndTime().getTime());
        values.putPause(shift.getPause());
        values.putSynchronize(isLocal ? 2 : 0);
        values.putServerid(shift.getmId());

        return values;

    }

    public RideContentValues build(long shiftId, Ride ride){

        RideContentValues values = new RideContentValues();
        values.putServerid(ride.getmId());
        values.putShiftid(shiftId);
        values.putStarttime(ride.getStartTime().getTime());
        values.putEndtime(ride.getEndTime().getTime());

        return values;


    }

    public Shift buildShift(ShiftModel values){
        Shift shift = new Shift();
        shift.setmId(values.getServerid());
        shift.setStartTime(new Date(values.getStarttime()));
        shift.setEndTime(new Date(values.getEndtime()));
        shift.setPause(values.getPause());
        return shift;
    }

    public Ride buildRide(RideModel values){
        Ride ride = new Ride();
        ride.setStartTime(new Date(values.getStarttime()));
        ride.setEndTime(new Date(values.getEndtime()));
        ride.setmId(values.getServerid());
        return ride;

    }
}

package com.bionic.td_android.Data;

import android.content.Context;

import com.bionic.td_android.Data.Provider.job.JobContentValues;
import com.bionic.td_android.Data.Provider.job.JobCursor;
import com.bionic.td_android.Data.Provider.job.JobSelection;
import com.bionic.td_android.Data.Provider.ride.RideContentValues;
import com.bionic.td_android.Data.Provider.ride.RideCursor;
import com.bionic.td_android.Data.Provider.ride.RideSelection;
import com.bionic.td_android.Data.Provider.shift.ShiftContentValues;
import com.bionic.td_android.Data.Provider.shift.ShiftCursor;
import com.bionic.td_android.Data.Provider.shift.ShiftSelection;
import com.bionic.td_android.Data.Provider.user.UserContentValues;
import com.bionic.td_android.Data.Provider.user.UserCursor;
import com.bionic.td_android.Data.Provider.user.UserSelection;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleContentValues;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleCursor;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleModel;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleSelection;
import com.bionic.td_android.Data.Utility.CVHelper;
import com.bionic.td_android.Entity.Ride;
import com.bionic.td_android.Entity.Shift;
import com.bionic.td_android.Entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16.04.2016.
 */
public class DbManager {


    public DbManager(Context context) {
        this.context = context;
    }

    private Context context;
    private CVHelper helper = new CVHelper();

    public void clear(){
        new UserSelection().delete(context);
        new WorkscheduleSelection().delete(context);
        new JobSelection().delete(context);
        new ShiftSelection().delete(context);
        new RideSelection().delete(context);

    }

    private void clearUserWorkschedule(){
        new UserSelection().delete(context);
        new WorkscheduleSelection().delete(context);
        new JobSelection().delete(context);
    }
    public void loginClear(User user){
        User current = loadUser();
        if(current.getmId().equals(user.getmId()))clearUserWorkschedule();
        else clear();

    }

    public void save(User user){

        //Inserting user
        UserContentValues userContentValues = helper.build(user);
        WorkscheduleContentValues workscheduleContentValues = null;
        userContentValues.insert(context);

        //Do we need workschedule
        if(!user.isZeroHours()) {
            workscheduleContentValues = helper.build(user.getWorkSchedule());
        }

        //Inserting workschedule
        if(workscheduleContentValues != null)
            workscheduleContentValues.insert(context);

        //Inserting jobs
        if(user.getJobs() != null){
            for (Integer integer : user.getJobs()) {
                JobContentValues job = helper.build(integer,user.getmId());
                job.insert(context);
            }
        }

        //Binding workschedule to user
        if(!user.isZeroHours())
            new UserContentValues()
                    .putWorkscheduleId(user.getWorkSchedule().getmId())
                    .update(context, new UserSelection().serverid(user.getmId()));
    }

    public void update(User user){

        //Updating user, user is present always
        UserContentValues userContentValues = helper.build(user);
        userContentValues.update(context,new UserSelection().serverid(user.getmId()));
        WorkscheduleContentValues workscheduleContentValues = null;

        //Cleanin db ( we cant be sure, had user or not workschedule and certain jobs before update)
        new WorkscheduleSelection().delete(context);
        new JobSelection().delete(context);

        //Do we need workschedule
        if(!user.isZeroHours()) {
            workscheduleContentValues = helper.build(user.getWorkSchedule());
        }

        //Inserting workschedule
        if(workscheduleContentValues != null)
            workscheduleContentValues.insert(context);


        //Inserting jobs
        if(user.getJobs() != null){
            for (Integer integer : user.getJobs()) {
                JobContentValues job = helper.build(integer,user.getmId());
                job.insert(context);
            }
        }

        //Binding workschedule to user
        if(!user.isZeroHours())
            new UserContentValues()
                    .putWorkscheduleId(user.getWorkSchedule().getmId())
                    .update(context, new UserSelection().serverid(user.getmId()));

    }

    public User loadUser(){
        User res = null;
        UserSelection selection = new UserSelection();
        UserCursor user = selection.query(context);
        if(!user.moveToFirst()) return null;
        res = helper.buildUser(user);
        WorkscheduleModel workschedule = null;
        if(user.getZerohours().equals(0)) {
            WorkscheduleCursor workscheduleCursor = new WorkscheduleSelection().serverid(user.getWorkscheduleId()).query(context);
            if(workscheduleCursor.moveToFirst()) {
                workschedule = workscheduleCursor;
                res.setWorkSchedule(helper.buildWorkschedule(workschedule));
            }
        }

        JobCursor jobs = new JobSelection().userid(res.getmId()).query(context);
        List<Integer> userJobs = new ArrayList<>();
        while (jobs.moveToNext()){
            userJobs.add(jobs.getJobid().intValue());
        }
        res.setJobs(userJobs);

        return res;
    }

    public Shift loadLocalShift(){

        ShiftSelection shiftSelection = new ShiftSelection();
        ShiftCursor shifts = shiftSelection.synchronize(2).query(context);
        RideCursor rides = new RideSelection().shiftid(0l).query(context);

        if(!shifts.moveToNext())return null;
        Shift res = helper.buildShift(shifts);
        List<Ride> rideList = new ArrayList<>();
        while (rides.moveToNext()){
            Ride obj = helper.buildRide(rides);
//            obj.createTimePacks();
            obj.setShift(res);
            rideList.add(obj);
        }
        res.setRides(rideList);
//        if(!res.validate())return null;
        return res;
    }
    public void saveUpdateLocalShift(Shift shift){

        clearLocalShift();
        User user = loadUser();
        ShiftContentValues shiftVal = helper.build(user.getmId(), shift, true);
        List<RideContentValues> ridesVal = new ArrayList<>();
        for (Ride ride : shift.getRides()) {
            ridesVal.add(helper.build(shift.getmId(),ride));
        }

        shiftVal.insert(context);
        for (RideContentValues values : ridesVal) {
            values.insert(context);
        }


    }

    public void clearLocalShift(){

        ShiftSelection shiftSelection = new ShiftSelection();
        RideSelection rideSelection = new RideSelection();
        shiftSelection.serverid(0l).delete(context);
        rideSelection.shiftid(0l).delete(context);
    }

    public void clearShifts(){
        ShiftSelection shiftSelection = new ShiftSelection();
        RideSelection rideSelection = new RideSelection();
        shiftSelection.serveridNot(0l).delete(context);
        rideSelection.shiftidNot(0l).delete(context);
    }

    public void saveShifts(List<Shift> shifts){
        clearShifts();
        User user = loadUser();
        for (Shift shift : shifts) {
            ShiftContentValues shiftVal = helper.build(user.getmId(), shift, true);
            List<RideContentValues> ridesVal = new ArrayList<>();
            for (Ride ride : shift.getRides()) {
                ridesVal.add(helper.build(shift.getmId(),ride));
            }
            shiftVal.insert(context);
            for (RideContentValues values : ridesVal) {
                values.insert(context);
            }
        }
    }

    public List<Shift> loadShifts(){
        User user = loadUser();
        List<Shift> result = new ArrayList<>();
        ShiftSelection shiftSelection = new ShiftSelection();
        ShiftCursor shifts = shiftSelection.userid(user.getmId()).query(context);
        while (shifts.moveToNext()){
            Shift res = helper.buildShift(shifts);
            RideCursor rides = new RideSelection().shiftid(res.getmId()).query(context);
            List<Ride> rideList = new ArrayList<>();
            while (rides.moveToNext()){
                Ride obj = helper.buildRide(rides);
//                obj.createTimePacks();
                obj.setShift(res);
                rideList.add(obj);
            }
            res.setRides(rideList);
            result.add(res);
        }

        return result;
    }

}

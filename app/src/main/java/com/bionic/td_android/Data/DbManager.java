package com.bionic.td_android.Data;

import android.content.Context;

import com.bionic.td_android.Data.Provider.job.JobContentValues;
import com.bionic.td_android.Data.Provider.job.JobCursor;
import com.bionic.td_android.Data.Provider.job.JobSelection;
import com.bionic.td_android.Data.Provider.user.UserContentValues;
import com.bionic.td_android.Data.Provider.user.UserCursor;
import com.bionic.td_android.Data.Provider.user.UserSelection;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleContentValues;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleCursor;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleModel;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleSelection;
import com.bionic.td_android.Data.Utility.CVHelper;
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
            userJobs.add(jobs.getJobid());
        }
        res.setJobs(userJobs);

        return res;
    }


}

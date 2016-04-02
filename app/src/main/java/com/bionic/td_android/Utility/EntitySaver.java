package com.bionic.td_android.Utility;

import com.bionic.td_android.Entity.Employer;
import com.bionic.td_android.Entity.Job;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.WorkSchedule;

import java.util.List;

/**
 * Created by user on 30.03.2016.
 */
public class EntitySaver {


    public static void save(User user){
        User.deleteAll(User.class);
        WorkSchedule.deleteAll(WorkSchedule.class);
        Job.deleteAll(Job.class);
        Employer.deleteAll(Employer.class);

        if(user.getWorkSchedule() != null)user.getWorkSchedule().save();

        if(user.getJobs() != null)
            for (Job job : user.getJobs())
                job.save();

        if(user.getEmployer() != null)
            user.getEmployer().save();

        user.save();
    }

    public static User getUser(){
        User user;
        List<User> users = User.listAll(User.class);

        user = users.get(users.size() - 1);
        List<Job> jobs = Job.listAll(Job.class);
        user.setJobs(jobs);

        return user;

    }
}

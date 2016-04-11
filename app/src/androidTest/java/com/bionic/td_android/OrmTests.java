package com.bionic.td_android;

import android.test.AndroidTestCase;
import android.util.Log;

import com.bionic.td_android.Entity.Job;
import com.bionic.td_android.Entity.User;
import com.bionic.td_android.Entity.UserRoleEnum;
import com.bionic.td_android.Entity.WorkSchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 30.03.2016.
 */
public class OrmTests extends AndroidTestCase {



//    public void test() {
//        Log.e("Bionic", "Start ORM test");
//        final String url = API.GET_USER();
//        String login = "granium90@gmail.com";
//        String pass = "qwerty";
//
//        String encoded = Base64.encodeToString((login + ":" + pass).getBytes(), 0);
//        Log.e("Bionic", encoded);
//        SyncHttpClient client = new SyncHttpClient();
//
//        client.addHeader("Authorization", "Basic " + encoded);
//
//
//        client.get(mContext, url, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.e("Bionic", "Fail " + statusCode);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                User user = null;
//                try {
//                    user = new ObjectMapper().readValue(responseString, User.class);
//                    Log.e("Bionic", user.toString());
//                    user.getEmployer().save();
//                    for (Job job : user.getJobs()) {
//                        job.save();
//                    }
//                    user.getWorkSchedule().save();
//                    user.save();
//
//                    Log.e("Bionic", "Saved");
//
//                    List<User> users = User.listAll(User.class);
//                    assertNotNull(users);
//                    Log.e("Bionic", users.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e("Bionic",e.getMessage());
//                }
//            }
//        });
//
//
//    }

/*
    public void test(){

        List<User> users_clear = User.listAll(User.class);
        if(users_clear != null && !users_clear.isEmpty()){
            for (User user : users_clear) {
                user.delete();
            }
        }

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Stepan");
        List<Job> jobs = new ArrayList<>();
        Job job = new Job();
        job.setJobName("Test job");
        jobs.add(job);


        user.setJobs(jobs);
        WorkSchedule schedule = new WorkSchedule();
        schedule.setMonday("1");
        schedule.setCreationTime(new Date());
        user.setWorkSchedule(schedule);
        user.setPasswordExpire(new Date());
        user.setRole(UserRoleEnum.USER);
        for (Job job1 : user.getJobs()) {
            job1.save();
        }
        user.getWorkSchedule().save();


        user.save();
        List<User> users = User.listAll(User.class);
        assertNotNull(users);
        Log.e("Bionic",users.toString());

    }
*/

    public void test(){

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Stepan");
        List<Job> jobs = new ArrayList<>();
        Job job = new Job();
        job.setJobName("Test job");
        jobs.add(job);


        user.setJobs(jobs);
        WorkSchedule schedule = new WorkSchedule();
        schedule.setMonday("1");
        schedule.setCreationTime(new Date());
        user.setWorkSchedule(schedule);
        user.setPasswordExpire(new Date());
        user.setRole(UserRoleEnum.USER);
        for (Job job1 : user.getJobs()) {
            job1.save();
        }
        user.getWorkSchedule().save();


        user.save();
        List<User> users = User.listAll(User.class);
        assertNotNull(users);
        Log.e("Bionic", users.toString());

    }


}

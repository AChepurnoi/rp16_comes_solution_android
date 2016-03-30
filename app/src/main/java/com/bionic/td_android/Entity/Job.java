package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */
/**
 *
 */

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

/**
 * @author vitalii.levash
 *
 */
public class Job extends SugarRecord{
    private Long id;
    private String jobName;


    @Ignore
    private List<User> users;

    public Job() {
        id = 0l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", users=" + users +
                '}';
    }
}
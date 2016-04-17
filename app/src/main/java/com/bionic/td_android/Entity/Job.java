package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */
/**
 *
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author vitalii.levash
 *
 */
public class Job{
    @JsonProperty("id")
    private Long mId;
    private String jobName;


    public Job(String jobName) {
        mId = 0l;
        this.jobName = jobName;
    }

    private List<User> users;

    public Job() {
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
                "id=" + mId +
                ", jobName='" + jobName + '\'' +
                ", users=" + users +
                '}';
    }
}
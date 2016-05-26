package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 *
 * @author vitalii.levash
 * @author Dima Budko
 * @version 0.3
 */

public class User{


    @JsonProperty("id")
    private Long mId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String insertion;
    private String sex;
    private boolean fourWeekPayOff;
    private boolean zeroHours;
    private int contractHours;
    private int tvt;

    private boolean paidTimeForTime;;
    private boolean enabled;
    private boolean verified;
    private String postalCode;
    private Date passwordExpire;
    private WorkSchedule workSchedule;


    private UserRoleEnum role;
    private Employer employer;
    private List<Integer> jobs;



    public User() {
        mId = 0l;
    }

    @JsonProperty("id")
    public Long getmId() {
        return mId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public String getSex() {
        return sex;
    }

    public int getTvt() {
        return tvt;
    }

    public void setTvt(int tvt) {
        this.tvt = tvt;
    }

    public boolean isPaidTimeForTime() {
        return paidTimeForTime;
    }

    public void setPaidTimeForTime(boolean paidTimeForTime) {
        this.paidTimeForTime = paidTimeForTime;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isFourWeekPayOff() {
        return fourWeekPayOff;
    }

    public void setFourWeekPayOff(boolean fourWeekPayOff) {
        this.fourWeekPayOff = fourWeekPayOff;
    }

    public boolean isZeroHours() {
        return zeroHours;
    }

    public void setZeroHours(boolean zeroHours) {
        this.zeroHours = zeroHours;
    }

    public int getContractHours() {
        return contractHours;
    }

    public void setContractHours(int contractHours) {
        this.contractHours = contractHours;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Integer> getJobs() {
        return jobs;
    }

    public void setJobs(List<Integer> jobs) {
        this.jobs = jobs;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getPasswordExpire() {
        return passwordExpire;
    }

    public void setPasswordExpire(Date passwordExpire) {
        this.passwordExpire = passwordExpire;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    @JsonProperty("id")
    public void setmId(Long id) {
        this.mId = id;
    }

    public WorkSchedule getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(WorkSchedule workSchedule) {
        this.workSchedule = workSchedule;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "workSchedule=" + workSchedule +
                ", passwordExpire=" + passwordExpire +
                ", postalCode='" + postalCode + '\'' +
                ", verified=" + verified +
                ", enabled=" + enabled +
                ", paidTimeForTime=" + paidTimeForTime +
                ", tvt=" + tvt +
                ", contractHours=" + contractHours +
                ", zeroHours=" + zeroHours +
                ", fourWeekPayOff=" + fourWeekPayOff +
                ", sex='" + sex + '\'' +
                ", insertion='" + insertion + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mId=" + mId +
                '}';
    }
}
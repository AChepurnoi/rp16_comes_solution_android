package com.bionic.td_android.Entity;

/**
 * Created by user on 26.03.2016.
 */

public class PasswordsDTO {
    private String oldPassword;
    private String newPassword;

    public PasswordsDTO() {
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordsDTO{" +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}

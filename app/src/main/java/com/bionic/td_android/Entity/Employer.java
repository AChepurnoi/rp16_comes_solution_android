package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */
/**
 *
 */

import com.orm.SugarRecord;

/**
 * @author vitalii.levash
 * @version 0.1
 */
public class Employer extends SugarRecord {
    private Long id;
    private String name;

    public Employer(){
        id = 0l;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
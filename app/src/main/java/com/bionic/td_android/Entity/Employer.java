package com.bionic.td_android.Entity;

/**
 * Created by user on 19.03.2016.
 */
/**
 *
 */

/**
 * @author vitalii.levash
 * @version 0.1
 */
public class Employer {
    private Integer id;
    private String name;

    public Employer(){ }


    public Integer getId() {
        return id;
    }


    public void setId(int id) {
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
package com.noveogroup.task2;

import java.io.Serializable;

/**
 * Created by Yuri on 26.07.2014.
 */
public class Employee implements Serializable {
    private String fname;
    private String lname;
    private String skills;

    public Employee(String fname, String lname, String skills) {
        this.fname = fname;
        this.lname = lname;
        this.skills = skills;
    }
    
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}

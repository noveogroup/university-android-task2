package com.noveogroup.task2;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee {

    private String mName;
    private String mSurname;
    private String mSkills;

    public Employee(String name, String surname) {
        this.mName = name;
        this.mSurname = surname;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getSkills() {
        return mSkills;
    }

    public void setSkills(String skills) {
        this.mSkills = skills;
    }

    @Override
    public String toString() {
        return mName + " " + mSurname;
    }

}

package com.noveogroup.task2;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

    private static final String EMPTY = "";

    private String mName;
    private String mSurname;
    private String mSkills = EMPTY;

    public Employee(String name, String surname) {
        this.mName = name;
        this.mSurname = surname;
    }

    private Employee(Parcel parcel) {
        mName = parcel.readString();
        mSurname = parcel.readString();
        mSkills = parcel.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mSurname);
        dest.writeString(mSkills);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

}

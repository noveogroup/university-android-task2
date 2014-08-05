package com.noveogroup.task2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Arseny on 26.07.2014.
 */
public class Employee implements Parcelable {
    private String name;
    private String surname;
    private String skills;

    public Employee(String name, String surname, String newSkills) {
        this.name = name;
        this.surname = surname;
        this.skills = newSkills;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getSkills() {
        return this.skills;
    }

    public void setSkills(String skils) {
        this.skills = skils;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(surname);
        out.writeString(skills);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int i) {
            return new Employee[i];
        }
    };

    private Employee(Parcel in) {
        name = in.readString();
        surname = in.readString();
        skills = in.readString();

    }
}

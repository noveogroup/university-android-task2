package com.noveogroup.task2;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable{
    private String name;
    private String surname;
    private String skills;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    private Employee(Parcel parcel) {
        name = parcel.readString();
        surname = parcel.readString();
        skills = parcel.readString();
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(skills);
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

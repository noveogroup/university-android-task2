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

    public Employee(Parcel parcel) {
        String[] data = new String[3];
        parcel.readStringArray(data);
        name = data[0];
        surname = data[1];
        skills = data[2];
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
        dest.writeStringArray(new String[]{name, surname, skills});
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

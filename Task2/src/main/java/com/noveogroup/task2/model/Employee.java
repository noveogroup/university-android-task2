package com.noveogroup.task2.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Employee implements Parcelable{
    private String name;
    private String surname;
    private String skills;

    public Employee(String name, String surname, String skills) {
        this.name = name;
        this.surname = surname;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringArray(new String[] {name, surname, skills});
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            String[] data = new String[3];
            parcel.readStringArray(data);
            return new Employee(data[0], data[1], data[2]);
        }

        @Override
        public Object[] newArray(int size) {
            return new Employee[size];
        }
    };

    public void setSkills(String newSkills) {
        skills = newSkills;
    }
}

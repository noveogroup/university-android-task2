package com.noveogroup.task2;

import android.os.Parcel;
import android.os.Parcelable;


public class Employee implements Parcelable {
	private final String name;
	private final String surname;
	private String skills;

	public Employee(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	private Employee(Parcel parcel) {
		this(parcel.readString(), parcel.readString());
		this.setSkills(parcel.readString());
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

	public void setSkills(String skills) {
		this.skills = skills;
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

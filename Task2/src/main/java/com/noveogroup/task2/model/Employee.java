package com.noveogroup.task2.model;

import java.util.ArrayList;

/**
 * Created by Arseny on 26.07.2014.
 */
public class Employee {
    private String name;
    private String surname;
    private ArrayList<String> skills = new ArrayList<String>();

    public Employee(String name, String surname, ArrayList<String> newSkills) {
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

    public ArrayList<String> getSkills() {
        return this.skills;
    }
}

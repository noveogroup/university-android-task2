package com.noveogroup.task2.model;

import java.util.ArrayList;

/**
 * Created by Arseny on 26.07.2014.
 */
public class Employee {
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
}

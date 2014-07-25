package com.noveogroup.task2;

import java.util.ArrayList;

/**
 * Created by Arseny on 26.07.2014.
 */
public class Employee {
    private String name;
    private String surname;
    private ArrayList<String> skills;

    public Employee(String name, String surname, ArrayList<String> skills) {
        this.name = name;
        this.surname = surname;
        this.skills.addAll(skills);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void addSkills(ArrayList<String> skills) {
        this.skills.addAll(skills);
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
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

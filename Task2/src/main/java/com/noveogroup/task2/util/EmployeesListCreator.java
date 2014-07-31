package com.noveogroup.task2.util;

import com.noveogroup.task2.model.Employee;

import java.util.ArrayList;

public final class EmployeesListCreator {
    private EmployeesListCreator() {
        throw new UnsupportedOperationException(this.toString() + "can not be created.");
    }

    public static ArrayList<Employee> getEmployeesList(int n) {
        ArrayList<Employee> employeesList = new ArrayList<Employee>();

        for(int i = 1; i <= n; ++i) {
            Employee tempEmployee = new Employee("Name " + i, "Surname " + i, "Skills " + i);
            employeesList.add(tempEmployee);
        }

        return employeesList;
    }
}

package com.noveogroup.task2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
    public static final String EMPLOYEES_KEY = "com.noveogroup.task2.employees";
    public static final String POSITION_KEY = "com.noveogroup.task2.position";

    private SkillsFragment skillsFragment;
    private int selectedPosition = -1;
    private Employee[] employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ListView employeesList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        employeesList = (ListView) findViewById(R.id.employees_list);

        if(savedInstanceState != null) {
            employees = (Employee[]) savedInstanceState.getParcelableArray(EMPLOYEES_KEY);
        }
        else {
            employees = getEmployees();
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            View header = getLayoutInflater().inflate(R.layout.header_fragment_layout, employeesList, false);
            employeesList.addHeaderView(header);
            skillsFragment = (SkillsFragment) fm.findFragmentById(R.id.fragment_header);
        }
        else {
            skillsFragment = (SkillsFragment) fm.findFragmentById(R.id.fragment);
        }
        fm.beginTransaction().hide(skillsFragment).commit();

        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(
                this, android.R.layout.simple_list_item_1, employees);
        employeesList.setAdapter(adapter);
        employeesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    selectedPosition = position - employeesList.getHeaderViewsCount();
                }
                else {
                    selectedPosition = position;
                }
                showEmployee();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedPosition = savedInstanceState.getInt(POSITION_KEY);
        if(selectedPosition >= 0) {
            showEmployee();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION_KEY, selectedPosition);
        outState.putParcelableArray(EMPLOYEES_KEY, employees);
    }

    private void showEmployee() {
        skillsFragment.setEmployee(employees[selectedPosition]);
        getSupportFragmentManager().beginTransaction()
                .show(skillsFragment)
                .commit();
    }

    private Employee[] getEmployees() {
        return new Employee[] {
                new Employee("Vasya","Pupkin"),
                new Employee("Petya","Vasechkin"),
                new Employee("Serezha","Balobolkin"),
                new Employee("Krolik","Seledkin"),
                new Employee("Vanya","Cherep"),
                new Employee("Miha","Siplyj"),
        };
    }
}

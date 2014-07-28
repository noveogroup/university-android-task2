package com.noveogroup.task2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
    private SkillsFragment skillsFragment;
    private ListView employeesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fm = getSupportFragmentManager();
        skillsFragment = (SkillsFragment) fm.findFragmentById(R.id.fragment);
        employeesList = (ListView) findViewById(R.id.employees_list);

        fm.beginTransaction().hide(skillsFragment).commit();

        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(
                this, android.R.layout.simple_list_item_1, getEmployees());
        employeesList.setAdapter(adapter);

        employeesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) employeesList.getAdapter().getItem(position);
                skillsFragment.setEmployee(employee);
                fm.beginTransaction().show(skillsFragment).commit();

            }
        });
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

package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private View headerView;
    private View headerEdit;
    private boolean isHeaderInit = false;
    private boolean isEditMode = false;
    private int currentEmployer;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerView = getLayoutInflater().inflate(R.layout.header_view, null);
        headerEdit = getLayoutInflater().inflate(R.layout.header_edit, null);
        listView = (ListView) findViewById(R.id.list);

        for (int i = 0; i < 50; i++) {
            employees.add(new Employee("fname" + i, "lname" + i, "skills" + i));
        }

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, employees);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup employeeInfoContainer = (ViewGroup) findViewById(R.id.employee_info);
                if(employeeInfoContainer != null) {
                    employeeInfoContainer.removeAllViews();
                    employeeInfoContainer.addView(headerView);
                }
                else {
                    if (!isHeaderInit) {
                        listView.addHeaderView(headerView);
                        isHeaderInit = true;
                    } else {
                        position --;
                    }
                }

                currentEmployer = position;

                updateHeader();
            }
        });
    }

    public void editSkillsButtonOnClick(View v) {
        ViewGroup employeeInfoContainer = (ViewGroup) findViewById(R.id.employee_info);
        if(employeeInfoContainer != null) {
            employeeInfoContainer.removeAllViews();
            employeeInfoContainer.addView(headerEdit);
        } else {
            listView.removeHeaderView(headerView);
            listView.addHeaderView(headerEdit);
        }

        isEditMode = true;
        updateHeader();
    }

    public void saveSkillsButtonOnClick(View v) {

        TextView skills = (TextView) headerEdit.findViewById(R.id.edit_skills_view);
        employees.get(currentEmployer).skills = skills.getText().toString();

        ViewGroup employeeInfoContainer = (ViewGroup) findViewById(R.id.employee_info);
        if(employeeInfoContainer != null) {
            employeeInfoContainer.removeAllViews();
            employeeInfoContainer.addView(headerView);
        } else {
            listView.removeHeaderView(headerEdit);
            listView.addHeaderView(headerView);
        }

        isEditMode = false;
        updateHeader();
    }

    private void updateHeader() {
        Employee employee = employees.get(currentEmployer);

        if (!isEditMode) {
            TextView fname = (TextView) headerView.findViewById(R.id.fname);
            TextView lname = (TextView) headerView.findViewById(R.id.lname);
            TextView skills = (TextView) headerView.findViewById(R.id.skills);

            fname.setText(employee.fname);
            lname.setText(employee.lname);
            skills.setText(employee.skills);
        } else {
            TextView fname = (TextView) headerEdit.findViewById(R.id.fname);
            TextView lname = (TextView) headerEdit.findViewById(R.id.lname);
            TextView skills = (TextView) headerEdit.findViewById(R.id.edit_skills_view);

            fname.setText(employee.fname);
            lname.setText(employee.lname);
            skills.setText(employee.skills);
        }
    }
}

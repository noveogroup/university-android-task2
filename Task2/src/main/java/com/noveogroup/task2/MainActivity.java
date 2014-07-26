package com.noveogroup.task2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private View headerView;
    private View headerEdit;
    private boolean isHeaderInit = false;
    private boolean isEditMode = false;
    private int currentEmployer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        headerView = getLayoutInflater().inflate(R.layout.header_view, null);
        headerEdit = getLayoutInflater().inflate(R.layout.header_edit, null);

        for (int i = 0; i < 50; i++) {
            employees.add(new Employee("fname" + i, "lname" + i, "skills" + i));
        }

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, employees);
        setListAdapter(adapter);
    }

    public void editSkillsButtonOnClick(View v) {
        ListView listView = getListView();
        listView.removeHeaderView(headerView);
        listView.addHeaderView(headerEdit);
        isEditMode = true;
        updateHeader();
    }

    public void saveSkillsButtonOnClick(View v) {

        TextView skills = (TextView) headerEdit.findViewById(R.id.edit_skills_view);
        employees.get(currentEmployer).skills = skills.getText().toString();

        ListView listView = getListView();
        listView.removeHeaderView(headerEdit);
        listView.addHeaderView(headerView);
        isEditMode = false;
        updateHeader();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (!isHeaderInit) {
            ListView listView = getListView();
            listView.addHeaderView(headerView);
            isHeaderInit = true;
        } else {
            position --;
        }

        currentEmployer = position;

        updateHeader();
    }

    private void updateHeader() {
        Employee employee = (Employee) this.getListAdapter().getItem(currentEmployer);

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

package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private int currentEmployer = -1;
    private ListView listView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentEmployer", currentEmployer);
        outState.putSerializable("employees", employees);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentEmployer = savedInstanceState.getInt("currentEmployer");
            employees = (ArrayList<Employee>)savedInstanceState.getSerializable("employees");
        } else {
            for (int i = 0; i < 50; i++) {
                employees.add(new Employee("fname" + i, "lname" + i, "skills" + i));
            }
        }

        setContentView(R.layout.activity_main);

        View header = getLayoutInflater().inflate(R.layout.header, listView, false);
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);
        SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, employees);
        listView.setAdapter(adapter);

        hideAll();

        if (currentEmployer >= 0) {
            showOrHideListHeader();
            showViewMode();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOrHideListHeader();
                currentEmployer = --position;
                showViewMode();
            }
        });
    }

    public void showOrHideListHeader() {
        findViewById(R.id.header).setVisibility(View.VISIBLE);
        View headerLand = findViewById(R.id.header_land);
        if(headerLand != null) {
            listView.findViewById(R.id.header_box).setVisibility(View.GONE);
        }
        else {
            listView.findViewById(R.id.header_box).setVisibility(View.VISIBLE);
        }
    }

    public void showViewMode() {
        TextView fname = (TextView) findViewById(R.id.fname);
        TextView lname = (TextView) findViewById(R.id.lname);
        TextView skills = (TextView) findViewById(R.id.skills);

        fname.setVisibility(View.VISIBLE);
        lname.setVisibility(View.VISIBLE);
        skills.setVisibility(View.VISIBLE);
        findViewById(R.id.edit_skills_button).setVisibility(View.VISIBLE);
        findViewById(R.id.edit_skills_view).setVisibility(View.GONE);
        findViewById(R.id.save_skills_button).setVisibility(View.GONE);

        if (currentEmployer >= 0) {
            fname.setText(employees.get(currentEmployer).getFname());
            lname.setText(employees.get(currentEmployer).getLname());
            skills.setText(employees.get(currentEmployer).getSkills());
        }
    }

    public void showEditMode() {

        EditText editSkills = (EditText) findViewById(R.id.edit_skills_view);
        editSkills.setText(employees.get(currentEmployer).getSkills());

        findViewById(R.id.fname).setVisibility(View.VISIBLE);
        findViewById(R.id.lname).setVisibility(View.VISIBLE);
        findViewById(R.id.skills).setVisibility(View.GONE);
        findViewById(R.id.edit_skills_button).setVisibility(View.GONE);
        editSkills.setVisibility(View.VISIBLE);
        findViewById(R.id.save_skills_button).setVisibility(View.VISIBLE);
    }

    public void hideAll() {
        listView.findViewById(R.id.header_box).setVisibility(View.GONE);
        findViewById(R.id.skills).setVisibility(View.GONE);
        findViewById(R.id.edit_skills_button).setVisibility(View.GONE);
        findViewById(R.id.edit_skills_view).setVisibility(View.GONE);
        findViewById(R.id.save_skills_button).setVisibility(View.GONE);
        findViewById(R.id.fname).setVisibility(View.GONE);
        findViewById(R.id.lname).setVisibility(View.GONE);
    }

    public void editSkillsButtonOnClick(View v) {
        showEditMode();
    }

    public void saveSkillsButtonOnClick(View v) {

        TextView skills = (TextView) findViewById(R.id.edit_skills_view);
        employees.get(currentEmployer).setSkills(skills.getText().toString());

        showViewMode();
    }
}

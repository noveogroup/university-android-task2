package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String CURRENT_EMPLOYER_BUNDLE_KEY = "CURRENT_EMPLOYER_BUNDLE_KEY";
    private static final String EMPLOYEES_BUNDLE_KEY = "EMPLOYEES_BUNDLE_KEY";

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private int currentEmployer = -1;
    private ListView listView;
    private View header;
    private View headerLand;
    private View headerBox;
    private TextView fname;
    private TextView lname;
    private TextView skills;
    private Button editSkillsButton;
    private Button saveSkillsButton;
    private EditText editSkillsView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_EMPLOYER_BUNDLE_KEY, currentEmployer);
        outState.putSerializable(EMPLOYEES_BUNDLE_KEY, employees);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentEmployer = savedInstanceState.getInt(CURRENT_EMPLOYER_BUNDLE_KEY);
            employees = (ArrayList<Employee>)savedInstanceState.getSerializable(EMPLOYEES_BUNDLE_KEY);
        } else {
            for (int i = 0; i < 50; i++) {
                employees.add(new Employee("fname" + i, "lname" + i, "skills" + i));
            }
        }

        setContentView(R.layout.activity_main);

        header = getLayoutInflater().inflate(R.layout.header, listView, false);
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);
        SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, employees);
        listView.setAdapter(adapter);

        headerLand = findViewById(R.id.header_land);
        headerBox = listView.findViewById(R.id.header_box);
        fname = (TextView) findViewById(R.id.fname);
        lname = (TextView) findViewById(R.id.lname);
        skills = (TextView) findViewById(R.id.skills);
        editSkillsView = (EditText) findViewById(R.id.edit_skills_view);
        editSkillsButton = (Button) findViewById(R.id.edit_skills_button);
        saveSkillsButton = (Button) findViewById(R.id.save_skills_button);

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
        header.setVisibility(View.VISIBLE);
        if(headerLand != null) {
            headerBox.setVisibility(View.GONE);
        }
        else {
            headerBox.setVisibility(View.VISIBLE);
        }
    }

    public void showViewMode() {
        fname.setVisibility(View.VISIBLE);
        lname.setVisibility(View.VISIBLE);
        skills.setVisibility(View.VISIBLE);
        editSkillsButton.setVisibility(View.VISIBLE);
        editSkillsView.setVisibility(View.GONE);
        saveSkillsButton.setVisibility(View.GONE);

        if (currentEmployer >= 0) {
            fname.setText(employees.get(currentEmployer).getFname());
            lname.setText(employees.get(currentEmployer).getLname());
            skills.setText(employees.get(currentEmployer).getSkills());
        }
    }

    public void showEditMode() {

        editSkillsView.setText(employees.get(currentEmployer).getSkills());

        fname.setVisibility(View.VISIBLE);
        lname.setVisibility(View.VISIBLE);
        skills.setVisibility(View.GONE);
        editSkillsButton.setVisibility(View.GONE);
        editSkillsView.setVisibility(View.VISIBLE);
        saveSkillsButton.setVisibility(View.VISIBLE);
    }

    public void hideAll() {
        headerBox.setVisibility(View.GONE);
        fname.setVisibility(View.GONE);
        lname.setVisibility(View.GONE);
        skills.setVisibility(View.GONE);
        editSkillsButton.setVisibility(View.GONE);
        editSkillsView.setVisibility(View.GONE);
        saveSkillsButton.setVisibility(View.GONE);
    }

    public void editSkillsButtonOnClick(View v) {
        showEditMode();
    }

    public void saveSkillsButtonOnClick(View v) {
        employees.get(currentEmployer).setSkills(editSkillsView.getText().toString());
        showViewMode();
    }
}

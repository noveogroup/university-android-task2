package com.noveogroup.task2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.noveogroup.task2.adapter.EmployeesListAdapter;
import com.noveogroup.task2.fragment.SkillsFragment;
import com.noveogroup.task2.model.Employee;
import com.noveogroup.task2.util.EmployeesListCreator;

import java.util.ArrayList;

public final class MainActivity extends FragmentActivity
                                implements SkillsFragment.OnSaveListener {

    public final static String EMPLOYEES_LIST = "com.noveogroup.com.task2.EMPLOYEES_LIST";
    public final static String SELECTED_ITEM_POSITION
                               = "com.noveogroup.task2.SELECTED_ITEM_POSITION";

    private ArrayList<Employee> employeesList;
    private int selectedItemIndex;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if(savedInstanceState == null) {
            employeesList = EmployeesListCreator.getEmployeesList(20);
        }
        else {
            employeesList = savedInstanceState.getParcelableArrayList(EMPLOYEES_LIST);
            selectedItemIndex = savedInstanceState.getInt(SELECTED_ITEM_POSITION);
        }

        EmployeesListAdapter employeesListAdapter = new EmployeesListAdapter(this, employeesList);
        listView = (ListView) findViewById(R.id.list_view);

//        If we are in portrait orientation we need to inflate a header.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listView.addHeaderView(getLayoutInflater()
                    .inflate(R.layout.list_header_layout, listView, false));
        }

        listView.setAdapter(employeesListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                If we are in portrait orientation we need to shift index by one point to left.
                if(getResources().getConfiguration().orientation
                                                        == Configuration.ORIENTATION_PORTRAIT) {
                    --i;
                }
                ((MainActivity) adapterView.getContext()).onItemSelected(i);
            }
        });
    }

    @Override
    public void onSave(String newSkills) {
        employeesList.get(selectedItemIndex).setSkills(newSkills);
        putFragment();
        Toast.makeText(this, "Skills were updated", Toast.LENGTH_SHORT).show();
    }

    private void onItemSelected(final int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;

        SkillsFragment fragment = (SkillsFragment) getSupportFragmentManager()
                                                    .findFragmentByTag(SkillsFragment.FRAGMENT_TAG);
        if(fragment == null) {
            putFragment();
        }
        else {
            fragment.showEmployeeInfo(employeesList.get(selectedItemIndex));
        }

//        If we are in portrait orientation make ListView scroll to the header.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listView.smoothScrollToPosition(0);
        }
    }

    private void putFragment() {
        Fragment newFragment = SkillsFragment.newInstance(employeesList.get(selectedItemIndex));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.info_skills_layout, newFragment, SkillsFragment.FRAGMENT_TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EMPLOYEES_LIST, employeesList);
        outState.putInt(SELECTED_ITEM_POSITION, selectedItemIndex);
    }
}

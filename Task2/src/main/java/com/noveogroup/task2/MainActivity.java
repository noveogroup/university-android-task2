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

    public final static String EMPLOYEE = "com.noveogroup.com.EMPLOYEE";
    public final static String EMPLOYEES_LIST = "com.noveogroup.com.EMPLOYEES_LIST";
    public final static String SELECTED_ITEM_POSITION
                               = "com.noveogroup.task2.SELECTED_ITEM_POSITION";
    public final static String FRAGMENT_TAG = "com.noveogroup.task2.FRAGMENT_TAG";

    private ArrayList<Employee> employeesList;
    int selectedItemIndex;

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
        ListView listView = (ListView) findViewById(R.id.list_view);

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
    public void onSave() {
        EditText editText = (EditText) findViewById(R.id.skills_edit_text);
        String newSkills = editText.getText().toString();
        employeesList.get(selectedItemIndex).setSkills(newSkills);

        putFragment();

        InputMethodManager inputMethodManager
                                    = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        Toast.makeText(this, "Skills were updated", Toast.LENGTH_SHORT).show();
    }

    private void onItemSelected(final int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;

        SkillsFragment fragment = (SkillsFragment) getSupportFragmentManager()
                                                              .findFragmentByTag(FRAGMENT_TAG);
        if(fragment == null) {
            putFragment();
        }
        else {
            fragment.showEmployeeInfo(employeesList.get(selectedItemIndex));
        }

//        If we are in portrait orientation make ListView scroll to the header.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((ListView) findViewById(R.id.list_view)).smoothScrollToPosition(0);
        }
    }

    private void putFragment() {
        Fragment newFragment = new SkillsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EMPLOYEE, employeesList.get(selectedItemIndex));
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.info_skills_layout, newFragment, FRAGMENT_TAG);
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

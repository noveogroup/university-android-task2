package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.*;

public class MainActivity extends FragmentActivity {

    private static final String KEY_EMPLOYEES = "employees";
    private static final String KEY_SELECTED_INDEX = "selected_index";

    private SkillsFragment mSkillsFragment;
    private ListView mList;
    private Employee[] mEmployees;
    private int mSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager manager = getSupportFragmentManager();
        mSkillsFragment = (SkillsFragment)manager.findFragmentById(R.id.skills);
        manager.beginTransaction().hide(mSkillsFragment).commit();
        if (savedInstanceState == null) {
            mEmployees = getRandomEmployees();
        } else {
            mEmployees = (Employee[])savedInstanceState.getParcelableArray(KEY_EMPLOYEES);
        }
        mList = (ListView)findViewById(R.id.list);
        mList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        ListAdapter adapter = new ArrayAdapter<Employee>(
                this, android.R.layout.simple_list_item_1, mEmployees);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                mSelectedIndex = pos;
                showSelectedEmployee();
            }
        });
    }

    private void showSelectedEmployee() {
        mSkillsFragment.setEmployee(mEmployees[mSelectedIndex]);
        getSupportFragmentManager().beginTransaction()
                .show(mSkillsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSelectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX);
        if (mSelectedIndex >= 0) {
            showSelectedEmployee();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(KEY_EMPLOYEES, mEmployees);
        outState.putInt(KEY_SELECTED_INDEX, mSelectedIndex);
    }

    private static Employee[] getRandomEmployees() {
        return new Employee[] {
                new Employee("Barabara", "Saba"),
                new Employee("Burt", "Poteete"),
                new Employee("Alejandrina", "Tarlton"),
                new Employee("Mozell", "Obregon"),
                new Employee("Ambrose", "Augustus"),
                new Employee("Marlen", "Bushard"),
                new Employee("Stephanie", "Olenick"),
                new Employee("Kenny", "Arcuri"),
                new Employee("Jacalyn", "Trippe"),
                new Employee("Jeri", "Gahan"),
                new Employee("Greg", "Lightford"),
                new Employee("Bulah", "Dilbeck"),
                new Employee("Woodrow", "Elks"),
                new Employee("Rick", "Lebel"),
                new Employee("Maryjo", "Clevinger"),
                new Employee("Arminda", "Waiters"),
                new Employee("Toi", "Clodfelter"),
                new Employee("Layla", "Timmerman"),
                new Employee("Gregg", "Selley"),
                new Employee("Hank", "Blassingame"),
                new Employee("Erlene", "Delavega"),
                new Employee("Daine", "Tindell"),
                new Employee("Gearldine", "Binford"),
                new Employee("Dorathy", "Wacaster"),
                new Employee("Orville", "Shivers"),
                new Employee("Giselle", "Hester"),
                new Employee("Genia", "Dade"),
                new Employee("Debroah", "Locicero"),
                new Employee("Lloyd", "Dockstader"),
                new Employee("Kara", "Forehand")
        };
    }
}

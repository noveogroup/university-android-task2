package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SkillsFragment extends Fragment {

    private static final String KEY_EMPLOYEE_ID = "employee_id";
    private static final String KEY_EDIT_MODE = "edit_mode";

    private TextView mNameView;
    private TextView mSurnameView;
    private TextView mSkillsView;
    private Button mEditBtn;
    private EditText mSkillsEdit;
    private Button mSaveBtn;

    private int mEmployeeId;
    private FragmentHost mHost;
    private boolean mEditMode;

    public static void fillInto(FragmentManager manager, int container, int employeeId) {
        SkillsFragment instance = (SkillsFragment)manager.findFragmentById(R.id.container);
        if (instance == null) {
            Bundle args = new Bundle();
            args.putInt(KEY_EMPLOYEE_ID, employeeId);
            instance = new SkillsFragment();
            instance.setArguments(args);
            manager.beginTransaction().add(container, instance).commit();
        } else {
            instance.showEmployee(employeeId);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mHost = (FragmentHost)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mEmployeeId = savedInstanceState.getInt(KEY_EMPLOYEE_ID);
        } else {
            mEmployeeId = getArguments().getInt(KEY_EMPLOYEE_ID);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skills, container, false);
        mNameView = (TextView)view.findViewById(R.id.name);
        mSurnameView = (TextView)view.findViewById(R.id.surname);
        mSkillsView = (TextView)view.findViewById(R.id.skills_view);
        mEditBtn = (Button)view.findViewById(R.id.skills_edit_btn);
        mSkillsEdit = (EditText)view.findViewById(R.id.skills_edit);
        mSaveBtn = (Button)view.findViewById(R.id.skills_save);
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditMode();
            }
        });
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = mHost.getEmployee(mEmployeeId);
                employee.setSkills(mSkillsEdit.getText().toString());
                showEmployee(mEmployeeId);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNameView = null;
        mSurnameView = null;
        mSkillsView = null;
        mEditBtn = null;
        mSkillsEdit = null;
        mSaveBtn = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_EMPLOYEE_ID, mEmployeeId);
        outState.putBoolean(KEY_EDIT_MODE, mEditMode);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            setEmployee(mEmployeeId);
        } else {
            mEditMode = savedInstanceState.getBoolean(KEY_EDIT_MODE);
            if (mEditMode) switchEditMode();
        }
    }

    public void showEmployee(int employeeId) {
        setEmployee(employeeId);
        switchViewMode();
    }

    private void setEmployee(int employeeId) {
        mEmployeeId = employeeId;
        Employee employee = mHost.getEmployee(employeeId);
        mNameView.setText(employee.getName());
        mSurnameView.setText(employee.getSurname());
        String skills = employee.getSkills();
        mSkillsEdit.setText(skills);
        mSkillsView.setText(TextUtils.isEmpty(skills) ? getString(R.string.no_skills) : skills);
    }

    private void switchViewMode() {
        mEditMode = false;
        mSkillsView.setVisibility(View.VISIBLE);
        mEditBtn.setVisibility(View.VISIBLE);
        mSkillsEdit.setVisibility(View.GONE);
        mSaveBtn.setVisibility(View.GONE);
    }

    private void switchEditMode() {
        mEditMode = true;
        mSkillsView.setVisibility(View.GONE);
        mEditBtn.setVisibility(View.GONE);
        mSkillsEdit.setVisibility(View.VISIBLE);
        mSaveBtn.setVisibility(View.VISIBLE);
    }

}

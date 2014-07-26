package com.noveogroup.task2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SkillsFragment extends Fragment {

    private TextView mNameView;
    private TextView mSurnameView;
    private TextView mSkillsView;
    private Button mEditBtn;
    private EditText mSkillsEdit;
    private Button mSaveBtn;

    private Employee mEmployee;

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
                mEmployee.setSkills(mSkillsEdit.getText().toString());
                switchViewMode();
            }
        });
        return view;
    }

    public void setEmployee(Employee item) {
        mEmployee = item;
        mNameView.setText(item.getName());
        mSurnameView.setText(item.getSurname());
        switchViewMode();
    }

    private void switchViewMode() {
        String skills = mEmployee.getSkills();
        mSkillsView.setText(
                (skills != null && skills.length() > 0) ? skills : getString(R.string.no_skills));
        mSkillsView.setVisibility(View.VISIBLE);
        mEditBtn.setVisibility(View.VISIBLE);
        mSkillsEdit.setVisibility(View.GONE);
        mSaveBtn.setVisibility(View.GONE);
    }

    private void switchEditMode() {
        mSkillsEdit.setText(mEmployee.getSkills());
        mSkillsView.setVisibility(View.GONE);
        mEditBtn.setVisibility(View.GONE);
        mSkillsEdit.setVisibility(View.VISIBLE);
        mSaveBtn.setVisibility(View.VISIBLE);
    }

}

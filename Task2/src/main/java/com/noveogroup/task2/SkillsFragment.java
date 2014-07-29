package com.noveogroup.task2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SkillsFragment extends Fragment {
    private TextView nameText;
    private TextView surnameText;
    private TextView skillsText;
    private Button editBtn;
    private Button saveBtn;
    private EditText skillsEdit;
    private Employee employee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.skills_fragment, container);
        nameText = (TextView) v.findViewById(R.id.name);
        surnameText = (TextView) v.findViewById(R.id.surname);
        skillsText = (TextView) v.findViewById(R.id.skills);
        skillsEdit = (EditText) v.findViewById(R.id.skills_edit);
        editBtn = (Button) v.findViewById(R.id.edit_btn);
        saveBtn = (Button) v.findViewById(R.id.save_btn);

        skillsEdit.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editView();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employee.setSkills(skillsEdit.getText().toString());
                infoView();
            }
        });
        setRetainInstance(true);
        return v;
    }

    private void setTextSkills() {
        String skills = employee.getSkills();
        if(TextUtils.isEmpty(skills)) {
            skillsText.setText(R.string.no_skills);
        }
        else {
            skillsText.setText(skills);
        }
    }

    public void setEmployee(Employee item) {
        employee = item;
        nameText.setText(employee.getName());
        surnameText.setText(employee.getSurname());
        setTextSkills();
    }

    public void infoView() {
        setTextSkills();
        skillsText.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.GONE);
        skillsEdit.setVisibility(View.GONE);
    }

    public void editView() {
        skillsEdit.setText(employee.getSkills());
        skillsText.setVisibility(View.GONE);
        editBtn.setVisibility(View.GONE);
        saveBtn.setVisibility(View.VISIBLE);
        skillsEdit.setVisibility(View.VISIBLE);
    }
}

package com.noveogroup.task2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.noveogroup.task2.R;

public final class SkillsInspectFragment extends SkillsFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skills_inspect_layout, container, false);
        Button skills_edit_button = (Button) view.findViewById(R.id.skills_edit_button);
        skills_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditSaveListener.onEdit();
            }
        });

        fillTextFields(view, R.id.skills_inspect_text);
        return view;
    }
}

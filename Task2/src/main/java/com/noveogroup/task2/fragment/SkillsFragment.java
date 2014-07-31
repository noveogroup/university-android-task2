package com.noveogroup.task2.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.noveogroup.task2.R;
import com.noveogroup.task2.model.Employee;

public final class SkillsFragment extends Fragment {

    public final static String FRAGMENT_TAG = "com.noveogroup.task2.FRAGMENT_TAG";
    public final static String IS_EDITING = "com.noveogroup.com.task2.IS_EDITING";
    public final static String EMPLOYEE = "com.noveogroup.com.task2.EMPLOYEE";
    public final static String SAVED_TEXT_FIELDS = "com.noveogroup.com.task2.SAVED_TEXT_FIELDS";

    private OnSaveListener onSaveListener;
    private TextView nameText;
    private TextView surnameText;
    private TextView skillsInspectText;
    private EditText skillsEditText;
    private Button skillsEditButton;
    private Button skillsSaveButton;
    private Bundle savedTextFields;

    public static SkillsFragment newInstance(Employee employee) {
        SkillsFragment newFragment = new SkillsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EMPLOYEE, employee);
        newFragment.setArguments(bundle);

        return newFragment;
    }

    private Employee getEmployee() {
        Bundle bundle = getArguments();
        return bundle == null ? null : (Employee) bundle.getParcelable(EMPLOYEE);
    }

    public interface OnSaveListener {
        void onSave(final String textToSave);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onSaveListener = (OnSaveListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.skills_layout, container, false);
        nameText = (TextView) view.findViewById(R.id.name_text);
        surnameText = (TextView) view.findViewById(R.id.surname_text);
        skillsInspectText = (TextView) view.findViewById(R.id.skills_inspect_text);
        skillsEditText = (EditText) view.findViewById(R.id.skills_edit_text);
        skillsEditButton = (Button) view.findViewById(R.id.skills_edit_button);
        skillsSaveButton = (Button) view.findViewById(R.id.skills_save_button);

        skillsEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skillsEditText.setText(skillsInspectText.getText());
                changeEditViewsVisibility(View.VISIBLE);
                changeInspectViewsVisibility(View.INVISIBLE);

                skillsEditText.setSelection(skillsEditText.getText().length());
                if (skillsEditText.requestFocus()) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                            .getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(skillsEditText,
                                                             InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        skillsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(skillsEditText.getWindowToken(), 0);
                onSaveListener.onSave(skillsEditText.getText().toString());
            }
        });
        final Resources resources = getResources();
        final Drawable drawable = resources.getDrawable(R.drawable.save_icon);
        drawable.setBounds(0, 0,
                resources.getDimensionPixelSize(R.dimen.drawable_size),
                resources.getDimensionPixelSize(R.dimen.drawable_size));

        skillsSaveButton.setCompoundDrawables(drawable, null, null, null);

        Bundle bundle = savedInstanceState == null ? null
                                                : savedInstanceState.getBundle(SAVED_TEXT_FIELDS);
        Employee employee = bundle == null ? getEmployee()
                            : (Employee) bundle.getParcelable(EMPLOYEE);
        if(employee != null) {
            nameText.setText(employee.getName());
            surnameText.setText(employee.getSurname());
            if (bundle != null && bundle.getBoolean(IS_EDITING)) {
                skillsEditText.setText(employee.getSkills());
                changeInspectViewsVisibility(View.INVISIBLE);
            } else {
                skillsInspectText.setText(employee.getSkills());
                changeEditViewsVisibility(View.INVISIBLE);
            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(savedTextFields == null) {
            savedTextFields = new Bundle();
            saveTextFields(savedTextFields);
        }
        outState.putBundle(SAVED_TEXT_FIELDS, savedTextFields);
    }

    public void showEmployeeInfo(Employee employee) {
        if (skillsEditText.getVisibility() == View.VISIBLE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(skillsEditText.getWindowToken(), 0);

            changeEditViewsVisibility(View.INVISIBLE);
            changeInspectViewsVisibility(View.VISIBLE);
        }
        setNameSurnameText(employee);
        skillsInspectText.setText(employee.getSkills());
    }

    private void setNameSurnameText(Employee employee) {
        nameText.setText(employee.getName());
        surnameText.setText(employee.getSurname());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        savedTextFields = new Bundle();
        saveTextFields(savedTextFields);

        nameText = null;
        surnameText = null;
        skillsInspectText = null;
        skillsEditText = null;
        skillsEditButton = null;
        skillsSaveButton = null;
    }

    private void changeEditViewsVisibility(int newState) {
        if(newState == View.VISIBLE || newState == View.INVISIBLE) {
            skillsEditText.setVisibility(newState);
            skillsSaveButton.setVisibility(newState);
        }
    }

    private void changeInspectViewsVisibility(int newState) {
        if(newState == View.VISIBLE || newState == View.INVISIBLE) {
            skillsInspectText.setVisibility(newState);
            skillsEditButton.setVisibility(newState);
        }
    }

    private void saveTextFields(Bundle bundle) {

        if(skillsEditText != null) {
            if (skillsEditText.getVisibility() == View.VISIBLE) {
                bundle.putBoolean(IS_EDITING, true);
                bundle.putParcelable(EMPLOYEE, new Employee(nameText.getText().toString(),
                        surnameText.getText().toString(),
                        skillsEditText.getText().toString()));
            } else {
                bundle.putBoolean(IS_EDITING, false);
                bundle.putParcelable(EMPLOYEE, new Employee(nameText.getText().toString(),
                        surnameText.getText().toString(),
                        skillsInspectText.getText().toString()));
            }
        }
    }
}
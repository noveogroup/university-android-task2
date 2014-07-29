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

import com.noveogroup.task2.MainActivity;
import com.noveogroup.task2.R;
import com.noveogroup.task2.model.Employee;

public class SkillsFragment extends Fragment {

    public final static String IS_EDITING = "com.noveogroup.com.IS_EDITING";
    public final static String SKILLS_TEXT = "com.noveogroup.com.SKILLS_TEXT";

    protected OnSaveListener onSaveListener;

    public interface OnSaveListener {
        void onSave();
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

        final Button skillsEditButton = (Button) view.findViewById(R.id.skills_edit_button);
        skillsEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) getActivity().findViewById(R.id.skills_inspect_text);
                EditText editText = (EditText) getActivity().findViewById(R.id.skills_edit_text);
                editText.setText(textView.getText());

                textView.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                skillsEditButton.setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.skills_save_button).setVisibility(View.VISIBLE);

                editText.setSelection(editText.getText().length());
                if (editText.requestFocus()) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                            .getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        final Button skillsSaveButton = (Button) view.findViewById(R.id.skills_save_button);
        skillsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveListener.onSave();
            }
        });
        final Resources resources = getResources();
        final Drawable drawable = resources.getDrawable(R.drawable.save_icon);
        drawable.setBounds(0, 0,
                resources.getDimensionPixelSize(R.dimen.drawable_size),
                resources.getDimensionPixelSize(R.dimen.drawable_size));

        skillsSaveButton.setCompoundDrawables(drawable, null, null, null);

        Bundle bundle = getArguments();
        Employee employee = bundle.getParcelable(MainActivity.EMPLOYEE);
        setNameSurnameText(view, employee);

        if (savedInstanceState != null && savedInstanceState.getBoolean(IS_EDITING)) {
            String skillsText = savedInstanceState.getString(SKILLS_TEXT);
            EditText editText = (EditText) view.findViewById(R.id.skills_edit_text);
            editText.setText(skillsText);
            skillsEditButton.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.skills_inspect_text).setVisibility(View.INVISIBLE);
        } else {
            TextView skillsText = (TextView) view.findViewById(R.id.skills_inspect_text);
            skillsText.setText(employee.getSkills());
            skillsSaveButton.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.skills_edit_text).setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView textView = (TextView) getActivity().findViewById(R.id.skills_edit_text);
        if (textView.getVisibility() == View.VISIBLE) {
            outState.putBoolean(IS_EDITING, true);
            outState.putString(SKILLS_TEXT, textView.getText().toString());
        } else {
            outState.putBoolean(IS_EDITING, false);
            outState.putString(SKILLS_TEXT, "");
        }
    }

    public void showEmployeeInfo(Employee employee) {
        View editText = getActivity().findViewById(R.id.skills_edit_text);
        TextView skillsInspectText = (TextView) getView().findViewById(R.id.skills_inspect_text);
        if (editText.getVisibility() == View.VISIBLE) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

            editText.setVisibility(View.INVISIBLE);
            getView().findViewById(R.id.skills_save_button).setVisibility(View.INVISIBLE);
            skillsInspectText.findViewById(R.id.skills_inspect_text).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.skills_edit_button).setVisibility(View.VISIBLE);
        }
        setNameSurnameText(getView(), employee);
        skillsInspectText.setText(employee.getSkills());
    }

    private void setNameSurnameText(View view, Employee employee) {
        TextView nameText = (TextView) view.findViewById(R.id.name_text);
        nameText.setText(employee.getName());
        TextView surnameText = (TextView) view.findViewById(R.id.surname_text);
        surnameText.setText(employee.getSurname());

    }
}
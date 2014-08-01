package com.noveogroup.task2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	public static final String EMPLOYEE_BUNDLE_TEXT = "EMPLOYEE";
	private Employee currentEmployee;
	private TextView nameText;
	private TextView surnameText;
	private TextView skillText;
	private EditText skillEditText;
	private View skillEdit;
	private View skillShow;

	private final View.OnClickListener editListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			doEdit();
		}
	};

	private final View.OnClickListener saveListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			doSave();
		}
	};


	public static InfoFragment newInstance(Employee employee) {
		InfoFragment fragment = new InfoFragment();
		Bundle args = new Bundle();
		args.putParcelable(EMPLOYEE_BUNDLE_TEXT, employee);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_fragment, container, false);
		view.findViewById(R.id.edit_btn).setOnClickListener(editListener);
		view.findViewById(R.id.save_btn).setOnClickListener(saveListener);
		nameText = (TextView) view.findViewById(R.id.name_text);
		surnameText = (TextView) view.findViewById(R.id.surname_text);
		skillEditText = (EditText) view.findViewById(R.id.skills_text_edit);
		skillText = (TextView) view.findViewById(R.id.skills_text);
		skillEdit = view.findViewById(R.id.edit_view);
		skillShow = view.findViewById(R.id.show_view);

		if (savedInstanceState == null) {
			applyInfo((Employee) getArguments().getParcelable(EMPLOYEE_BUNDLE_TEXT));
		} else {
			applyInfo((Employee) savedInstanceState.getParcelable(EMPLOYEE_BUNDLE_TEXT));
		}

		changeState(false);
		System.out.println(123);
		return view;
	}

	public void applyInfo(Employee employee) {
		currentEmployee = employee;
		nameText.setText(employee.getName());
		surnameText.setText(employee.getSurname());
		skillText.setText(employee.getSkills());
	}

	private void doSave() {

		changeState(false);
		currentEmployee.setSkills(skillEditText.getText().toString());
		applyInfo(currentEmployee);
		if (getActivity().getCurrentFocus() != null) {
			((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
					hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
		}

	}

	private void doEdit() {
		changeState(true);
		skillEditText.setText(currentEmployee.getSkills());
	}

	private void changeState(boolean isEditable) {
		if (isEditable) {
			skillEdit.setVisibility(View.VISIBLE);
			skillShow.setVisibility(View.GONE);
		} else {
			skillShow.setVisibility(View.VISIBLE);
			skillEdit.setVisibility(View.GONE);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(EMPLOYEE_BUNDLE_TEXT, currentEmployee);
	}

}

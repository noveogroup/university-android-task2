package com.noveogroup.task2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	public static final String EMPLOYEE_BUNDLE_TEXT = "EMPLOYEE";
	private Employee currentEmployee;

	private final View.OnClickListener editListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			doEdit(getView());
		}
	};

	private final View.OnClickListener saveListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			doSave(getView());
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_fragment, container, false);
		applyInfo(view);
		view.findViewById(R.id.edit_btn).setOnClickListener(editListener);
		view.findViewById(R.id.save_btn).setOnClickListener(saveListener);
		view.findViewById(R.id.show_view).setVisibility(View.VISIBLE);
		view.findViewById(R.id.edit_view).setVisibility(View.GONE);
		return view;
	}

	public static InfoFragment newInstance(Employee employee) {
		InfoFragment fragment = new InfoFragment();
		Bundle args = new Bundle();
		args.putParcelable(EMPLOYEE_BUNDLE_TEXT, employee);
		fragment.setArguments(args);
		return fragment;
	}

	private void applyInfo(View view) {
		currentEmployee = getArguments().getParcelable(EMPLOYEE_BUNDLE_TEXT);
		applyInfo(view, currentEmployee);
	}

	public void applyInfo(Employee employee) {
		currentEmployee = employee;
		applyInfo(getView(), employee);
	}

	private void applyInfo(View view, Employee employee) {
		((TextView) view.findViewById(R.id.name_text)).setText(employee.getName());
		((TextView) view.findViewById(R.id.surname_text)).setText(employee.getSurname());
		((TextView) view.findViewById(R.id.skills_text)).setText(employee.getSkills());
	}

	private void doSave(View view) {
		view.findViewById(R.id.show_view).setVisibility(View.VISIBLE);
		view.findViewById(R.id.edit_view).setVisibility(View.GONE);
		currentEmployee.setSkills(((EditText) view.findViewById(R.id.skills_text_edit)).getText().toString());
		applyInfo(view, currentEmployee);
	}

	private void doEdit(View view) {
		view.findViewById(R.id.edit_view).setVisibility(View.VISIBLE);
		view.findViewById(R.id.show_view).setVisibility(View.GONE);
		((EditText) view.
				findViewById(R.id.skills_text_edit)).setText(currentEmployee.getSkills());
	}
}

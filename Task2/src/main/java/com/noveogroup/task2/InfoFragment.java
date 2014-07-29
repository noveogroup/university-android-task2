package com.noveogroup.task2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	public static final String EMPLOYEE_BUNDLE_TEXT = "EMPLOYEE";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.info_fragment, container, false);
		setInfo(view);
		System.out.println("View created");
		return view;
	}

	public static InfoFragment newInstance(Employee employee) {
		InfoFragment fragment = new InfoFragment();
		Bundle args = new Bundle();
		args.putSerializable(EMPLOYEE_BUNDLE_TEXT, employee);
		fragment.setArguments(args);
		return fragment;
	}

	private void setInfo(View view) {
		Employee employee = (Employee) getArguments().getSerializable(EMPLOYEE_BUNDLE_TEXT);
		applyInfo(view, employee);
	}

	public void applyInfo(Employee employee) {
		applyInfo(getView(), employee);
	}

	private void applyInfo(View view, Employee employee) {
		((TextView) view.findViewById(R.id.name_text)).setText(employee.getName());
		((TextView) view.findViewById(R.id.surname_text)).setText(employee.getSurname());
		((TextView) view.findViewById(R.id.skills_text)).setText(employee.getSkills());
	}
}

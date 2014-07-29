package com.noveogroup.task2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends Activity {
	private InfoFragment infoFragment;
	private int currentPosition;
	public static final Employee[] initial = {
			new Employee("Johan", "Ivanov"),
			new Employee("Peter", "Petrov"),
			new Employee("Samuel", "Semenov"),
			new Employee("John", "Doe"),
			new Employee("Jane", "Doe"),
			new Employee("Baby", "Doe"),
			new Employee("Richard", "Roe"),
			new Employee("Jack", "Smith"),
			new Employee("Jill", "Smith"),
			new Employee("Name", "McSurname"),
			new Employee("First", "O'Last"),

	};

	static {
		for (Employee employee : initial) {
			employee.setSkills("No skills, absolutely useless");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoFragment = null;

		ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this, R.layout.list_item, initial);
		ListView list = (ListView) findViewById(R.id.listView);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			View headerView = new FrameLayout(this);
			headerView.setId(R.id.info_container);
			list.addHeaderView(headerView);
		}

		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				getFragmentManager().executePendingTransactions();
				if (infoFragment == null) {

					getFragmentManager().beginTransaction().
							replace(R.id.info_container, infoFragment = InfoFragment.newInstance(
											((Employee) parent.getItemAtPosition(position)))
							).commit();
				} else {
					infoFragment.applyInfo(((Employee) parent.getItemAtPosition(position)));
				}

			}
		});
	}
}

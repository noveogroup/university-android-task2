package com.noveogroup.task2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
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


		ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this, R.layout.list_item, initial);
		ListView list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//TODO: replace
				Toast.makeText(MainActivity.this, ((Employee) parent.getItemAtPosition(position)).getSkills(), Toast.LENGTH_SHORT).show();
			}
		});
	}

}

package com.noveogroup.task2;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        header = getLayoutInflater().inflate(R.layout.header, null);

        ListView listView = getListView();
        listView.addHeaderView(header);

        for (int i = 0; i < 50; i++) {
            employees.add(new Employee("fname" + i, "lname" + i, "skills" + i));
        }

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, employees);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        if (position > 0) {

            Employee employee = (Employee) this.getListAdapter().getItem(position - 1);

            TextView fname = (TextView) header.findViewById(R.id.fname);
            TextView lname = (TextView) header.findViewById(R.id.lname);
            TextView skills = (TextView) header.findViewById(R.id.skills);

            fname.setText(employee.fname);
            lname.setText(employee.lname);
            skills.setText(employee.skills);
        }
    }

    public class Employee {
        public String fname;
        public String lname;
        public String skills;

        public Employee(String fname, String lname, String skills) {
            this.fname = fname;
            this.lname = lname;
            this.skills = skills;
        }
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<Employee> {
        private final Context context;
        private final ArrayList<Employee> values;

        public MySimpleArrayAdapter(Context context, ArrayList<Employee> values) {
            super(context, R.layout.row_layout, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_layout, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            textView.setText(values.get(position).fname + " " + values.get(position).lname);

            return rowView;
        }
    }
}

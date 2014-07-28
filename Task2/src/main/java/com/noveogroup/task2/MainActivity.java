package com.noveogroup.task2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.noveogroup.task2.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends Activity {

    private int currentEmployeeNum;
    private Button editButton;
    private Button saveButton;
    private EditText skillsEditor;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView skillsTextView;
    private List<Employee> employees = new ArrayList<Employee>();
    private boolean isEditing;

    private void setViewMode() {
        editButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.GONE);
        skillsTextView.setVisibility(View.VISIBLE);
        skillsEditor.setVisibility(View.GONE);
        isEditing = false;
    }

    private void setEditMode() {
        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.VISIBLE);
        skillsTextView.setVisibility(View.GONE);
        skillsEditor.setVisibility(View.VISIBLE);
        isEditing = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < employees.size(); i++) {
            String name = "name" + i;
            String surname = "surname" + i;
            String skills = "skills" + i;
            outState.putString(name, employees.get(i).getName());
            outState.putString(surname, employees.get(i).getSurname());
            outState.putString(skills, employees.get(i).getSkills());
        }
        outState.putInt("num", employees.size());
        outState.putInt("currentEmployee", currentEmployeeNum);
        outState.putBoolean("isEditing", isEditing);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int num = savedInstanceState.getInt("num");
        for (int i = 0; i < num; i++) {
            String name = "name" + i;
            String surname = "surname" + i;
            String skills = "skills" + i;
            Employee temp = new Employee(savedInstanceState.getString(name), savedInstanceState.getString(surname), savedInstanceState.getString(skills));
            employees.set(i, temp);
        }
        nameTextView.setText(employees.get(savedInstanceState.getInt("currentEmployee")).getName());
        surnameTextView.setText(employees.get(savedInstanceState.getInt("currentEmployee")).getSurname());
        skillsTextView.setText(employees.get(savedInstanceState.getInt("currentEmployee")).getSkills());
        if (savedInstanceState.getBoolean("isEditing")) {
            setEditMode();
        }
        else {
            setViewMode();
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editButton = (Button)findViewById(R.id.editButton);
        saveButton = (Button)findViewById(R.id.saveButton);
        skillsEditor = (EditText)findViewById(R.id.skillsEditor);
        nameTextView = (TextView)findViewById(R.id.name);
        surnameTextView = (TextView)findViewById(R.id.surname);
        skillsTextView = (TextView)findViewById(R.id.skills);

        final Resources res = getResources();
        final Drawable drawable = res.getDrawable(R.drawable.checkmark);
        drawable.setBounds(0, 0, res.getDimensionPixelOffset(R.dimen.compound_drawable_size), res.getDimensionPixelOffset(R.dimen.compound_drawable_size));
        saveButton.setCompoundDrawables(null, null, drawable, null);

        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        skillsEditor.setVisibility(View.GONE);

        //final List<Employee> employees = new ArrayList<Employee>();
        Random rnd = new Random();

        for (int i = 0; i < 100; i++) {
            String name = getString(R.string.name) + i;
            String surname = getString(R.string.surname) + i;
            int max = rnd.nextInt(5) + 1;
            String newSkills = "";
            for (int j = 0; j < max; j++) {
                newSkills = newSkills + getString(R.string.skills) + j;
                if (j < (max - 1)) {
                    newSkills = newSkills + ", ";
                }
            }
            employees.add(new Employee(name, surname, newSkills));
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skillsEditor.setText(skillsTextView.getText());
                setEditMode();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.set(currentEmployeeNum, new Employee(nameTextView.getText().toString(), surnameTextView.getText().toString(), skillsEditor.getText().toString()));
                setViewMode();
                skillsTextView.setText(skillsEditor.getText());
            }
        });

        ListView listView = (ListView)findViewById(R.id.list_view);
        /*if (res.getConfiguration().orientation == 1) {
            View header = View.inflate(this, R.layout.header , null);
            listView.addHeaderView(header);
        }*/
        listView.setAdapter(new EmployeesAdapter(this, employees));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentEmployeeNum = position;
                nameTextView.setText(employees.get(position).getName());
                surnameTextView.setText(employees.get(position).getSurname());
                skillsTextView.setText(employees.get(position).getSkills());
                setViewMode();
            }
        });
    }

    public static class EmployeesAdapter extends BaseAdapter {
        private Context context;
        private List<Employee> listItems;

        public EmployeesAdapter(Context context, List<Employee> listItems) {
            this.context = context;
            this.listItems = listItems;
        }

        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Employee getItem(int i) {
            return listItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
                holder = new ViewHolder();
                holder.fullname = (TextView)view.findViewById(R.id.listFullname);
                view.setTag(holder);
            }
            if (holder == null) {
                holder = (ViewHolder)view.getTag();
            }

            final Employee employee = getItem(i);
            holder.fullname.setText(employee.getName() + " " + employee.getSurname());
            return view;
        }

        private class ViewHolder {
            private TextView fullname;
        }
    }
}

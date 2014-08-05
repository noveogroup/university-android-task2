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
import android.view.inputmethod.InputMethodManager;
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

public class MainActivity extends Activity {

    private int currentEmployeeNum;
    private Button editButton;
    private Button saveButton;
    private EditText skillsEditor;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView skillsTextView;
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private boolean isEditing;

    private static final String KEY_EMPLOYEES = "EMPLOYEES";
    private static final String KEY_IS_EDITING = "IS_EDITING";
    private static final String KEY_CURRENT_EMPLOYEE_NUM = "CURRENT_EMPLOYEE_NUM";
    private static final String KEY_EDITOR_TEXT = "EDITOR_TEXT";

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
        if (isEditing) {
            outState.putString(KEY_EDITOR_TEXT, skillsEditor.getText().toString());
        }
        outState.putInt(KEY_CURRENT_EMPLOYEE_NUM, currentEmployeeNum);
        outState.putParcelableArrayList(KEY_EMPLOYEES, employees);
        outState.putBoolean(KEY_IS_EDITING, isEditing);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        employees = savedInstanceState.getParcelableArrayList(KEY_EMPLOYEES);
        nameTextView.setText(employees.get(savedInstanceState.getInt(KEY_CURRENT_EMPLOYEE_NUM)).getName());
        surnameTextView.setText(employees.get(savedInstanceState.getInt(KEY_CURRENT_EMPLOYEE_NUM)).getSurname());
        skillsTextView.setText(employees.get(savedInstanceState.getInt(KEY_CURRENT_EMPLOYEE_NUM)).getSkills());
        if (savedInstanceState.getBoolean(KEY_IS_EDITING)) {
            setEditMode();
            skillsEditor.setText(savedInstanceState.getString(KEY_EDITOR_TEXT));
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

        ListView listView = (ListView)findViewById(R.id.list_view);
        if (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            View header = View.inflate(this, R.layout.header , null);
            listView.addHeaderView(header);

            editButton = (Button)header.findViewById(R.id.editButton);
            saveButton = (Button)header.findViewById(R.id.saveButton);
            skillsEditor = (EditText)header.findViewById(R.id.skillsEditor);
            nameTextView = (TextView)header.findViewById(R.id.name);
            surnameTextView = (TextView)header.findViewById(R.id.surname);
            skillsTextView = (TextView)header.findViewById(R.id.skills);
        }
        saveButton.setCompoundDrawables(null, null, drawable, null);

        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        skillsEditor.setVisibility(View.GONE);

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
                setViewMode();
                employees.get(currentEmployeeNum).setSkills(skillsEditor.getText().toString());
                skillsTextView.setText(skillsEditor.getText());
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        listView.setAdapter(new EmployeesAdapter(this, employees));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    position--;
                }
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
                holder.fullName = (TextView)view.findViewById(R.id.listFullname);
                view.setTag(holder);
            }
            if (holder == null) {
                holder = (ViewHolder)view.getTag();
            }

            final Employee employee = getItem(i);
            holder.fullName.setText(employee.getName() + " " + employee.getSurname());
            return view;
        }

        private class ViewHolder {
            private TextView fullName;
        }
    }
}

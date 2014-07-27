package com.noveogroup.task2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noveogroup.task2.model.Employee;
import com.noveogroup.task2.model.StringGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private int currentEmployeeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button editButton = (Button)findViewById(R.id.editButton);
        final Button saveButton = (Button)findViewById(R.id.saveButton);
        final EditText skillsEditor = (EditText)findViewById(R.id.skillsEditor);
        final TextView nameTextView = (TextView)findViewById(R.id.name);
        final TextView surnameTextView = (TextView)findViewById(R.id.surname);
        final TextView skillsTextView = (TextView)findViewById(R.id.skills);

        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        skillsEditor.setVisibility(View.GONE);

        final List<Employee> employees = new ArrayList<Employee>();
        Random rnd = new Random();

        for (int i = 0; i < 100; i++) {
            String name = StringGenerator.generateString(8);
            String surname = StringGenerator.generateString(8);
            int max = rnd.nextInt(5) + 1;
            String newSkills = "";
            for (int j = 0; j < max; j++) {
                newSkills = newSkills + StringGenerator.generateString(20);
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
                skillsTextView.setVisibility(View.GONE);
                skillsEditor.setVisibility(View.VISIBLE);
                editButton.setVisibility(View.GONE);
                saveButton.setVisibility(View.VISIBLE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.set(currentEmployeeNum, new Employee(nameTextView.getText().toString(), surnameTextView.getText().toString(), skillsEditor.getText().toString()));
                saveButton.setVisibility(View.GONE);
                editButton.setVisibility(View.VISIBLE);
                skillsEditor.setVisibility(View.GONE);
                skillsTextView.setVisibility(View.VISIBLE);
                skillsTextView.setText(skillsEditor.getText());
            }
        });

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this, employees));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentEmployeeNum = position;
                nameTextView.setText(employees.get(position).getName());
                surnameTextView.setText(employees.get(position).getSurname());
                skillsTextView.setText(employees.get(position).getSkills());
                editButton.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.GONE);
                skillsEditor.setVisibility(View.GONE);
                skillsTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    public static class MyAdapter extends BaseAdapter {
        private Context context;
        private List<Employee> listItems;

        public MyAdapter(Context context, List<Employee> listItems) {
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
                //holder.surname = (TextView)view.findViewById(R.id.surname);
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
            //private TextView surname;
        }
    }
}

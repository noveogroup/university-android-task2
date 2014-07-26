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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noveogroup.task2.model.Employee;
import com.noveogroup.task2.model.StringGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView nameTextView = (TextView)findViewById(R.id.name);
        final TextView surnameTextView = (TextView)findViewById(R.id.surname);
        final TextView skillsTextView = (TextView)findViewById(R.id.skills);

        final List<Employee> employees = new ArrayList<Employee>();
        String name;
        String surname;
        ArrayList<String> skills = new ArrayList<String>();
        Random rnd = new Random();

        for (int i = 0; i < 100; i++) {
            name = StringGenerator.generateString(8);
            surname = StringGenerator.generateString(8);
            //int max = rnd.nextInt(5) + 1;
            for (int j = 0; j < 2; j++) {
                skills.add(StringGenerator.generateString(2));
            }
            employees.add(new Employee(name, surname, skills));
            skills.clear();
        }

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this, employees));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, employees.get(position).getName(), Toast.LENGTH_SHORT).show();
                nameTextView.setText(employees.get(position).getName());
                surnameTextView.setText(employees.get(position).getSurname());
                ArrayList<String> gottenSkills = employees.get(position).getSkills();
                String stringSkills = "";
                for (int i = 0; i < gottenSkills.size(); i++) {
                    stringSkills = stringSkills + gottenSkills.get(i) + ", ";
                }
                skillsTextView.setText(stringSkills);
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

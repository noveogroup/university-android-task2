package com.noveogroup.task2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.noveogroup.task2.R;
import com.noveogroup.task2.model.Employee;

import java.util.List;

public final class EmployeesListAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> employeesList;

    private class ViewHolder {
        private TextView nameText;
        private TextView surnameText;
    }

    public EmployeesListAdapter(Context context, List<Employee> employeesList) {
        this.context = context;
        this.employeesList = employeesList;
    }

    @Override
    public int getCount() {
        if(employeesList != null) {
            return employeesList.size();
        }
        return 0;
    }

    @Override
    public Employee getItem(int position) {
        if(employeesList != null) {
            return employeesList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(context)
                                        .inflate(R.layout.list_item_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameText = (TextView) convertView.findViewById(R.id.name_item);
            viewHolder.surnameText = (TextView) convertView.findViewById(R.id.surname_item);

            convertView.setTag(viewHolder);
        }

        viewHolder = viewHolder == null ? (ViewHolder) convertView.getTag() : viewHolder;
        final Employee item = getItem(position);

        if(item != null) {
            viewHolder.nameText.setText(item.getName());
            viewHolder.surnameText.setText(item.getSurname());
        }

        return convertView;
    }
}

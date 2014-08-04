package com.noveogroup.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yuri on 26.07.2014.
 */
public class SimpleArrayAdapter extends ArrayAdapter<Employee> {
    private final ArrayList<Employee> values;

    public SimpleArrayAdapter(Context context, ArrayList<Employee> values) {
        super(context, R.layout.row_layout, values);
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);

            holder = new ViewHolder();
            holder.fname = (TextView) convertView.findViewById(R.id.fname);
            holder.lname = (TextView) convertView.findViewById(R.id.lname);

            convertView.setTag(holder);
        }

        holder = holder == null ? (ViewHolder) convertView.getTag() : holder;

        final Employee item = values.get(position);

        holder.fname.setText(item.getFname());
        holder.lname.setText(item.getLname());

        return convertView;
    }

    private class ViewHolder {
        private TextView lname;
        private TextView fname;
    }
}

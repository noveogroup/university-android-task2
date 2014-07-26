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

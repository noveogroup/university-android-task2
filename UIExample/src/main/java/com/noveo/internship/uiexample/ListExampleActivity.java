package com.noveo.internship.uiexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.noveo.internship.uiexample.model.ExampleListItem;

import java.util.ArrayList;
import java.util.List;

public class ListExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_example);

        final List<ExampleListItem> titles = new ArrayList<ExampleListItem>();

        final String title = getString(R.string.title_text);
        final String left = getString(R.string.left_text);
        final String right = getString(R.string.right_text);
        for (int i = 0; i < 100; i++) {
            titles.add(new ExampleListItem(title + i, left + i, right + i));
        }

        final ListView listView = (ListView) findViewById(R.id.example_list);
        listView.setAdapter(new ExampleAdapter(this, titles));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListExampleActivity.this,
                        getString(R.string.toast_message, position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class ExampleAdapter extends BaseAdapter {

        private Context context;
        private List<ExampleListItem> listItems;

        public ExampleAdapter(Context context, List<ExampleListItem> listItems) {
            this.context = context;
            this.listItems = listItems;
        }

        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public ExampleListItem getItem(int position) {
            return listItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.relative_title);
                holder.leftText = (TextView) convertView.findViewById(R.id.left_text);
                holder.rightText = (TextView) convertView.findViewById(R.id.right_text);

                convertView.setTag(holder);
            }

            holder = holder == null ? (ViewHolder) convertView.getTag() : holder;

            final ExampleListItem item = getItem(position);

            holder.title.setText(item.getTitle());
            holder.leftText.setText(item.getLeftText());
            holder.rightText.setText(item.getRightText());

            return convertView;
        }

        private class ViewHolder {
            private TextView title;
            private TextView leftText;
            private TextView rightText;
        }
    }
}

package com.noveo.internship.uiexample;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LayoutsExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts_example);

        final TextView title = (TextView) findViewById(R.id.relative_title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) v).setText(R.string.title_clicked);
            }
        });

        final Resources res = getResources();
        final Drawable drawable = res.getDrawable(R.drawable.ic_launcher);
        drawable.setBounds(0, 0,
                res.getDimensionPixelSize(R.dimen.compound_drawable_size),
                res.getDimensionPixelSize(R.dimen.compound_drawable_size));

        final Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setCompoundDrawables(null, null, drawable, null);
    }

    public void onNextClick(View view) {
        startActivity(new Intent(this, ListExampleActivity.class));
    }
}

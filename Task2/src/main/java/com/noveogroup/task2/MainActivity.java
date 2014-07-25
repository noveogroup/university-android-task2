package com.noveogroup.task2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_edit_layout);

        ((TextView) findViewById(R.id.name_text)).setText("Name");
        ((TextView) findViewById(R.id.surname_text)).setText("Surname");
        ((TextView) findViewById(R.id.skills_text)).setText("Skills");
    }

    public void editSkills(View view) {

    }

    public void saveEdited(View view) {

    }

    public void discardEdited(View view) {

    }
}

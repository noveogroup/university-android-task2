package com.noveogroup.task2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.noveogroup.task2.fragment.SkillsEditFragment;
import com.noveogroup.task2.fragment.SkillsFragment;
import com.noveogroup.task2.fragment.SkillsInspectFragment;
import com.noveogroup.task2.model.DataModel;
import com.noveogroup.task2.model.DataTuple;

import java.util.ArrayList;

public final class MainActivity extends Activity implements SkillsFragment.OnEditSaveListener {

    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        int selectedItemPosition = 0;
        int offset = -1;
        if(savedInstanceState == null) {
            dataModel = new DataModel(null, null, null);
        }
        else {
            ArrayList<String> namesList = savedInstanceState.getStringArrayList(DataModel.NAME);
            ArrayList<String> surnamesList
                                    = savedInstanceState.getStringArrayList(DataModel.SURNAME);
            ArrayList<String> skillsList = savedInstanceState.getStringArrayList(DataModel.SKILLS);
            selectedItemPosition = savedInstanceState.getInt(DataModel.SELECTED_ITEM_POSITION);
            dataModel = new DataModel(namesList, surnamesList, skillsList);
        }

        String[] from = new String[]{DataModel.NAME, DataModel.SURNAME};
        int[] to = new int[]{R.id.name_item, R.id.surname_item};

        SimpleAdapter adapter = new SimpleAdapter(this, dataModel.getData(),
                                                  R.layout.list_item_layout, from, to);
        ListView listView = (ListView) findViewById(R.id.list_view);

//        If we are in portrait orientation we need to inflate a header and redefine the offset.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listView.addHeaderView(getLayoutInflater()
                    .inflate(R.layout.list_header_layout, listView, false));
            offset = -2;
        }

        dataModel.setOffset(offset);
        dataModel.setSelectedItemPosition(selectedItemPosition - 2 * offset - 3);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity) adapterView.getContext()).onItemSelected(i + 1);
            }
        });
    }

    @Override
    public void onEdit() {
        putFragment(true);
    }

    @Override
    public void onSave() {
        EditText editText = (EditText) findViewById(R.id.skills_edit_text);
        String newSkills = editText.getText().toString();
        dataModel.saveSkills(newSkills);
        putFragment(false);

        InputMethodManager inputMethodManager
                                    = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        Toast.makeText(this, "Skills were updated", Toast.LENGTH_SHORT).show();
    }

    private void onItemSelected(final int newSelectedItemPosition) {
        dataModel.setSelectedItemPosition(newSelectedItemPosition);
        View editText = findViewById(R.id.skills_edit_text);
        if(editText != null) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        putFragment(false);

//        If we are in portrait orientation make ListView scroll to the header.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((ListView) findViewById(R.id.list_view)).smoothScrollToPosition(0);
        }
    }

    private void putFragment(boolean isEditFragment) {
        Fragment newFragment;
        if (isEditFragment) {
            newFragment = new SkillsEditFragment();
        } else {
            newFragment = new SkillsInspectFragment();
        }
        newFragment.setArguments(dataModel.getSelectedInBundle());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.info_skills_layout, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        DataTuple dataInLists = dataModel.getDataInLists();
        outState.putStringArrayList(DataModel.NAME, dataInLists.namesList);
        outState.putStringArrayList(DataModel.SURNAME, dataInLists.surnamesList);
        outState.putStringArrayList(DataModel.SKILLS, dataInLists.skillsList);
        outState.putInt(DataModel.SELECTED_ITEM_POSITION, dataModel.getSelectedItemPosition());
    }
}

package com.noveogroup.task2.model;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public final class DataModel {
    public final static String NAME = "com.noveogroup.task2.NAME";
    public final static String SURNAME = "com.noveogroup.task2.SURNAME";
    public final static String SKILLS = "com.noveogroup.task2.SKILLS";
    public final static String SELECTED_ITEM_POSITION
                                = "com.noveogroup.task2.SELECTED_ITEM_POSITION";
    private ArrayList<HashMap<String, String>> data;
    private int selectedItemPosition = 0;
    private int offset = -1;

    public DataModel(ArrayList<String> namesList,
                     ArrayList<String> surnamesList,
                     ArrayList<String> skillsList) {
        data = new ArrayList<HashMap<String, String>>();
        if(namesList == null || surnamesList == null || skillsList == null
                             || namesList.size() != surnamesList.size()
                             || namesList.size() != skillsList.size()) {
            for (int i = 1; i < 21; ++i) {
                HashMap<String, String> temp_map = new HashMap<String, String>();
                temp_map.put(NAME, String.format("Name %d", i));
                temp_map.put(SURNAME, String.format("Surname %d", i));
                temp_map.put(SKILLS, String.format("Skills %d", i));
                data.add(temp_map);
            }
        }
        else {
            for(int i = 0; i < namesList.size(); ++i) {
                HashMap<String, String> temp_map = new HashMap<String, String>();
                temp_map.put(NAME, namesList.get(i));
                temp_map.put(SURNAME, surnamesList.get(i));
                temp_map.put(SKILLS, skillsList.get(i));
                data.add(temp_map);
            }
        }
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    public void setSelectedItemPosition(int newSelectedItemPosition) {
        if(newSelectedItemPosition < 0 || newSelectedItemPosition + offset < 0
                                       || newSelectedItemPosition + offset >= data.size()) {
            selectedItemPosition = 0;
        }
        else {
            selectedItemPosition = newSelectedItemPosition;
        }
    }

    public void saveSkills(String newSkills) {
        if(selectedItemPosition != 0) {
            data.get(selectedItemPosition + offset).remove(SKILLS);
            data.get(selectedItemPosition + offset).put(SKILLS, newSkills);
        }
    }

    public Bundle getSelectedInBundle() {
        if(selectedItemPosition == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NAME, data.get(selectedItemPosition + offset).get(NAME));
        bundle.putString(SURNAME, data.get(selectedItemPosition + offset).get(SURNAME));
        bundle.putString(SKILLS, data.get(selectedItemPosition + offset).get(SKILLS));
        return bundle;
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public DataTuple getDataInLists() {
        DataTuple dataInLists = new DataTuple();
        dataInLists.namesList = new ArrayList<String>();
        dataInLists.surnamesList = new ArrayList<String>();
        dataInLists.skillsList = new ArrayList<String>();
        for(HashMap<String, String> item : data) {
            dataInLists.namesList.add(item.get(NAME));
            dataInLists.surnamesList.add(item.get(SURNAME));
            dataInLists.skillsList.add(item.get(SKILLS));
        }
        return dataInLists;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        if(selectedItemPosition != 0) {
            if (selectedItemPosition + offset < 0 || selectedItemPosition + offset >= data.size()) {
                selectedItemPosition = 0;
            }
        }
    }
}

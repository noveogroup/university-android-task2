package com.noveogroup.task2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.noveogroup.task2.R;
import com.noveogroup.task2.model.DataModel;

public class SkillsFragment extends Fragment {
    protected OnEditSaveListener onEditSaveListener;

    public interface OnEditSaveListener {
        void onEdit();
        void onSave();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onEditSaveListener = (OnEditSaveListener) activity;
    }

    protected void fillTextFields(View view, int skillsTextId) {
        Bundle bundle = getArguments();
        TextView nameText = (TextView) view.findViewById(R.id.name_text);
        nameText.setText(bundle.getString(DataModel.NAME));
        TextView surnameText = (TextView) view.findViewById(R.id.surname_text);
        surnameText.setText(bundle.getString(DataModel.SURNAME));
        TextView skillsText = (TextView) view.findViewById(skillsTextId);
        skillsText.setText(bundle.getString(DataModel.SKILLS));
    }
}

package com.noveogroup.task2.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.noveogroup.task2.R;

public final class SkillsEditFragment extends SkillsFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skills_edit_layout, container, false);
        final Button skillsSaveButton = (Button) view.findViewById(R.id.skills_save_button);
        skillsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditSaveListener.onSave();
            }
        });
        final Resources resources = getResources();
        final Drawable drawable = resources.getDrawable(R.drawable.save_icon);
        drawable.setBounds(0, 0,
                resources.getDimensionPixelSize(R.dimen.drawable_size),
                resources.getDimensionPixelSize(R.dimen.drawable_size));

        skillsSaveButton.setCompoundDrawables(drawable, null, null, null);

        fillTextFields(view, R.id.skills_edit_text);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = (EditText) getActivity().findViewById(R.id.skills_edit_text);
        editText.setSelection(editText.getText().length());
        if (editText.requestFocus()) {
            InputMethodManager inputMethodManager
               = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        }
    }
}

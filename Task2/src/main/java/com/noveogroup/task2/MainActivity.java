package com.noveogroup.task2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity {
	Mode mode = Mode.NO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.list_container, new EmployeeListFragment());

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			switch (mode){
				case NO:
					break;
				case READ:
					transaction.replace(R.id.info_container, new EmployeeInfoFragment());
					break;
				case WRITE:
					transaction.replace(R.id.info_container, new EmployeeInfoEditFragment());
					break;
			}
		}
		getFragmentManager().executePendingTransactions();
		transaction.commit();
    }
}

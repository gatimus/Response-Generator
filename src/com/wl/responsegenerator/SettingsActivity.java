package com.wl.responsegenerator;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				addPreferencesFromResource(R.xml.settings);
		}else{ 
				getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
		}
	}

}

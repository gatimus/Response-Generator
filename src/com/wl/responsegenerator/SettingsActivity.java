package com.wl.responsegenerator;

import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceChangeListener{
	
	private final String TAG = "Settings:";

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				addPreferencesFromResource(R.xml.settings);
				PreferenceScreen prefScreen = (PreferenceScreen) findPreference("pref_screen");
				prefScreen.setOnPreferenceChangeListener(this);
				Log.i(TAG, "Preference Method- Activity");
		}else{ 
				getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
				Log.i(TAG, "Preference Method- Fragment");
		}
	}

	/**
	@SuppressWarnings("deprecation")
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			Preference pref = findPreference(key);
			pref.setSummary(sharedPreferences.getString(key, "10000"));
		}
	}
	**/
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			Preference pref = findPreference(preference.getKey());
			pref.setSummary(newValue.toString());
		}
		Log.i(TAG, "Preference Changed");
		return false;
	}
	
	private class SettingsFragment extends PreferenceFragment implements OnPreferenceChangeListener{
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);
			PreferenceScreen prefScreen = (PreferenceScreen) findPreference("pref_screen");
			prefScreen.setOnPreferenceChangeListener(this);
		}

		/**
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			Preference pref = findPreference(key);
			pref.setSummary(sharedPreferences.getString(key, "10000"));
		}
		**/

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			Preference pref = findPreference(preference.getKey());
			pref.setSummary(newValue.toString());
			Log.i(TAG, "Preference Changed");
			return false;
		}

	}

}

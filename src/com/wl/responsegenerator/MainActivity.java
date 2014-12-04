package com.wl.responsegenerator;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements MainFragment.FragmentListener, Shaker.Callback{
	
	FragmentManager fragmentManager;
	private MainFragment mainFragment;
	private ResponseFragment responseFragment;
	private Generator generator;
	private Configuration config;
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences pref;
	private Shaker shaker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shaker=new Shaker(this, 1.25d, 500, this);
		pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		config = getResources().getConfiguration();
		if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			setContentView(R.layout.portrait_layout);
		}
		else{
			setContentView(R.layout.landscape_layout);
		}
		fragmentManager = getFragmentManager();
		mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.fragmentMain);
		mainFragment.setRetainInstance(true);
		responseFragment = (ResponseFragment) fragmentManager.findFragmentById(R.id.fragmentResponse);
		responseFragment.setRetainInstance(true);
		generator = new Generator(this);
		responseFragment.displayResponse(pref.getString("response", "Response Fragment"));
	} //onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	} //onCreateOptionsMenu(Menu menu)

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
		case R.id.action_settings :
			Intent intent = new Intent();
            intent.setClass(this, SettingsActivity.class);
            startActivity(intent);
			break;
		case R.id.action_about :
			break;
		case R.id.action_help :
			break;
		case R.id.action_quit : System.exit(0);
			break;
		default : 
			break;
		}
		return super.onOptionsItemSelected(item);
	} //onOptionsItemSelected(MenuItem item)
	

	@Override
	public void onButtonClick() {
		responseFragment.displayResponse(generator.generateResponse());
	}
	
	@Override
    protected void onPause(){
		super.onPause();
		pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("response", responseFragment.getResponse());
		editor.commit();
	}

	@Override
	public void shakingStarted() {
		responseFragment.displayResponse(generator.generateResponse());
	}

	@Override
	public void shakingStopped() {
		// do nothing
	}
	
} //class

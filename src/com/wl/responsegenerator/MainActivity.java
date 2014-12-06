package com.wl.responsegenerator;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements MainFragment.FragmentListener, Shaker.Callback{
	
	private final String TAG = "Main:";
	private FragmentManager fragmentManager;
	private Fragment mainFragment;
	private Fragment responseFragment;
	private Generator generator;
	private Configuration config;
	public static final String PREFS_NAME = "MyPrefsFile";
	private SharedPreferences pref;
	private Shaker shaker;
	private DialogFragment aDialog;
	private DialogFragment hDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "create");
		super.onCreate(savedInstanceState);
		shaker=new Shaker(this, 1.5d, 500, this);
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
		((ResponseFragment)responseFragment).displayResponse(pref.getString("response", ""));
		((MainFragment)mainFragment).setQuestion(pref.getString("question", ""));
		aDialog = new AboutDialog();
		hDialog = new HelpDialog();
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
			Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
			break;
		case R.id.action_about : aDialog.show(fragmentManager, "About");
			break;
		case R.id.action_help : hDialog.show(fragmentManager, "Help");
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
		Log.i(TAG, "button click");
		((ResponseFragment)responseFragment).displayResponse(generator.generateResponse());
	}
	
	@Override
    protected void onPause(){
		Log.i(TAG, "pause");
		super.onPause();
		pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("response", ((ResponseFragment)responseFragment).getResponse());
		editor.putString("question", ((MainFragment)mainFragment).getQuestion());
		editor.commit();
	}

	@Override
	public void shakingStarted() {
		Log.i(TAG, "shaking start");
		((ResponseFragment)responseFragment).displayResponse(generator.generateResponse());
	}

	@Override
	public void shakingStopped() {
		Log.i(TAG, "shaking stop");
		// do nothing
	}
	
} //class

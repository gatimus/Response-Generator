package com.wl.responsegenerator;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements MainFragment.FragmentListener{
	
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	private MainFragment mainFragment;
	private ResponseFragment responseFragment;
	private Generator generator;
	private Configuration config;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		config = getResources().getConfiguration();
		if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			setContentView(R.layout.portrait_layout);
		}
		else{
			setContentView(R.layout.landscape_layout);
		}
		fragmentManager = getFragmentManager();
		//fragmentTransaction = fragmentManager.beginTransaction();
		mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.fragmentMain);
		responseFragment = (ResponseFragment) fragmentManager.findFragmentById(R.id.fragmentResponse);
		//fragmentTransaction.replace(R.id.fragmentMain, mainFragment);
		//fragmentTransaction.replace(R.id.fragmentResponse, responseFragment);
		//fragmentTransaction.commit();
		generator = new Generator(this);
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
	
} //class

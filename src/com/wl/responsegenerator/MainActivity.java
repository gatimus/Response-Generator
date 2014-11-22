package com.wl.responsegenerator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	private Button btnGenerate;
	private TextView result;
	private Generator generator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnGenerate = (Button) findViewById(R.id.button1);
		btnGenerate.setOnClickListener(this);
		result = (TextView) findViewById(R.id.textView1);
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	} //onOptionsItemSelected(MenuItem item)
	
	@Override
	public void onClick(View v) {
		if(v.getId() == btnGenerate.getId()){
			result.setText(generator.generateResponse());
		} //if button is clicked generate and display response 
	} //onClick(View v)
	
} //class

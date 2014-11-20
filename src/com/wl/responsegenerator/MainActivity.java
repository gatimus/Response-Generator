package com.wl.responsegenerator;

import java.util.Random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	private Button btnGenerate;
	private TextView result;
	private String[] neutral;
	private String[] positive;
	private String[] negative;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnGenerate = (Button) findViewById(R.id.button1);
		btnGenerate.setOnClickListener(this);
		result = (TextView) findViewById(R.id.textView1);
		neutral = getResources().getStringArray(R.array.Neutral);
		positive = getResources().getStringArray(R.array.Positive);
		negative = getResources().getStringArray(R.array.Negative);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int generate() {
		Random random = new Random();
		double percent = 0.5D;
		double deviation = 0D;
		int n = 10_000;
		int positive = 0;
		for (int i = 0; i < n; i++) {
			if(random.nextBoolean()) positive ++;
		}
		percent = (double)positive/n;
		deviation = 0.5D - percent;
		deviation = deviation*1000D;
		return (int)deviation;
	}
	
	public String generateResponse() {
		int select = generate();
		String response = null;
		try{
		if(Math.abs(select)<neutral.length){
			response = neutral[Math.abs(select)];
		}
		else if(select>0){
			if(select<neutral.length+positive.length){
				response = positive[Math.abs(select)-neutral.length];
			}
			else{
				response = positive[positive.length-1];
			}
		}
		else if(select<0){
			if(Math.abs(select)<neutral.length+negative.length){
				response = negative[Math.abs(select)-neutral.length];
			}
			else{
				response = negative[negative.length-1];
			}
		}
		}
		catch(ArrayIndexOutOfBoundsException e){
			Log.e("index",String.valueOf(select));
		}
		return response;
	}

	@Override
	public void onClick(View v) {
		
		result.setText(generateResponse());
	}
	
}

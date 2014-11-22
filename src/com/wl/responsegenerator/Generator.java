package com.wl.responsegenerator;

import java.util.Random;

import android.content.Context;
import android.util.Log;

public class Generator {
	
	private String[] neutral;
	private String[] positive;
	private String[] negative;
	
	public Generator(Context context) {
		neutral = context.getResources().getStringArray(R.array.Neutral);
		positive = context.getResources().getStringArray(R.array.Positive);
		negative = context.getResources().getStringArray(R.array.Negative);
	}
	
	public int generateInt() {
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
		int select = generateInt();
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

}
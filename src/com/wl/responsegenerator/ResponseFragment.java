package com.wl.responsegenerator;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResponseFragment extends Fragment {
	
	private View v;
	private Vibrator vib;
	private TextView response;
	private final String TAG = "Response:";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_response, container, false);
		response = (TextView) v.findViewById(R.id.responseView);
		vib =(Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		return v;
	}
	
	public void displayResponse(String s){
		if(vib.hasVibrator()){
			Log.i(TAG,"vibrate");
			vib.vibrate(500);
		} else {
			Log.i(TAG,"no vibrator");
		}
		response.setText(s);
	}
	
	public String getResponse(){
		return response.getText().toString();
	}
	
}

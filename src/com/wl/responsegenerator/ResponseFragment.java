package com.wl.responsegenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResponseFragment extends Fragment {
	
	private View v;
	private TextView response;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_response, container, false);
		response = (TextView) v.findViewById(R.id.responseView);
		return v;
	}
	
	public void displayResponse(String s){
		response.setText(s);
	}
	
	public String getResponse(){
		return response.getText().toString();
	}
	
}

package com.wl.responsegenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment implements OnClickListener{
	
	private View view;
	private Button btn;
	FragmentListener activityCallback;
	
	public interface FragmentListener {
		public void onButtonClick();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_main, container, false);
		activityCallback = (FragmentListener) inflater.getContext();
		btn = (Button) view.findViewById(R.id.btnSubmit);
		btn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == btn.getId()){
			activityCallback.onButtonClick();
		} //if button is clicked generate and display response 
	}

}

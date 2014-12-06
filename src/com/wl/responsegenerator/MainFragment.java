package com.wl.responsegenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainFragment extends Fragment implements OnClickListener, OnEditorActionListener{
	
	private View v;
	private Button btn;
	private FragmentListener activityCallback;
	private EditText question;
	
	public interface FragmentListener {
		public void onButtonClick();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_main, container, false);
		question = (EditText) v.findViewById(R.id.etQuestion);
		activityCallback = (FragmentListener) inflater.getContext();
		btn = (Button) v.findViewById(R.id.btnSubmit);
		btn.setOnClickListener(this);
		question.setOnEditorActionListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == btn.getId()){
			activityCallback.onButtonClick();
		} //if button is clicked generate and display response 
	}
	
	public String getQuestion(){
		return question.getText().toString();
	}
	
	public void setQuestion(String text){
		question.setText(text);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE){
			activityCallback.onButtonClick();
		}
		return false;
	}

}

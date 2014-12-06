package com.wl.responsegenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AboutDialog extends DialogFragment {
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("About");
		builder.setIcon(R.id.icon);
		builder.setMessage(R.string.about_msg);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   dialog.dismiss();
	           }
		});
		return builder.create();
	}

}

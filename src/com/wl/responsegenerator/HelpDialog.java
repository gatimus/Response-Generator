package com.wl.responsegenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class HelpDialog extends DialogFragment {
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Help");
		builder.setIcon(R.id.icon);
		builder.setMessage(R.string.help_msg);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   dialog.dismiss();
	           }
		});
		return builder.create();
	}

}

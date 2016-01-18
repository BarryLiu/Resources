package com.example.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends Activity {

	private TextView tv_text;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_content);
		
		Intent intent = getIntent();
 		String content = intent.getStringExtra("content");

 		FragmentManager manager = getFragmentManager();
		
		RightFragment rf = (RightFragment) manager.findFragmentById(R.id.frag_right);
		rf.setContent(content);
	}

	 

}

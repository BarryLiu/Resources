package com.example.fragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ContentActivity extends Activity {

	private TextView tv_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_content);
		
		Intent intent = getIntent();
		String content = intent.getStringExtra("content");
		tv_text = (TextView) findViewById(R.id.tv_text);
		tv_text.setText(content);
	}

	 

}

package com.example.work9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TwoActivity extends Activity {

	private TextView tv_two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		tv_two = (TextView) findViewById(R.id.tv_two);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int age = intent.getIntExtra("age", 0);
		
		tv_two.setText("name:"+name+"  age:"+age);
	}
	

}

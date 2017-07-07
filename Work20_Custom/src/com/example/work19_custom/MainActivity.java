package com.example.work19_custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void toSecond(View v) {
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);

		startActivity(intent);
	}

	public void toThree(View v) {
		Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
		startActivity(intent);
	}
	public void toFour(View v) {
		Intent intent = new Intent(MainActivity.this, FourActivity.class);
		startActivity(intent);
	}
}

package com.example.work7;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *  3.设置全局可读写模式，再次测试
 * @author Barry
 *
 */
public class ThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.three, menu);
		return true;
	}

}

package com.example.ed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	int pos;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pos = (int) event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (pos - event.getX() > 100) {
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
				// 界面跳转动画
				overridePendingTransition( R.anim.right_in,R.anim.left_out);
				finish();
				return true;
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}

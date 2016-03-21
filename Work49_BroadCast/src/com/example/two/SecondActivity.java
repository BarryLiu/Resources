package com.example.two;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.wbc.R;

/**
 * 热启动		其他App中的调用 这个的
 * @author Barry
 *
 */
public class SecondActivity extends Activity {

	MyBroadCast resever ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		resever = new MyBroadCast();
		
		registerReceiver(resever, new IntentFilter("test.test"));
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(resever);
	}
	
	public void click(View v){
		Intent intent = new Intent();
		intent.putExtra("data", "hello, I'm Tom!");
		intent.setAction("test.test");
		
		sendBroadcast(intent);
	}

	class MyBroadCast extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("来了");
			System.out.println(intent.getStringExtra("data"));
		}
	}
}

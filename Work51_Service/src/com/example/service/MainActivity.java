package com.example.service;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	ServiceConnection serviceConnection;
	MyService myService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 加载一个音乐
		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
			}// 连接Service的时候

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				myService = ((MyService.MyBinder) service).getService();
			}
		};
	}

	public void startMusic(View v) {
		myService.startMusic();
	}

	public void stopMusic(View v) {
		myService.stopMusic();
	}

	public void startService(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyService.class);
		startService(intent);
	}

	public void stopService(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyService.class);
		stopService(intent);
	}

	public void bindService(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MyService.class);
		// 绑定服务
		bindService(intent, serviceConnection,// 当服务器当前触发的相关事件
				BIND_AUTO_CREATE);// 设置如果服务没有启动，绑定的时候就自动启动
	}

	public void unBindService(View v) {
		unbindService(serviceConnection);
	}

	int url = 1;
	public  void downLoad(View v) {
		   Intent intent = new Intent();
	        intent.setClass(this,DownLoadService.class);
	        intent.putExtra("url", "下载"+url);
	        startService(intent);
	        url++;
	}
	public static void showTips(String text) {
		Log.d("tag", text);
	}
}

package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import static com.example.service.MainActivity.showTips;

public class MyService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		showTips("onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		showTips("onBind");
		return new MyBinder();
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		showTips("bindService");
		return super.bindService(service, conn, flags);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		showTips("unbindService");
		super.unbindService(conn);
	}
	@Override
	public boolean stopService(Intent name) {
		showTips("stopService");
		return super.stopService(name);
	}

	@Override
	public void onDestroy() {
		showTips("onStartCommand");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		showTips("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	public class MyBinder extends Binder {
		public MyService getService() {
			return MyService.this;
		}
	}

	MediaPlayer mediaPlayer ;
	public void startMusic() {
		mediaPlayer = MediaPlayer.create(this, R.raw.music02);
		mediaPlayer.start();

		showTips("≤•∑≈“Ù¿÷¡À");
	}

	public void stopMusic() {
		mediaPlayer.pause();
		showTips("Õ£÷π≤•∑≈");
	}

}

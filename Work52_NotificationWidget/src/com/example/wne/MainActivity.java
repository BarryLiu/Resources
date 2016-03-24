package com.example.wne;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Notification 消息提示 
 * Widget 窗口小部件
 * 
 * @author Barry
 * 
 */
public class MainActivity extends Activity {
	NotificationManager notificationManager;
	MyBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 从系统得到服务
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		receiver = new MyBroadcastReceiver();
		registerReceiver(receiver, new IntentFilter("com.example.wne"));

		// widget
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

		// 更新widget
		appWidgetManager.updateAppWidget(
				new ComponentName(this, MyWidget.class), getRemoteViews());

	}

	Handler handler = new Handler();
	Runnable r = new Runnable() {
		@Override
		public void run() {
			// 发送通知
			notificationManager.notify(code, createCustomNotifcation()

			);
			handler.postDelayed(r, 1000);
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		handler.removeCallbacks(r);
	}

	int code = 100;

	public void sendNotification(View view) {
		// 创建一般notification
		Notification notification = new NotificationCompat.Builder(this)
				.setWhen(System.currentTimeMillis())
				// 什么时候发送的
				.setSmallIcon(R.drawable.ic_launcher)
				// 图标
				.setTicker("您有一条新的消息")
				.setContentTitle("芳芳")
				.setContentText("晚上老地方见")
				.setContentIntent(
						PendingIntent.getActivity(this, 0, new Intent(this,
								MainActivity.class), 0)).setAutoCancel(true) // 自动消失
				.setDefaults(Notification.DEFAULT_SOUND).build();

		// 发送通知
		notificationManager.notify(code, createCustomNotifcation());

		handler.postDelayed(r, 1000);
	}

	int progress = 0;

	public RemoteViews getRemoteViews() {
		RemoteViews remoteViews = new RemoteViews(getPackageName(),// 包名
				R.layout.notification_views // 布局
		);
		// 设置播放广播
		remoteViews.setOnClickPendingIntent(R.id.btn_play, PendingIntent
				.getBroadcast(this, 1,
						(new Intent("com.example.wne")).putExtra("type", 2),
						PendingIntent.FLAG_UPDATE_CURRENT));
		// 设置暂停广播
		remoteViews.setOnClickPendingIntent(R.id.btn_pause, PendingIntent
				.getBroadcast(this, 2,
						(new Intent("com.example.wne")).putExtra("type", 3),
						PendingIntent.FLAG_UPDATE_CURRENT));

		remoteViews.setProgressBar(R.id.pb, 100, progress++, false);

		return remoteViews;
	}

	public Notification createCustomNotifcation() {

		Notification notification = new NotificationCompat.Builder(this)
				.setContent(getRemoteViews())
				.setSmallIcon(R.drawable.ic_launcher).setTicker("正在播放音乐~~~~~")
				.build();
		return notification;
	};

	class MyBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			int type = intent.getIntExtra("type", -1);
			switch (type) {
			case 2:
				Toast.makeText(MainActivity.this, "播放音乐", Toast.LENGTH_SHORT)
						.show();
				break;
			case 3:
				Toast.makeText(MainActivity.this, "暂停音乐", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				Toast.makeText(MainActivity.this, "我来了", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}
}

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
 * Notification ��Ϣ��ʾ 
 * Widget ����С����
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
		// ��ϵͳ�õ�����
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		receiver = new MyBroadcastReceiver();
		registerReceiver(receiver, new IntentFilter("com.example.wne"));

		// widget
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

		// ����widget
		appWidgetManager.updateAppWidget(
				new ComponentName(this, MyWidget.class), getRemoteViews());

	}

	Handler handler = new Handler();
	Runnable r = new Runnable() {
		@Override
		public void run() {
			// ����֪ͨ
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
		// ����һ��notification
		Notification notification = new NotificationCompat.Builder(this)
				.setWhen(System.currentTimeMillis())
				// ʲôʱ���͵�
				.setSmallIcon(R.drawable.ic_launcher)
				// ͼ��
				.setTicker("����һ���µ���Ϣ")
				.setContentTitle("����")
				.setContentText("�����ϵط���")
				.setContentIntent(
						PendingIntent.getActivity(this, 0, new Intent(this,
								MainActivity.class), 0)).setAutoCancel(true) // �Զ���ʧ
				.setDefaults(Notification.DEFAULT_SOUND).build();

		// ����֪ͨ
		notificationManager.notify(code, createCustomNotifcation());

		handler.postDelayed(r, 1000);
	}

	int progress = 0;

	public RemoteViews getRemoteViews() {
		RemoteViews remoteViews = new RemoteViews(getPackageName(),// ����
				R.layout.notification_views // ����
		);
		// ���ò��Ź㲥
		remoteViews.setOnClickPendingIntent(R.id.btn_play, PendingIntent
				.getBroadcast(this, 1,
						(new Intent("com.example.wne")).putExtra("type", 2),
						PendingIntent.FLAG_UPDATE_CURRENT));
		// ������ͣ�㲥
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
				.setSmallIcon(R.drawable.ic_launcher).setTicker("���ڲ�������~~~~~")
				.build();
		return notification;
	};

	class MyBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			int type = intent.getIntExtra("type", -1);
			switch (type) {
			case 2:
				Toast.makeText(MainActivity.this, "��������", Toast.LENGTH_SHORT)
						.show();
				break;
			case 3:
				Toast.makeText(MainActivity.this, "��ͣ����", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				Toast.makeText(MainActivity.this, "������", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}
}

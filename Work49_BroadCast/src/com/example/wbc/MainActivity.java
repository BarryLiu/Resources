package com.example.wbc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import com.example.two.SecondActivity;

/**
 * 热注册启动	广播接收者
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	BroadcastReceiver mbr;
	String netAction = "android.net.conn.CONNECTIVITY_CHANGE";// 监听网络变化的情况
	ConnectivityManager cm;

	TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_result = (TextView) findViewById(R.id.tv_result);

		mbr = new MyBroadCase();

		Intent intent = new Intent();
		intent.putExtra("data", "要发送的数据");
		sendBroadcast(intent);
		registerReceiver(mbr, new IntentFilter(netAction));
		// 从系统中获取ConnectivityManager；
		cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public void toSecond(View v){
		Intent intent = new Intent(this,SecondActivity.class);
		startActivity(intent);
	}
	
	class MyBroadCase extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {

			// 获取ConnectivityManager
			/*
			 * 它主要负责的是 1 监视网络连接状态 包括（Wi-Fi, GPRS, UMTS, etc） 2 当网络状态改变时发送广播通知 3
			 * 当网络连接失败尝试连接其他网络 4提供API，允许应用程序获取可用的网络状态
			 */
			// 获取当前网络状态
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni != null && ni.isConnectedOrConnecting()) {

				switch (ni.getType()) {// 获取当前网络的状态

				case ConnectivityManager.TYPE_WIFI:// wifi的情况下
					tv_result.setText("wifi");
					break;

				case ConnectivityManager.TYPE_MOBILE:

					switch (ni.getSubtype()) {

					case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
					case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
					case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
					case TelephonyManager.NETWORK_TYPE_1xRTT:
					case TelephonyManager.NETWORK_TYPE_IDEN:
						tv_result.setText("2G");

						break;

					case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
					case TelephonyManager.NETWORK_TYPE_UMTS:
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
					case TelephonyManager.NETWORK_TYPE_HSDPA:
					case TelephonyManager.NETWORK_TYPE_HSUPA:
					case TelephonyManager.NETWORK_TYPE_HSPA:
					case TelephonyManager.NETWORK_TYPE_EVDO_B:
					case TelephonyManager.NETWORK_TYPE_EHRPD:
					case TelephonyManager.NETWORK_TYPE_HSPAP:

						tv_result.setText("3G");

						break;

					case TelephonyManager.NETWORK_TYPE_LTE:

						tv_result.setText("4G");

						break;

					default:

						tv_result.setText("未知网络");

					}

					break;

				default:

					tv_result.setText("未知网络");

				}
			}else{
				tv_result.setText("没有网络");
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mbr);
	}
}

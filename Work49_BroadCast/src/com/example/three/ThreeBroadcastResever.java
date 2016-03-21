package com.example.three;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 冷注册
 * 在Manifest.xml中 配置 等到发出广播的时候就调用这里
 * @author Barry
 *
 */
public class ThreeBroadcastResever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intentActivity = new Intent(context,ThreeActivity.class);
		//
		intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intentActivity);
	}

}

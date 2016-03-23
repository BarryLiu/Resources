package com.example.wp;

import cn.jpush.android.api.JPushInterface;
import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		JPushInterface.init(this);
		JPushInterface.setDebugMode(true);

	}

}

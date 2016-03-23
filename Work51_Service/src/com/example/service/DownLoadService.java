package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DownLoadService extends IntentService {

	public DownLoadService() {
		super("downService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final String url = intent.getStringExtra("url");
		Log.d("tag", "正在下载" + url);
		// 下载....
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Toast.makeText(this,"下载"+url+"完毕",Toast.LENGTH_SHORT).show();
		Log.d("tag", "下载" + url + "完毕");
	}

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "服务关闭");
        Toast.makeText(this, "服务关闭", Toast.LENGTH_SHORT).show();
   }
}

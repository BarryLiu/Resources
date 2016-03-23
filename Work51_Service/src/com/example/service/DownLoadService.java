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
		Log.d("tag", "��������" + url);
		// ����....
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Toast.makeText(this,"����"+url+"���",Toast.LENGTH_SHORT).show();
		Log.d("tag", "����" + url + "���");
	}

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "����ر�");
        Toast.makeText(this, "����ر�", Toast.LENGTH_SHORT).show();
   }
}

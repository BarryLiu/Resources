package com.example.safe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.example.soft.HttpsUrlSSLHelper;
import com.example.work35_md5.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 使用Https 加密
 * @author Barry
 */
public class HttpsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_md5);

		Thread thread = new Thread() {
			@Override
			public void run() {
				sendHttpsRequest("https://192.168.1.116:8443/");
			}
		};
		thread.start();
	}

	public void sendHttpsRequest(String path) {
		try {
			URL url = new URL(path);
			// 使用HttpsUrlConnection
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// 设置信任的站点名字
			conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			// 加载SSL层
			conn.setSSLSocketFactory(HttpsUrlSSLHelper.getSSLContext(this)
					.getSocketFactory());

			int code = conn.getResponseCode();

			InputStream is = conn.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				Log.d("https", line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package com.example.du;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String PATH = "http://192.168.8.14:8080/Http1/";

	int downMax;
	int downLength;
	int fileLength;
	
	private ProgressBar pb;
	private TextView tvProgress;

	boolean isDowning = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pb = (ProgressBar) findViewById(R.id.pb);
		tvProgress = (TextView) findViewById(R.id.tv_progress);

	}

	DownTask task;

	public void download(View v) {

		GetVersionTask gvt = new GetVersionTask();
		gvt.execute();

	}

	class DownTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {

			try {
				String path = PATH + "apk/FastJsonProject.apk";
				URL url = new URL(path);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStream is = conn.getInputStream();

				// 在继续下载的时候要设置头文件
				// 只有在继续下载的时候才设置
				File file = new File(Environment.getExternalStorageDirectory(),
						"file.apk");
				RandomAccessFile raf = new RandomAccessFile(file, "rw");

				if (downMax > 0) { // 移动到当前位置
					conn.setRequestProperty("Range", "bytes=" + downMax);
					raf.seek(downMax);
				} else {
					int length = conn.getContentLength();
					publishProgress(length, 0);
					// 获取下载内容的长度
					downMax = length;
				}

				int len = -1;
				byte[] buffer = new byte[1024];

				OutputStream os = new FileOutputStream(file);
				while ((len = is.read(buffer)) != -1) {
					if (!isDowning) { // 没有下载了
						return "暂停下载";
					}
					os.write(buffer, 0, len);
					publishProgress(downMax, len);
					pb.setProgress(len + pb.getProgress());
				}

				os.flush();
				os.close();
				is.close();
				return "下载成功";
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "下载失败";
		}

		/**
		 * 在主线程调用，用于在下载的过程中更新ui
		 * 
		 * @param values
		 */
		@Override
		protected void onProgressUpdate(Object... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);

			pb.setMax((Integer) values[0]);
			pb.setProgress((Integer) values[1] + pb.getProgress());

			tvProgress.setText(pb.getProgress() * 100 / pb.getMax() + "%");

		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);

			Toast.makeText(MainActivity.this, result.toString(),
					Toast.LENGTH_SHORT).show();
			// 判断文件的大小是否完整
			if (pb.getMax() == pb.getProgress()) {
				// 自动安装
				Intent intent = new Intent();
				// 指定动作
				intent.setAction(Intent.ACTION_VIEW);
				// 指定数据
				File file = new File(Environment.getExternalStorageDirectory(),
						"file.apk");
				intent.setDataAndType(Uri.fromFile(file),
						"application/vnd.android.package-archive");
				startActivity(intent);
			}
		}

	}

	public void pause(View v) {
		isDowning = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (task != null) {
			task.cancel(true);
		}
	}

	class GetVersionTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			try {
				URL url = new URL(PATH+"getVersionServlet");

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line = br.readLine();
				JSONObject obj = new JSONObject(line);

				return obj.getInt("versionCode");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return -1;
		}
		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			try {
				int currVersion = getPackageManager().getPackageInfo(
						getPackageName(), 0).versionCode;
				int serverVersion = (Integer) result;
				if (currVersion < serverVersion) {
					task = new DownTask();
					task.execute();
				} else {
					Toast.makeText(MainActivity.this, "已经是最新版本了",
							Toast.LENGTH_SHORT).show();
				}
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		

	}
}

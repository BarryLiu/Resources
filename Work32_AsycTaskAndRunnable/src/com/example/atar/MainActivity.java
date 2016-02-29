package com.example.atar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String path = "http://192.168.8.14:8080/Http1/getJsonServlet";
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
	}

	public void click1(View v) {
		// 创建一个线程
		TaskGetData task = new TaskGetData();
		// 执行线程
		task.execute();
	}

	class TaskGetData extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			URL url = null;
			String result = null;
			try {
				url = new URL(path);
				// 2，打开网络链接
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// 3，获取响应的内容
				// 通过流获取内容
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				Log.d("httpcontent", sb.toString());
				result = sb.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;

		}

		protected void onPostExecute(Object result) {
			String jsonString = (String) result;
			List<DataBean> list = new ArrayList<DataBean>();
			// 将String转换成json对象从中获取数据
			try {
				JSONArray array = new JSONArray(jsonString);

				// 遍历数组获取其中的内容
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					DataBean db = new DataBean();
					db.setImageName(obj.getString("image"));
					db.setName(obj.getString("str"));
					// 将数据放入到实体类中
					list.add(db);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			listView.setAdapter(new DataAdapter(MainActivity.this, list));
		};

	}

}

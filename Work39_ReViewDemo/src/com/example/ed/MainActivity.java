package com.example.ed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String PATH = "http://192.168.8.10:8080/Work39Server/GetNewsServlet";
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);

		DataTask task = new DataTask();
		task.execute();
	}

	public void toSecond(View v) {
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		startActivity(intent);
	}
	public void toViewPager(View v) {
		Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
		startActivity(intent);
	}

	class DataTask extends AsyncTask {
		@Override
		protected Object doInBackground(Object... arg0) {
			try {
				URL url = new URL(PATH);
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();

				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer sb = new StringBuffer();

				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				is.close();
				br.close();
				JSONArray array = new JSONArray(sb.toString());
				List<NewsBean> data = new ArrayList<NewsBean>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					NewsBean nb = new NewsBean(obj.getString("name"),
							obj.getString("iconPath"));
					data.add(nb);
				}
				URL headUrl = new URL(
						"http://192.168.8.10:8080/Work39Server/images/tv_head.jpg");
				InputStream headIs = headUrl.openConnection().getInputStream();
				Bitmap bmHead = BitmapFactory.decodeStream(headIs);

				Object[] objs = new Object[] { bmHead, data };
				return objs;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			try {
				Object objs[] = (Object[]) result;
				Bitmap bmHead = (Bitmap) objs[0];

				List<NewsBean> list = (List<NewsBean>) objs[1];

				ImageView iv = new ImageView(MainActivity.this);
				// iv.setImageResource(R.drawable.tv_head);
				iv.setImageBitmap(bmHead);
				lv.addHeaderView(iv);
				lv.setAdapter(new MyAdapter(list, MainActivity.this));
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		}
	}

}

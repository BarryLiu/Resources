package com.example.ed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ed.R;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private GridView gv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		gv = (GridView) findViewById(R.id.gv);

	}

	class DataTask extends AsyncTask {
		@Override
		protected Object doInBackground(Object... arg0) {
			try {
				URL url = new URL(MainActivity.PATH);
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

				ImageView iv = new ImageView(SecondActivity.this);
				// iv.setImageResource(R.drawable.tv_head);
				iv.setImageBitmap(bmHead);

				gv.setAdapter(new MyAdapter(list, SecondActivity.this));
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(SecondActivity.this, "..", Toast.LENGTH_SHORT).show();
			}

		}

	}

	// 打开视频的隐式调用
	public void openVideo() {
		String url = "http://192.168.1.116:8080/ExamServer/mp4/video.mp4";
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "video/mp4");
		startActivity(intent);
	}
}

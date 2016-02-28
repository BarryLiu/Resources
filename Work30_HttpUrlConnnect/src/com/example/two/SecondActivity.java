package com.example.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.huc.R;

public class SecondActivity extends Activity {
	public static final String path = "http://192.168.1.103:8080/Http1/getJsonServlet";
	private ListView listView;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 整个网页的内容 有标题 有正文
			String string = msg.obj.toString();
			// 只要正文内容 是json 格式的数据 ， 需要解析
			String str = Html.fromHtml(string).toString();

			try {
				// 解析数据
				JSONArray array = new JSONArray(str);
				List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					String param1 = obj.getString("str");
					String param2 = obj.getString("image");

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("str", param1);
					map.put("image", param2);

					data.add(map);
				}
				// 设置适配器
				SimpleAdapter adapter = new SimpleAdapter(SecondActivity.this,
						data, R.layout.lv_item,
						new String[] { "str", "image" }, new int[] {
								R.id.textView1, R.id.textView2 });
				listView.setAdapter(adapter);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		listView = (ListView) findViewById(R.id.listView);
	}

	// 根据 服务器的连接得到json数据 放到 ListView中
	public void click1(View v) {
		new Thread() {
			public void run() {
				try {
					URL url = new URL(path);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					String line = "";
					StringBuffer sb = new StringBuffer();
					while ((line = br.readLine()) != null) {
						sb.append(br);
					}

					Message msg = handler.obtainMessage();
					msg.obj = sb.toString();

					handler.sendMessage(msg);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

}

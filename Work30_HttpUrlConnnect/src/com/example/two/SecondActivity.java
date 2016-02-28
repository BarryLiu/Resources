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
import android.widget.Toast;

import com.example.huc.R;

/**
 * 得到数据用  ListView显示
 * @author Barry
 *
 */
public class SecondActivity extends Activity {
	public static final String path = "http://192.168.8.14:8080/Http1/getJsonServlet";
	private ListView listView;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// �����ҳ������ �б��� ������
			String string = msg.obj.toString();
			// ֻҪ�������� ��json ��ʽ����� �� ��Ҫ����
			String str = Html.fromHtml(string).toString();
			try {
				// �������
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
				// ����������
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

	// ��� �������l�ӵõ�json��� �ŵ� ListView��
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
						sb.append(line);
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

package com.example.four;

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
import android.widget.TextView;

import com.example.DataBean;
import com.example.Util;
import com.example.huc.R;
/*
 *  得到数据用  ListView显示 
 *  显示的图片 是从网络上得到的    没有用到图片的三级缓存();
 */
public class FourActivity extends Activity {
 	 
	private ListView listView;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 得到传递过来的数据
			String string = msg.obj.toString();
			//转换json格式
			String str = Html.fromHtml(string).toString();

			try {
				// 定义 json数组装载数据
				JSONArray array = new JSONArray(str);
				
				List<DataBean> data =new ArrayList<DataBean>();
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					String param1 = obj.getString("str");
					String param2 = obj.getString("image");

					DataBean dBean=new DataBean(param1, param2);
					data.add(dBean);
				}
				// 得到 适配器的对象
				DataAdapter adapter  =new DataAdapter(FourActivity.this, data); 
				listView.setAdapter(adapter);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		TextView tvTips = (TextView) findViewById(R.id.tv_tips);
		tvTips.setText("以三级缓存的方式加载用（LruCache）, 首先是内存缓存,再文件缓存,最后是网络缓存");
		
		
		listView = (ListView) findViewById(R.id.listView);
		
	}

	// 点击按钮从服务器得到数据
	public void click1(View v) {
		new Thread() {
			public void run() {
				try {
					URL url = new URL(Util.path+"getJsonServlet");
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
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

}

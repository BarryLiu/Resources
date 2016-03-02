package com.example.volley;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ov.MainActivity;
import com.example.ov.R;
import com.google.gson.reflect.TypeToken;

/**
 * Volley多用于对数据发送多  数据不大的情况
 * listView的item加载多用Volley 
 * 
 * @author Barry
 *
 */
public class VolleyActivity extends Activity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley);
		listView = (ListView) findViewById(R.id.listView1);
	}

	public void volley(View v) {

		final RequestQueue queue = ((MyApplication) getApplication())
				.getRequestQueue();
		String path = MainActivity.PATH + "getJsonServlet";
		// 创建一个请求
		StringRequest stringRequest = new StringRequest(path,
				new Listener<String>() {
					@Override
					public void onResponse(String response) {
						//response = [{"str":"第0条数据","image":"jpg_1.png"},{"str":"第1条数据","image":"jpg_2.png"},{"str":"第2条数据","image":"jpg_3.png"},{"str":"第3条数据","image":"jpg_4.png"},{"str":"第4条数据","image":"jpg_5.png"},{"str":"第5条数据","image":"jpg_6.png"},{"str":"第6条数据","image":"jpg_7.png"},{"str":"第7条数据","image":"jpg_8.png"},{"str":"第8条数据","image":"jpg_9.png"},{"str":"第9条数据","image":"jpg_10.png"},{"str":"第10条数据","image":"jpg_11.png"},{"str":"第11条数据","image":"jpg_12.png"},{"str":"第12条数据","image":"jpg_13.png"},{"str":"第13条数据","image":"jpg_14.png"},{"str":"第14条数据","image":"jpg_15.png"},{"str":"第15条数据","image":"jpg_16.png"},{"str":"第16条数据","image":"jpg_17.png"},{"str":"第17条数据","image":"jpg_18.png"},{"str":"第18条数据","image":"jpg_19.png"},{"str":"第19条数据","image":"jpg_20.png"}]
						// 直接使用gson获取数组
						List<DataBean> list = (List<DataBean>) JsonUtils
								.fromJson(response,
										new TypeToken<ArrayList<DataBean>>() {
										});
					 	listView.setAdapter(new DataAdapter( VolleyActivity.this,list,
								queue));
					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		// 将请求放入到队列中
		queue.add(stringRequest);
	}

}

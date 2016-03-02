package com.example.ov;

import java.io.IOException;

import com.example.volley.VolleyActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
/**
 * OkHttp 用于得到json的数据  比volley快一点
 * 它没有封装对  图片的下载
 * 
 * 大多国外程序员用 OkHttp + volley 两个框架
 * 而中国程序员多用   XUtils3 框架
 * @author Barry
 *
 */
public class MainActivity extends Activity {
	public static final String PATH = "http://192.168.8.14:8080/Http1/";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click1(View v) {
		//创建一个 OkHttpClient 
		OkHttpClient okClient = new OkHttpClient();
		//创建一个Request请求
		Request request = new Request.Builder().url(PATH).build();
		//创建一个call 
		Call call = okClient.newCall(request);
		
		//发送call 
		call.enqueue(new Callback() {
			// 因为这是回调   在后面执行
			@Override
			public void onResponse(Response response) throws IOException {
				final String json =response.body().string();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
					}
				});
				//会在result1的后面
				  Log.d("result", json);
			}
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				 
			}
		});
		
		Log.d("result1", "result1");
	}

	public void click2(View v) {
		Intent intent =new Intent(MainActivity.this,VolleyActivity.class);
		startActivity(intent);
	}
}

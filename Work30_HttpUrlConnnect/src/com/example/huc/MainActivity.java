package com.example.huc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.two.SecondActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * 从服务器中得到数据
 *   
 * @author Barry
 */
public class MainActivity extends Activity {

	private TextView textView;
	public static final String path = "http://192.168.1.103:8080/Http1/";
	
	//得到数据后， 刷新界面
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String str= (String) msg.obj;
			String dataStr = Html.fromHtml(str).toString();
			textView.setText(dataStr);
		};
	};
	
	public void toSecond(View v){
		Intent intent = new Intent(MainActivity.this,SecondActivity.class);
		startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView);
	}
	//点击链接 到服务器 直接把放回的字符串设置到TextView中
	public void click1(View v){

		//不能再主线程中直接发送请求 ，
		Thread thread = new Thread(){
			public void run() {
				try {
					//连接
					URL url = new URL(path);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					
					//将得到的参数转换成字符串
					BufferedReader br =new BufferedReader(new InputStreamReader(is));
					StringBuffer sb =new StringBuffer();
					String line  = "";
					while((line = br.readLine())!=null){
						sb.append(line);
					}
					
					//子线程不能刷新UI界面  ， 发送 请求道Handler 在handlerMessage中刷新界面
					Message msg = handler.obtainMessage();
					msg.obj = sb.toString();
					handler.sendMessage(msg);
				
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		};
		thread.start();
	}
	
	

}

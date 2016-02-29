package com.example.huc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.Util;
import com.example.three.ThreeActivity;
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
 * 简单的从服务器得到数据
 *   
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	private TextView textView;
	
	//handler改变UI界面
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {

			String str= (String) msg.obj;
			String dataStr = Html.fromHtml(str).toString();
			
			textView.setText(dataStr);
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView);
	}
	
	//点击button 按钮
	public void click1(View v){

		//不能从主线程发送网络数据
		Thread thread = new Thread(){
			public void run() {
				try {
					//创建Url请求
					URL url = new URL(Util.path);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					
					//接收数据
					BufferedReader br =new BufferedReader(new InputStreamReader(is));
					StringBuffer sb =new StringBuffer();
					String line  = "";
					while((line = br.readLine())!=null){
						sb.append(line);
					}
					
					//数据传递到hander 里面   用 handlerMassage 的方法得到Ui显示出来
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
	public void click2(View v){
		Intent intent =new Intent(MainActivity.this,SecondActivity.class);
		startActivity(intent);
	}
	public void click3(View v){
		Intent intent = new Intent(MainActivity.this,ThreeActivity.class);
		startActivity(intent);
		
	}

}

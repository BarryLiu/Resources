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
 * �ӷ������еõ�����
 *   
 * @author Barry
 */
public class MainActivity extends Activity {

	private TextView textView;
	public static final String path = "http://192.168.1.103:8080/Http1/";
	
	//�õ����ݺ� ˢ�½���
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
	//������� �������� ֱ�ӰѷŻص��ַ������õ�TextView��
	public void click1(View v){

		//���������߳���ֱ�ӷ������� ��
		Thread thread = new Thread(){
			public void run() {
				try {
					//����
					URL url = new URL(path);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					
					//���õ��Ĳ���ת�����ַ���
					BufferedReader br =new BufferedReader(new InputStreamReader(is));
					StringBuffer sb =new StringBuffer();
					String line  = "";
					while((line = br.readLine())!=null){
						sb.append(line);
					}
					
					//���̲߳���ˢ��UI����  �� ���� �����Handler ��handlerMessage��ˢ�½���
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

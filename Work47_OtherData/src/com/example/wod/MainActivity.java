package com.example.wod;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	
	Handler handler =new Handler(){
		public void handleMessage(android.os.Message msg) {
			String res = (String) msg.obj;
			tv.setText(res);
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.textView);
	}

	public void click(View v) {
	
		JuheDemo jd = new JuheDemo();
	 

			
			new Thread(){
				public void run() {
					try {
						String url = "http://op.juhe.cn/onebox/weather/query";
						Map params = new HashMap();// 请求参数
						params.put("cityname", "温州");// 要查询的城市，如：温州、上海、北京
						params.put("key", JuheDemo.APPKEY);// 应用APPKEY(应用详细页查询)
						params.put("dtype", "");// 返回数据的格式,xml或json，默认json
						String res = JuheDemo.net(url, params, "GET");
						System.out.println("res: "+res);
						
						Message msg = handler.obtainMessage();
						msg.obj = res;
						
						handler.sendMessage(msg);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				};
			}.start();
			
	 
		

	}
}

package com.example.rigster;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.HttpUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends Activity {
	TextView tvContent ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		
		tvContent = (TextView) findViewById(R.id.tv_content);

		
		GetTask task = new GetTask();
		task.execute();
	
	}

	
	class GetTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			
			
			try {
				String path = HttpUtils.path+"/getUserServlet";
				System.out.println(path);
				return HttpUtils.get(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "错误";
		}
		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			//显示用户的详细信息
			String resultStr = result.toString();
			
			try {
				JSONObject obj = new JSONObject(resultStr);
				String name = obj.getString("name");
				String pwd = obj.getString("pwd");
				
				tvContent.setText("用户的名字是："+name+" ;电话是："+pwd);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}

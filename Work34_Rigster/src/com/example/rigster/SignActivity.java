package com.example.rigster;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.HttpUtils;

/**
 * 注册 登陆页面   ， 
 * 
 * @author Barry
 *
 */
public class SignActivity extends Activity {

	private EditText etName;
	private EditText etPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);

		etName = (EditText) findViewById(R.id.et_userName);
		etPwd = (EditText) findViewById(R.id.et_password);
	}

	//注册
	public void register(View v) {
		try {
			HttpTask task = new HttpTask();
			String path = HttpUtils.path + "/registerServlet";
			String body = "";
			JSONObject obj = new JSONObject();
			obj.put("name", etName.getText().toString());
			obj.put("pwd", etPwd.getText().toString());
			body = obj.toString();
			task.execute(path, body);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	//登陆
	public void login(View v) {
		try {
			HttpTask task = new HttpTask();
			String path = HttpUtils.path + "/loginServlet";
			String body = "";
			JSONObject obj = new JSONObject();
			obj.put("name", etName.getText().toString());
			obj.put("pwd", etPwd.getText().toString());
			body = obj.toString();
			task.execute(path, body);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	class HttpTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			String path = (String) params[0];
			String body = (String) params[1];

			try {
				return HttpUtils.post(path, body);
			} catch (IOException e) {
				e.printStackTrace();
				return "网络异常";
			}
		}

		@Override
		protected void onPostExecute(Object result) {
			String logLine = (String) result;
			Toast.makeText(SignActivity.this,logLine,
					Toast.LENGTH_SHORT).show();
			
			if("登陆成功".equals(logLine)){
				Intent intent = new Intent(SignActivity.this,ContentActivity.class);
				startActivity(intent);
			}
		}
	}
}

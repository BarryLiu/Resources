package com.example.mob;

import java.util.HashMap;

import com.example.wod.R;
import com.example.wod.R.id;
import com.example.wod.R.layout;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MobActivity extends Activity {

	private TextView tv;
	private EditText etCode;
	private EditText etPhone;

	public static final String appkey = "1094733204282";
	public static final String appSecrect = "c0ddd0a9f2c89dbe743df71c18ef55c2";

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("提交验证码成功");
				break;
			case 2:
				System.out.println("获取验证码成功");
				break;
			case 3:
				System.out.println("失败");
				break;
				
			default:
				break;
			}
			
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mob);

		SMSSDK.initSDK(this, appkey, appSecrect);

		tv = (TextView) findViewById(R.id.textView);
		etCode = (EditText) findViewById(R.id.et_code);
		etPhone = (EditText) findViewById(R.id.et_phone);

		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {

				if (result == SMSSDK.RESULT_COMPLETE) {
					System.out.println("回调了");
					// 回调完成
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						// 提交验证码成功
						handler.sendEmptyMessage(1);
						System.out.println("提交验证码成功");
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// 获取验证码成功
						pd.dismiss();
						System.out.println("获取验证码成功");
						handler.sendEmptyMessage(2);
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// 返回支持发送验证码的国家列表
						System.out.println(" 返回支持发送验证码的国家列表");
					} else if (event == SMSSDK.RESULT_ERROR) {
						System.out.println("失败");
						handler.sendEmptyMessage(3);
					} else if (event == SMSSDK.RESULT_COMPLETE) {
						System.out.println("成功");
					}
				} else {
					System.out.println("失败了");
					((Throwable) data).printStackTrace();
				}
			}
		};
		SMSSDK.registerEventHandler(eh); // 注册短信回调

		pd = new ProgressDialog(this);

	}

	ProgressDialog pd = null;

	public void click(View v) {
		SMSSDK.getVerificationCode("86", etPhone.getText().toString());
		pd.show();
	}

	public void testclick(View v) {
		SMSSDK.submitVerificationCode("86", etPhone.getText().toString(),
				etCode.getText().toString());

	}
}

package com.jing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 对3.png 图片做个提交验证
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
	/**点击提交按钮*/
	public void submit(View v) {

		EditText pwd = (EditText) this.findViewById(R.id.et_pwd);
		EditText pwd2 = (EditText) this.findViewById(R.id.et_pwd2);
		CheckBox cbXy = (CheckBox) this.findViewById(R.id.cb_xy);
		EditText et_email = (EditText) this.findViewById(R.id.et_email);

		String result = "注册成功";
		if (!cbXy.isChecked())
			result = "必须选择接受协议";
		if (!pwd.getText().toString().equals(pwd2.getText().toString()))
			result = "两次密码不一致！";
		String yxStr = et_email.getText().toString();
		if (yxStr != null && !"".equals(yxStr)) {
			Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
			Matcher m = p.matcher(yxStr);
			if (!m.matches())
				result = "邮箱不正确";
		}
		Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
	}
}

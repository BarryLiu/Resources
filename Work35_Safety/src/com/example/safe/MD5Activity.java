package com.example.safe;

import com.example.soft.AbMd5;
import com.example.work35_md5.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * md5 加密字符串 常用语加密 密码等 - 唯一性 - 不可逆性 - 使用场景:密码保护,文件长度完整性
 * 
 * @author Barry
 * 
 */
public class MD5Activity extends Activity {

	private EditText etInput;
	private TextView tvContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_md5);
		etInput = (EditText) findViewById(R.id.et_input);
		tvContent = (TextView) findViewById(R.id.tv_content);
		tvContent.setText("MD5加密不可逆");
	}

	/**
	 * md5 加密字符串
	 */
	public void encode(View v) {
		String mingwen = etInput.getText().toString();

		String miwen = AbMd5.MD5(mingwen);
		tvContent.setText(miwen);
	}

	/**
	 * md5 不能解密
	 * 
	 * @param v
	 */
	public void decode(View v) {

	}
}

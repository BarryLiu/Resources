package com.example.safe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyPair;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soft.AbRsa;
import com.example.soft.HttpsUrlSSLHelper;
import com.example.work35_md5.R;

/**
 * RSA算法 - 非对称性算法
 * 看不到加密密码   ， 每次加密都不同的值  
 * 
 * @author Barry
 * 
 */
public class RsaActivity extends Activity {

	private EditText etInput;
	private EditText etPwd;
	private TextView tvContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_md5);

		etInput = (EditText) findViewById(R.id.et_input);
		etPwd = (EditText) findViewById(R.id.et_pwd);
		tvContent = (TextView) findViewById(R.id.tv_content);
		
		etPwd.setHint("没有加密密码");
		tvContent.setText("非对称性算法");
		// 提前生成公钥和私有的
		// 直接获取密钥对
		keyPair = AbRsa.generateRSAKeyPair(1024);
	}

	KeyPair keyPair;

	public void encode(View view) {
		String mima = etInput.getText().toString();
		String content;

		// 使用RSA加密
		content = AbRsa.encrypt(mima, keyPair.getPublic());

		tvContent.setText(content);
	}

	public void decode(View view) {
		String miwen = tvContent.getText().toString();

		String mingwen;

		// 使用RSA
		mingwen = new String(AbRsa.decrypt(miwen, keyPair.getPrivate()));

		Toast.makeText(this, mingwen, Toast.LENGTH_SHORT).show();
	}
}

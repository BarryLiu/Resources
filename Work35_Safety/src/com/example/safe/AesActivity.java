package com.example.safe;

import com.example.soft.AbAes;
import com.example.soft.AbMd5;
import com.example.work35_md5.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * AES算法
- 可逆性
- 对称加密
- 使用场景:用于加密消息报文
	有加密密码
 * @author Barry
 *
 */
public class AesActivity extends Activity {

    private EditText etInput;
    private EditText etPwd;
	private TextView tvContent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);

        etInput =   (EditText) findViewById(R.id.et_input);
		etPwd = (EditText) findViewById(R.id.et_pwd);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText("Aes加密");
    }

    /**
	  * Aes 加密字符串
	  */
	 public void encode(View v){
		 String mingwen = etInput.getText().toString();
		 String pwd  = etPwd.getText().toString();
		 String miwen = AbAes.encrypt(pwd, mingwen);
		 tvContent.setText(miwen);
	 }
	 /**
	  * Aes  解密
	  * @param v
	  */
	 public void decode(View v){
		 String mingwen = tvContent.getText().toString();
		 String pwd  = etPwd.getText().toString();
		 String miwen = AbAes.decrypt(pwd, mingwen);
		 tvContent.setText(miwen);
	 }
}

package com.example.work7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 *  4.先用原始的IO方式在/data/data/your_package_name/files下面创建一个文件，
 * 并且根据界面的editText输入的值写入内容 
 * 
 * @author Barry
 *
 */
public class FourActivity extends Activity {

	private EditText et_four;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		
		et_four = (EditText) findViewById(R.id.et_four);
	}

	public void click(View v){
		try {
			File file =new File("four.txt");
			FileOutputStream fos =new FileOutputStream(file);
			fos.write(et_four.getText().toString().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTips("文件没有找到");
		} catch (IOException e) {
			e.printStackTrace();
			showTips("写入失败");
		}
	}

	private void showTips(String text) {
		Toast.makeText(FourActivity.this, text, Toast.LENGTH_SHORT);
	}

}

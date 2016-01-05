package com.example.work7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 1.利用context.OpenFileInput读取文件，显示在textView中
 * @author Barry
 *
 */
public class OneActivity extends Activity{
	private TextView tv_one;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_one);
		
		tv_one = (TextView) this.findViewById(R.id.tv_one);
		read();
	}

	private void read() {
		try {
			FileInputStream is = OneActivity.this.openFileInput("myfirst.txt");
			StringBuffer sb=new StringBuffer();
			byte buffer[]=new byte[1024];
			int len = 0;
			while((len = is.read(buffer))!=-1){
				sb.append(new String(buffer,0,len));
			}
			
			showTips(sb.toString());
			tv_one.setText(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTips("文件没有找到");
		} catch (IOException e) {
			e.printStackTrace();
			showTips("文件读取错误");
		}
	}

	
	private void showTips(String text) {
		Toast.makeText(OneActivity.this, text, Toast.LENGTH_SHORT);
	}
}

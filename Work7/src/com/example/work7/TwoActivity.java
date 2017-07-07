package com.example.work7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;
/**
 * 2.利用context.openFileOutput，分别用MODE_PRIVATE、MODE_APPEND写入文件
 * @author Barry
 *
 */
public class TwoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		try {
			FileOutputStream fos = null;
			fos = TwoActivity.this.openFileOutput("myfirst.txt", MODE_PRIVATE);
//			 fos = TwoActivity.this.openFileOutput("myfirst.txt", TwoActivity.MODE_APPEND);
			
			write(fos,"12345");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTips("没有找到文件");
		}
	}
 
	private void write(FileOutputStream fos,String text) {
		try {
			fos.write(text.getBytes());
			fos.close();
			showTips("保存成功");
		} catch (IOException e) {
			e.printStackTrace();
			showTips("保存失败");
		}
	}

	public void showTips(String text){
		Toast.makeText(TwoActivity.this, text, Toast.LENGTH_SHORT);
	}
}

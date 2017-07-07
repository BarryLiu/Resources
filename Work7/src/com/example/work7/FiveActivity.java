package com.example.work7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

/**
 * 5.Sdcard上创建文件输入内容，并且读取内容
 * @author Barry
 */
public class FiveActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_five);

		File file = new File(Environment.getExternalStorageState(), "five.txt");
		write(file);
	}

	private void write(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write("我是five.txt的内容".getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTops("文件没找到");
		} catch (IOException e) {
			e.printStackTrace();
			showTops("写入错误");
		}

	}

	private void showTops(String string) {
		Toast.makeText(FiveActivity.this, string, Toast.LENGTH_SHORT);
	}

	public void click(View v) {
		File file = new File("five.txt");
		InputStream is;
		try {
			is = new FileInputStream(file);
			StringBuffer sb = new StringBuffer();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			
			System.out.println(sb.toString());
			showTops(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTops("文件没找到");
		} catch (IOException e) {
			e.printStackTrace();
			showTops("读取错误");
		}
	}

}

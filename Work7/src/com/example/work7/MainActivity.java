package com.example.work7;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

/***
 * 1.利用context.OpenFileInput读取文件，显示在textView中
 * 2.利用context.openFileOutput，分别用MODE_PRIVATE、MODE_APPEND写入文件 3.设置全局可读写模式，再次测试
 * 4.先用原始的IO方式在/data/data/your_package_name/files下面创建一个文件，
 * 并且根据界面的editText输入的值写入内容 5.Sdcard上创建文件输入内容，并且读取内容
 * 
 * @author Barry
 * 
 */
public class MainActivity extends Activity {

	private FileInputStream fis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//readFile();
		read();
		// write("哈哈哈哈哈哈sss");
	}

	private void readFile() {
		try {
			fis = MainActivity.this.openFileInput("myfirst.txt");
			StringBuffer sb = new StringBuffer();

			byte buffer[] = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			System.out.println("读完了" + sb.toString());
			showTips("内容是：" + sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			showTips("文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
			showTips("读取错误");
		}
	}

	/**
	 * 将数据写入系统默认位置
	 * 
	 * @param text
	 *            要保存的字符
	 */
	public void write(String text) {
		try {
			// 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
			FileOutputStream fos = this.openFileOutput("myfirst.txt",
					Context.MODE_WORLD_READABLE);
			fos.write(text.getBytes());// 写入
			fos.close(); // 关闭输出流
			// 弹出Toast消息
			Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 读取文件 */
	public void read() {
		try {
			FileInputStream fis = this.openFileInput("myfirst.txt"); // 获得输入流
			// 用来获得内存缓冲区的数据，转换成字节数组
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, length);// 获取内存缓冲区中的数据
			}
			stream.close(); // 关闭
			fis.close();
			//tv.setText(stream.toString()); // 设置文本控件显示内容
			System.out.println("读取成功"+stream.toString());
			Toast.makeText(MainActivity.this, "读取成功"+stream.toString(), Toast.LENGTH_LONG).show();// 弹出Toast消息
		} catch (FileNotFoundException e) {
			Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showTips(String text) {
		Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
	}

}

package com.example.work7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/***
 * 1.利用context.OpenFileInput读取文件，显示在textView中
 * 2.利用context.openFileOutput，分别用MODE_PRIVATE、MODE_APPEND写入文件
 *  3.设置全局可读写模式，再次测试
 * 4.先用原始的IO方式在/data/data/your_package_name/files下面创建一个文件，
 * 并且根据界面的editText输入的值写入内容 5.Sdcard上创建文件输入内容，并且读取内容
 * 
 * @author Barry
 * 
 */
public class MainActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View v){
		Intent intent =new Intent();
		switch (v.getId()) {
		case R.id.btn_one:
			intent.setClass(MainActivity.this, OneActivity.class);
			break;
		case R.id.btn_two:
			intent.setClass(MainActivity.this, TwoActivity.class);
			break;
		case R.id.btn_three:
			intent.setClass(MainActivity.this, ThreeActivity.class);
			break;
		case R.id.btn_four:
			intent.setClass(MainActivity.this, FourActivity.class);
			break;
		}
		this.startActivity(intent);

	}
	 
	private void showTips(String text) {
		Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
	}

}

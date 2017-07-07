package com.example.work18_myview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 自定义 控件 MyView 实现跑马灯效果
 * 
 * 1.添加 attrs.xml 配置自定义的控件的属性
 * 2.layout_main.xml　中的xmlns = "";
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

	
	
}

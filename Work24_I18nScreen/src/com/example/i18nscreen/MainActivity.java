package com.example.i18nscreen;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

/**
 * i18n  改变语言完成国际化
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	public void toChange(View v){
		switch (v.getId()) {
		case R.id.btn_en:
			setLanguage(Locale.US);
			break;
		case R.id.btn_zh:
			setLanguage(Locale.CHINA);
			break;

		default:
			break;
		}
		
	}

	@SuppressLint("NewApi")
	private void setLanguage(Locale locale) {
		//获得资源对象
		Resources resources = getResources();
		//获得设置对象
		Configuration configuration = resources.getConfiguration();
		//获得屏幕参数 ： 主要是分辨率、像素等		
		DisplayMetrics metrics = resources.getDisplayMetrics();
		//设置语言
		configuration.locale=locale;
		//更新设置
		getResources().updateConfiguration(configuration, metrics);
		//重新创建Activity
		recreate();
	}

}

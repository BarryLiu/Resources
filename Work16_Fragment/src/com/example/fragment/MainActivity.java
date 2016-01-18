package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity  implements IGetContent{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
	}

	@SuppressLint("NewApi")
	@Override
	public void setContent(String text) {
		//将接受的数据传递到Fragment中去
        //获取当前Activity中Fragment的管理器
        FragmentManager fm = getFragmentManager();
        RightFragment rightFragment = (RightFragment) fm.findFragmentById(R.id.frag_right);
        rightFragment.setContent(text);
	}

}

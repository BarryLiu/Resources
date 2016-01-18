package com.example.work17_fragmentv4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

/**
 *Fragment是3.0以后出现的版本,V4包的出现就是让3.0以前的版本也可以使用Fragment
 *这里是一个用V4包写的可用于3.0之前用的demo
 * 这个要继承FragmentActivity （AS的不用）
 * @author Barry
 * 
 */
public class MainActivity extends FragmentActivity {
	FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//FragmentManager fm = getFragmentManager();
		fm = getSupportFragmentManager();

		// 添加个Fragment
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.left, new LeftFragment());
		ft.commit();
	}
	//隐藏或显示左边
	public void hide(View v) {
		Fragment leftFragment = fm.findFragmentById(R.id.left);
		FragmentTransaction ft = fm.beginTransaction();
		if (leftFragment.isHidden())
			ft.show(leftFragment);
		else
			ft.hide(leftFragment);
		ft.commit();
	}

}

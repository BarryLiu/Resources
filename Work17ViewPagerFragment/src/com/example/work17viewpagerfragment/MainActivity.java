package com.example.work17viewpagerfragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
/**
 * Fragment 与ViewPager组合实现滑动
 * @author Barry
 *
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewPager vp = (ViewPager) findViewById(R.id.wp);
		
		vp.setAdapter(new VPAdapter(getSupportFragmentManager()));
	}
}

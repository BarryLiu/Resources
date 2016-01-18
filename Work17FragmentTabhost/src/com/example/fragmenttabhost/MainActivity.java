package com.example.fragmenttabhost;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TextView;
/**
 * FragmentTableHost 与 Fragment 的使用
 * 
 * @author Barry
 *
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//FragmentTableHost控件
		FragmentTabHost fth = (FragmentTabHost) findViewById(R.id.fth);
		//依赖于FrameLayout控件
		fth.setup(this,getSupportFragmentManager() , R.id.fl);
		for(int i=0 ; i<5 ; i++){
			TabHost.TabSpec tab = fth.newTabSpec(i+"");
			//tab.setIndicator(view);这里可以写自己 xml布局
			tab.setIndicator("第"+(i+1)+"页");
			
			Bundle b=new Bundle();
			b.putInt("pos", i);
			
			fth.addTab(tab,ContentFragment.class,b);
		}
	}

}

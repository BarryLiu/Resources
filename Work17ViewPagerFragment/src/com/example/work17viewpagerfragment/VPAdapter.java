package com.example.work17viewpagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 用于ViewPager+Fragment的适配器
 * @author Barry
 *
 */
public class VPAdapter extends FragmentPagerAdapter {

	public VPAdapter(FragmentManager fm) {
		super(fm);
	}
	@Override
	public Fragment getItem(int position) {
		
		PagerFragment fragment = new PagerFragment();
		Bundle b = new Bundle();
		b.putInt("pos",position);
		fragment.setArguments(b);
		return fragment;
	}
	
	@Override
	public int getCount() {
		return 3;//页面数
	}

}

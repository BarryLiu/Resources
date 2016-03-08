package com.example.ed;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewPagerActivity extends FragmentActivity {

	private ViewPager vp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);
	
		vp = (ViewPager) findViewById(R.id.vp);
		
		vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return 3;
			}
			
			@Override
			public Fragment getItem(int arg0) {

				
				
				return SelectonFragment.getInstence(arg0);
			}
		});
	}
	
	
	@SuppressLint("ValidFragment")
	static class SelectonFragment extends Fragment{
		
		@SuppressLint("ValidFragment")
		public static SelectonFragment getInstence(int position){
			SelectonFragment fragment = new SelectonFragment();
			Bundle args = new Bundle();
			args.putInt("position", position);
			fragment.setArguments(args );
			
			return fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.frag_main_vp_layout, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv);
			iv.setImageResource(R.drawable.ic_launcher);
			Bundle bundle = getArguments();
			int position = bundle.getInt("position");
			
			Log.d("pos", "第"+(position+1)+"个页面");
			Toast.makeText(getActivity(), "第"+(position+1)+"个页面", Toast.LENGTH_SHORT).show();
			
			return view;
		}
		
	}
	
	
	 
}

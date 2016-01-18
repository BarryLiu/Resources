package com.example.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment控件
 * @author Barry
 *
 */
public class ContentFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		TextView tv=new TextView(getActivity());
		
		int position = getArguments().getInt("pos");//得到此 页面的在创建时赋予的值
		tv.setText("FragmentTableHost+Fragment\n第"+(position+1)+"页的内容");
		tv.setTextSize(20);
		
		//return的内容为 此页的内容
		return tv;
	}
}

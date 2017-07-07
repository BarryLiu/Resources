package com.example.work17viewpagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			int position = getArguments().getInt("pos");
			TextView tv=new TextView(getActivity());
			tv.setText("第"+(position+1)+"页");
			tv.setTextSize(30);
		
		return tv;
	}
}

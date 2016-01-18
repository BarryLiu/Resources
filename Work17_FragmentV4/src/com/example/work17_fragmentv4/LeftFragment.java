package com.example.work17_fragmentv4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class LeftFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.left, null);
		
 		view.setLayoutParams(
 				new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
 						WindowManager.LayoutParams.MATCH_PARENT));
		return view;
	}
}

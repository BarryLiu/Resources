package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class RightFragment extends Fragment  {

	private TextView tv_text;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.right_layout, null);

		Toast.makeText(getActivity(), "rightFragment", Toast.LENGTH_SHORT)
				.show();
		tv_text = (TextView) view.findViewById(R.id.tv_text);
		return view;
	}
	public void setContent(String text) {
		tv_text.setText(text);
	}

}

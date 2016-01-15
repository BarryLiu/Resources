package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LeftFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 1准备数据
		String args[] = new String[15];
		for (int i = 0; i < 15; i++) {
			args[i] = "第" + i + "章";
		}
		// 创建视图
		View view = inflater.inflate(R.layout.left_layout, null);
		ListView lv = (ListView) view.findViewById(R.id.lv);
		// 创建适配器
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1, args);
		// 绑定适配器
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int status = getResources().getConfiguration().orientation;
				
				
				if (status == Configuration.ORIENTATION_LANDSCAPE) {// 是横屏
					TextView tv_text = (TextView) getActivity().findViewById(R.id.tv_text);
					tv_text.setText("第"+position+"章的内容");
					
				} else { // 竖屏
					Intent intent =new Intent(getActivity(),ContentActivity.class);
					intent.putExtra("content", "第"+position+"章的内容");
					
					startActivity(intent);
				}
			}
		});

		return view;
	}
}

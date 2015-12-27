package com.jing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	 
		  createAutoCompleteTextView();
		  
		 createSpinner();
		  
		 createListView();
		  
		  createGridView();
		  

		//testBaseAdapter();

	}

	private void testBaseAdapter() {
		ListView lvSed = (ListView) this.findViewById(R.id.lv_sed);

		// 准备数据
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 16; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "name" + i);
			map.put("id", icons[i % icons.length]);
			map.put("phone", 124214142);
			data.add(map);
		}

		// 创建适配器
		BaseAdapter adapter = new SimpleAdapter(MainActivity.this, data,
				R.layout.lv_person, new String[] { "name", "id", "phone" },
				new int[] {R.id.tv_name,R.id.iv_pid,R.id.tv_phone});
		// 设置适配器
		lvSed.setAdapter(adapter);
	}

	private int[] icons = new int[] { R.drawable.face1, R.drawable.face2,
			R.drawable.face3, R.drawable.face4, R.drawable.face5,
			R.drawable.face6, R.drawable.face7, R.drawable.face8 };

	private void createGridView() {
		GridView gvApps = (GridView) this.findViewById(R.id.gv_apps);
		// 1.得到数据
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 16; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "name" + i);
			map.put("id", icons[i % icons.length]);
			data.add(map);
		}
		// 2.创建适配器
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data,
				R.layout.gv_app, new String[] { "id", "name" }, new int[] {
						R.id.iv_icon, R.id.tv_name });
		// 3.设置适配器
		gvApps.setAdapter(adapter);
	}

	private void createListView() {
		ListView lvStus = (ListView) this.findViewById(R.id.lv_stus);
		// 1.得到数据
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "学生" + i);
			if (i % 2 == 0)
				map.put("status", "正常");
			else
				map.put("status", "不正常");
			data.add(map);
		}

		// 2.创建适配器
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data,
				R.layout.lv_stu, new String[] { "id", "name", "status" },
				new int[] { R.id.tv_id, R.id.tv_name, R.id.tv_status });
		// 3.设置适配器
		lvStus.setAdapter(adapter);
	}

	private void createSpinner() {
		Spinner sCity = (Spinner) this.findViewById(R.id.s_city);
		// 1.得到数据
		String[] citys = new String[] { "湖南", "湖北", "福建", "北京", "上海" };

		// 2.创建适配器
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
				android.R.layout.simple_gallery_item, citys);
		// 3.设置适配器
		sCity.setAdapter(adapter);
	}

	/** 1.实现AutoComplateTextView（配合ArrayAdapter） */
	private void createAutoCompleteTextView() {
		AutoCompleteTextView actvName = (AutoCompleteTextView) this
				.findViewById(R.id.actv_name);

		// 1.得到数据
		String[] names = new String[] { "张三", "张三2", "张杰", "张四", "李四" };
		// 2.创建适配器
		ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
				android.R.layout.simple_gallery_item, names);
		// 3.设置适配器
		actvName.setAdapter(adapter);
	}

}

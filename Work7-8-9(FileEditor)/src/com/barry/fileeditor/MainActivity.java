package com.barry.fileeditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<FileBean> datas = new ArrayList<FileBean>();
	private TextView tv_path;
	private ListView lv_listView;
	private GridView gv_menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initMenu();
		initData("/");
		initEvent();
	}

	private void initMenu() {
		// 1准备数据
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ResUtils.menuIds.length; i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("name", ResUtils.menuNames[i]);
			maps.put("icon", ResUtils.menuIds[i]);
			datas.add(maps);
		}

		// 2.定义适配器
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, datas,
				R.layout.gv_layout, new String[] { "name", "icon" }, new int[] {
						R.id.tv_name, R.id.iv_resId });

		// 设置适配器
		gv_menu.setAdapter(adapter);

	}

	private void initEvent() {
		lv_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FileAdapter adapter = (FileAdapter) parent.getAdapter();
				FileBean fb= (FileBean) adapter.getItem(position);
				
				File file = new File(fb.getFilePath());
				if(file.canRead()){
					if(file.isDirectory())
						initData(file.getPath());
					else
						showTips("不是文件夹不能打打开");
				}else{
					showTips("没有读取的权限");
				}
				
			}

			
		});

	}
	private void showTips(String string) {
		Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();;
	}
	private void initData(String path) {
	
		datas=FileManager.getFileLists(path);

		FileAdapter adapter = new FileAdapter(datas, MainActivity.this);
		lv_listView.setAdapter(adapter);
		
		tv_path.setText(path);
		FileManager.CurrPath=path;
	}

	/** 找到控件并为其赋值 */
	private void initView() {
		tv_path = (TextView) this.findViewById(R.id.tv_path);
		lv_listView = (ListView) this.findViewById(R.id.lv_listView);
		gv_menu = (GridView) this.findViewById(R.id.gv_menu);

	}

}

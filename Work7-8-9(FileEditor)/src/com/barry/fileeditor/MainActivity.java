package com.barry.fileeditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
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
				FileBean fb = (FileBean) adapter.getItem(position);

				File file = new File(fb.getFilePath());
				if (file.canRead()) {
					if (file.isDirectory())
						initData(file.getPath());
					else
						showTips("不是文件夹不能打打开");
				} else {
					showTips("没有读取的权限");
				}

			}
		});
		
		lv_listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				FileAdapter adapter = (FileAdapter) parent.getAdapter();
				final FileBean fileBean = (FileBean) adapter.getItem(position);
				final File file=new File(fileBean.getFilePath());
				Builder builder =new Builder(MainActivity.this);
				builder.setTitle("操作");
				builder.setItems(new String[]{"复制","删除"}, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int witch) {
						switch (witch) {
						case 0://复制
							FileManager.CopyPath = FileManager.CurrPath;
							showTips("复制");
							break;
						case 1://删除
							if(FileManager.getInstance().deleteFile(file))
								showTips("删除成功");
							else
								showTips("删除失败");
							break;
						}
					}
				}).show();
				return true;
			}
		});
		
		
		gv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0://点击手机
					initData("/");
					break;
				case 1:
					String sdCardPath = FileManager.getSdCard();
					if(sdCardPath==null)
						showTips("sd卡不存在");
					else
						initData(sdCardPath);
					break;
				case 2://新建
					Builder builder =new Builder(MainActivity.this);
					builder.setTitle("操作");
					final View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dailog_layout, null);
					builder.setView(v);
					
					builder.setNegativeButton("取消", null);
					builder.setPositiveButton("确定", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
							RadioGroup rg_new =(RadioGroup) v.findViewById(R.id.rg_new);
							File file=new File(FileManager.CurrPath,tv_name.getText().toString());
							boolean flag=true;
							switch (rg_new.getCheckedRadioButtonId()) {
							case R.id.rb_file:
								flag=FileManager.createFile(file);
								break;
							case R.id.rb_folder:
								flag=FileManager.createFolder(file);
								break;
							}
							if(flag)
								showTips("创建成功");
							else
								showTips("创建失败");
						}
					});
					builder.show();
					break;
				case 3://粘贴
					if(FileManager.getInstance().copyFile())
						showTips("粘贴成功");
					else
						showTips("粘贴失败");
					break;
				case 4://退出
					Builder builder2 =new Builder(MainActivity.this);
					 builder2.setTitle("退出吗？");
					 builder2.setPositiveButton("取消", null).setNegativeButton("确定", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							finish();
						}
					}).show();
					break;
 
				}
			}
		});
	}

	private void showTips(String string) {
		Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
		;
	}

	private void initData(String path) {

		datas = FileManager.getFileLists(path);

		FileAdapter adapter = new FileAdapter(datas, MainActivity.this);
		lv_listView.setAdapter(adapter);

		tv_path.setText(path);
		FileManager.CurrPath = path;
	}

	/** 找到控件并为其赋值 */
	private void initView() {
		tv_path = (TextView) this.findViewById(R.id.tv_path);
		lv_listView = (ListView) this.findViewById(R.id.lv_listView);
		gv_menu = (GridView) this.findViewById(R.id.gv_menu);

	}

}

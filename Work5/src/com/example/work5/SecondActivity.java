package com.example.work5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		  lvSed = (ListView) this.findViewById(R.id.lv_sed);
		testListView();
	}
	ArrayList<Student> datas =null;
	ListView lvSed =null;
	
	private static List<CheckBox> cbs=new ArrayList<CheckBox>();
	public static void addCheckBox(CheckBox cb){
		cbs.add(cb);
	}
	private void testListView() {
		// 1
		 datas = new ArrayList<Student>();
		for (int i = 0; i < 20; i++) {
			Student s = null;
			if (i % 2 == 0)
				s = new Student("同学" + i, null, 0, true);
			else
				s = new Student("同学" + i, null, 0, false);
			datas.add(s);
		}

		// 2
		MyAdapter2 adapter = new MyAdapter2(SecondActivity.this, datas);
		
		// 3
		
		lvSed.setAdapter(adapter);
	}
	
	public void toCheck(View v){
		ListView lvSed = (ListView) this.findViewById(R.id.lv_sed);
		long[] ids = lvSed.getCheckedItemIds();
		
		switch (v.getId()) {
		case R.id.btn_chooseAll:
			setSelectAll();
			break;
		case R.id.btn_disChooseAll:
			setDisSelect();
			break;
		case R.id.btn_chooseClear:
			removeSelect();
			break;
		}
	}
	
	/**删除选择项*/
	private void removeSelect() {
		for (int i = 0; i < cbs.size(); i++) {
			if(cbs.get(i).isChecked()){
				datas.remove(i);
				cbs.remove(i);
				i-=1;
			}
		}
		cbs.clear();
		setDisSelect();
	}

	/**设置为全部选中*/
	private void setSelectAll() {
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setIsChecked(true);
		}
		lvSed.setAdapter(new MyAdapter2(SecondActivity.this, datas));
	}
	/**设置为全部不选*/
	private void setDisSelect() {
		for (int i = 0; i < datas.size(); i++) {
			datas.get(i).setIsChecked(false);
		}
		lvSed.setAdapter(new MyAdapter2(SecondActivity.this, datas));
	}
}

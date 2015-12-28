package com.example.work5;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 图片5.1.png</br>
 * 自定义适配器
 * 效果 做出ListView 隔行变色 并优化ListView<br>
 * 
 * 与之有关的文件：MyAdapter.java,Student.java,activity_main.xml,lv_person.xml
 * @author Barry
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		testBaseAdapter();
	}

	//图片资源
	private int[] pics = { R.drawable.face1, R.drawable.face2,
			R.drawable.face3, R.drawable.face4, R.drawable.face5,
			R.drawable.face6, R.drawable.face7, R.drawable.face8 };

	private void testBaseAdapter() {
		// 1.准备数据
		List<Student> data = new ArrayList<Student>();

		for (int i = 0; i < 20; i++) {
			Student s = new Student();
			s.setName("stu" + i);
			s.setTel("1230" + i);
			s.setResId(pics[i % pics.length]);
			data.add(s);
		}
		// 2.定义适配器
		MyAdapter adapter = new MyAdapter(data, MainActivity.this);
		// 3.设置适配器
		ListView lv_sed = (ListView) this.findViewById(R.id.lv_sed);
		lv_sed.setAdapter(adapter);

		lv_sed.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
				Toast.makeText(MainActivity.this, tv_name.getText().toString(),
						Toast.LENGTH_SHORT).show();

			}

		});

	}

}

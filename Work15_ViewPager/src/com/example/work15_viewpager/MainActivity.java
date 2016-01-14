package com.example.work15_viewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {
	ViewPager vp;

	List<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = new ArrayList<View>();
		vp = (ViewPager) findViewById(R.id.vp);

		// 1获取数据
		View v1 = getLayoutInflater().inflate(R.layout.layout_1, null);
		View v2 = getLayoutInflater().inflate(R.layout.layout_2, null);
		View v3 = getLayoutInflater().inflate(R.layout.layout_3, null);

		list.add(v1);
		list.add(v2);
		list.add(v3);

		// 2.创建适配器
		MyAdapter adapter = new MyAdapter();

		// 3.绑定数据
		vp.setAdapter(adapter);

	}

	private class MyAdapter extends PagerAdapter {

		// 获取view的数量
		@Override
		public int getCount() {
			return list.size();
		}

		// 判断view是否是一个对象
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		// 创建每一个View
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 讲对应为置的View放到Vp中
			container.addView(list.get(position));
			// 返回添加的View
			return list.get(position);
		}

		// 删除ViewPager的一个view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView(list.get(position));
		}
	}
}

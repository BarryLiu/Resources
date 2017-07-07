package com.example.work15_viewpager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Barry
 */
public class MainActivity extends Activity {
	private ViewPager vp;
	private List<View> list;
	
	private ContactManager2 manager;
	private ContactAdapter adapter;
	private List<ContactBean> beans;

	private ExpandableListView elv;
	// 下面的白线
	private List<TextView> listLines;
	private int[] listLineIds = { R.id.line_group, R.id.line_contact,
			R.id.line_love };

	private RadioGroup rg ;
	private SearchView searchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = new ArrayList<View>();
		vp = (ViewPager) findViewById(R.id.vp);

		// 1获取数据
	
		

		// 2.创建适配器
		MyAdapter adapter = new MyAdapter();

		// 3.绑定数据
		vp.setAdapter(adapter);

		

		initContactView("");

	}

	private void initContactView(String whereStr) {
		manager = new ContactManager2();
		beans = manager.getContentList(getContentResolver(),whereStr);
		// 排序
		paixu();
		// 删除没有子选项的父选项
		removeNullParent();

		setAdapter();

		setLisenter();
	}

	private void initView() {
		//得到  群组、联系人、收藏的layout布局 
		View v1 = getLayoutInflater().inflate(R.layout.layout_1, null);
		View v2 = getLayoutInflater().inflate(R.layout.activity_second, null);
		View v3 = getLayoutInflater().inflate(R.layout.layout_3, null);
		list.add(v1);
		list.add(v2);
		list.add(v3);
		
		//得到上面的点击后显示的白线 (TextView)
		listLines = new ArrayList<TextView>();
		for (int i = 0; i < listLineIds.length; i++) {
			TextView tv = (TextView) findViewById(listLineIds[i]);
			listLines.add(tv);
		}
		//得到上面的栏目的容器(RadioGroup)
		rg = (RadioGroup) findViewById(R.id.rg);
		//得到第联系人页面的搜索框
		searchView = (SearchView) list.get(1).findViewById(R.id.searchView);
		
		
		//得到下面的控件()
		
		
	}


	private void setLisenter() {
		// 实现点击事件
		elv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Person person = (Person) adapter.getChild(groupPosition,
						childPosition);
				Intent intent = new Intent(MainActivity.this,
						EditActivity.class);
				intent.putExtra("person1", person);
				//showTips(person.toString());
				// startActivity(intent);
				 startActivityForResult(intent, 100);

				return false;
			}
		});

		/**点击上面的栏目*/
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int position = 0;
				switch (checkedId) {
				case R.id.rb_group:
					vp.setCurrentItem(0);
					position = 0;
					break;
				case R.id.rb_contact:
					vp.setCurrentItem(1);
					position = 1;
					break;
				case R.id.rb_love:
					vp.setCurrentItem(2);
					position = 2;
					break;
				}

				for (int i = 0; i < listLines.size(); i++) {
					if (position == i)
						listLines.get(i).setVisibility(View.VISIBLE);
					else
						listLines.get(i).setVisibility(View.INVISIBLE);
				}
			}
		});
		//使左右滑动做到点击滑动到哪个栏目下的效果
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * 当界面已经被选中之后
			 * 
			 * @param position
			 *            被选中的位置
			 */
			@Override
			public void onPageSelected(int position) {
				//根据位置获取当前应该被选中的radioButton
                RadioButton rb = (RadioButton) rg.getChildAt(position);
                //设置当前的button被选中
                rb.setChecked(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		//设置查询框输入值搜索的值发生改变
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}
			 /*
             * 文字发生变化的时候进行查询
             */
			@Override
			public boolean onQueryTextChange(String newText) {
				initContactView(" sort_key like  '%"+newText+"%'");
				return false;
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {// 更新了数据重新加载
			initContactView("");
		}
	}

	private void setAdapter() {
		adapter = new ContactAdapter(beans, MainActivity.this);
		elv = (ExpandableListView) list.get(1).findViewById(
				R.id.expandableListView1);
		elv.setAdapter(adapter);

		// 展开数据
		for (int i = 0; i < beans.size(); i++) {
			elv.expandGroup(i);
		}
	}

	/** 去掉父项中的空的 */
	private void removeNullParent() {
		for (int i = 0; i < beans.size(); i++) {
			if (beans.get(i).getChildList().size() == 0) {
				beans.remove(i);
				i--;
			}
		}
	}

	/**
	 * 为beans 添加排序功能
	 * 
	 */
	private void paixu() {
		Collections.sort(beans.get(0).getChildList(), new Comparator<Person>() {
			@Override
			public int compare(Person lhs, Person rhs) {
				// 根据姓名排序 得到姓名
				String name1 = lhs.getName();
				String name2 = rhs.getName();
				// 存放它们的首字母
				char c1, c2;
				// 拼英包
				String[] arr1 = PinyinHelper.toHanyuPinyinStringArray(name1
						.charAt(0));
				String[] arr2 = PinyinHelper.toHanyuPinyinStringArray(name2
						.charAt(0));

				if (arr1 == null) {
					c1 = name1.charAt(0);
				} else {
					c1 = arr1[0].charAt(0);
				}
				if (arr2 == null) {
					c2 = name2.charAt(0);
				} else {
					c2 = arr2[0].charAt(0);
				}
				if (c1 > c2) {
					return 1;
				} else if (c1 < c2) {
					return -1;
				}
				return 0;
			}
		});
	}
	/**Toast输出*/
	public void showTips(String text) {
		Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
	}
	/**
	 * ViewPager 的适配器,在界面中的左右滑动改变
	 * @author Barry
	 *
	 */
	class MyAdapter extends PagerAdapter {
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

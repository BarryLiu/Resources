package com.jing.work6;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 1.ListView 的 addHeaderView与addFooderView方法</br>
 * 2.删除ListView 的item
 * 
 * 
 * 嵌套两个适配器输出<br>
 * 做到nums 的值为多少就一行显示几条数据
 *
 * MyAdapter 套GVAdapter    List<Date>
 * 外ListView 内GridView    Date.getMovies<br>
 * 
 * 添加更多按钮
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	private ListView lv_listView;
	private static final int nums=2;//一行显示的个数
	private List<Data> datas =null; //需要绑定的数据
	private MyAdapter adapter = null;//自定义的适配器
	private int rids[]={			//图片资源
			R.drawable.a1,
			R.drawable.a2,
			R.drawable.a3,
			R.drawable.a4,
			R.drawable.a5,
			R.drawable.a6,
			R.drawable.a7,
			R.drawable.a8,
			R.drawable.a9,
			R.drawable.a10,
			R.drawable.a11,
			R.drawable.a12,
			R.drawable.a13,
			R.drawable.a14,
			R.drawable.a15
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv_listView = (ListView) this.findViewById(R.id.lv_listview);
		//1. 获取数据
		initData();
		
		//2. 创建适配器	3. 绑定适配器
		createBindAdapter();
		//
	}
 
	private void createBindAdapter() {
		  adapter=new MyAdapter(datas,MainActivity.this);
		//添加headerView 要在setAdapter之前运行
		addHeaderView();
		//bindAdapter 绑定Adapter
		lv_listView.setAdapter(adapter);
		//addFooterView
		addFooterView();
		
	}
	/**添加下面的button按钮*/
	private void addFooterView() {
		Button btn_addMore = createAddMoreBtn();
		lv_listView.addFooterView(btn_addMore);
	}

	/**创建 添加更多按钮
	 * @return
	 */
	private Button createAddMoreBtn() {
		Button btn_addMore=new Button(MainActivity.this);
		btn_addMore.setGravity(Gravity.CENTER);
		btn_addMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 Data  data = new Data();
			        List<Movie> movies =new ArrayList<Movie>();
			        for(int i=0;i<nums ;i++){
			            Movie m =new Movie();
			            m.setName("添加的电影"+i);
			            m.setResId(rids[i%rids.length]);
			            movies.add(m);
			        }
			        data.setMovies(movies);
			        datas.add(data);
			        //新加的电影
			        adapter.notifyDataSetChanged();
			}
		});
		btn_addMore.setText("添加更多");
		return btn_addMore;
	}

	private void addHeaderView() {
		ImageView ivheader=new ImageView(MainActivity.this);
		ivheader.setImageResource(R.drawable.a1);
		lv_listView.addHeaderView(ivheader);
	}

	private void initData() {
		  datas=new ArrayList<Data>();
		for(int i=0;i<4/nums;i++){ //4为要循环的个数
			Data data=new Data();
			List<Movie> movies =new ArrayList<Movie>();
			for (int j = 0; j < nums; j++) {
				int pos = nums*i+j;
				Movie m=new Movie(rids[pos%rids.length], "电影"+pos);
				movies.add(m);
			}
			data.setMovies(movies);
			datas.add(data);
		}
	}

}

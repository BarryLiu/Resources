package com.example.work5;

import java.util.List;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TabHost;


public class ThredActivity extends Activity {

	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thred);
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		addHeader();
		
		addMovie();
	}
	


	private void addMovie() {
		//1.׼������
		List<Movie> movies=new ArrayList<Movie>();
		for (int i = 0; i < 10; i++) {
			Movie m=new Movie("��Ӱ��"+i,"������"+i,pics[i%pics.length]);
			movies.add(m);
		}
		
		//2.����������
		MyAdapter3 adapter=new MyAdapter3(movies,ThredActivity.this);
		
		//3.�󶨿ؼ�
		GridView gvMv = (GridView) this.findViewById(R.id.gv_mv);
		gvMv.setAdapter(adapter);
	}



	private void addHeader() {
		tabHost.addTab(tabHost.newTabSpec("tawb1").setIndicator("���Ӿ�").setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("taeb2").setIndicator("��Ӱ").setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("���ս�Ŀ").setContent(R.id.tab3));
	}
	
	//ͼƬid
		private int [] pics={
				R.drawable.face1,
				R.drawable.face2,
				R.drawable.face3,
				R.drawable.face4,
				R.drawable.face5,
				R.drawable.face6,
				R.drawable.face7,
				R.drawable.face8
		};
	
		 
}

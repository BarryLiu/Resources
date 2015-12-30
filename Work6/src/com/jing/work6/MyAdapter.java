package com.jing.work6;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MyAdapter extends BaseAdapter{
	private List<Data> datas=null;
	private Context context = null;
	public MyAdapter(List<Data> datas, Context context) {
		this.datas = datas;
		this.context = context;
	}
	@Override
	public int getCount() {
		return datas.size();
	}
	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	GridView gv_movie=null;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView= inflater.inflate(R.layout.gv_layout,null);
			gv_movie = (GridView) convertView.findViewById(R.id.gv_movies);
		} 
		
		Data data = datas.get(position);
		gv_movie.setNumColumns(data.getMovies().size());
		GVAdapter adapter =new GVAdapter(data.getMovies(),context);
		gv_movie.setAdapter(adapter);

		return convertView;
	}
	
	
}

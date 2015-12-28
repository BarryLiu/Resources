package com.example.work5;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter3 extends BaseAdapter {
	private List<Movie> movies;
	private Context context;

	public MyAdapter3(List<Movie> movies, Context context) {
		this.movies = movies;
		this.context = context;

	}

	@Override
	public int getCount() {
		return movies.size();
	}

	@Override
	public Object getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder=new ViewHolder();
			
			LayoutInflater inflater = LayoutInflater.from(context);
			  convertView = inflater.inflate(R.layout.gv_mv, null);

			holder.ivPid = (ImageView) convertView.findViewById(R.id.iv_pid);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvUpto = (TextView) convertView.findViewById(R.id.tv_upto);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Movie m = movies.get(position);
		holder.ivPid.setImageResource(m.getResid());
		holder.tvName.setText(m.getName());
		holder.tvUpto.setText(m.getUpto());

		convertView.setTag(holder);
		return convertView;
	}

	class ViewHolder {
		ImageView ivPid;
		TextView tvName;
		TextView tvUpto;
	}
}
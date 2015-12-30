package com.jing.work6;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GVAdapter extends BaseAdapter {
	private List<Movie> movies;
	private Context context;

	public GVAdapter(List<Movie> movies, Context context) {
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
		if (convertView == null) {
			handler = new ViewHandler();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_layout, null);

			handler.iv_res = (ImageView) convertView.findViewById(R.id.iv_res);
			handler.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		} else {
			handler = (ViewHandler) convertView.getTag();
		}

		Movie movie = movies.get(position);
		handler.iv_res.setImageResource(movie.getResId());
		handler.tv_name.setText(movie.getName());

		convertView.setTag(handler);
		return convertView;
	}

	ViewHandler handler = null;

	class ViewHandler {
		ImageView iv_res;
		TextView tv_name;

	}

}

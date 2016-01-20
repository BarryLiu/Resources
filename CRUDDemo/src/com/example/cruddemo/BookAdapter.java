package com.example.cruddemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter{
	private List<BookBean> mList;
	private Context mContext;
	public BookAdapter(Context context,List<BookBean>  list) {
		this.mContext = context;
		this.mList = list;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			holder =new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_book, null);
			
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
			holder.tvData = (TextView) convertView.findViewById(R.id.tv_data);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		BookBean bb = mList.get(position);
		
		holder.tvName.setText(bb.getName());
		holder.tvAuthor.setText(bb.getAuthor());
		holder.tvData.setText(bb.getData());
		
		convertView.setTag(holder);
		return convertView;
	}
	private ViewHolder holder;
	class ViewHolder{
		TextView tvName;
		TextView tvAuthor;
		TextView tvData;
	}

}

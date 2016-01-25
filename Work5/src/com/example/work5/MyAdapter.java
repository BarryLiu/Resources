package com.example.work5;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 自定义适配器,简单优化
 * 1.避免重复创建View,2.避免多次调用findViewById方法
 * @author Barry
 *
 */
public class MyAdapter extends BaseAdapter {
	//数据
	private List<Student> datas;
	//上下文
	private Context context;

	public MyAdapter(List<Student> datas, Context context) {
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
	
	/**返回的数据并进行优化*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Student s = datas.get(position);
		if (convertView == null) {//第一次进来才为null，
			holder =new ViewHolder();
			LayoutInflater lif = LayoutInflater.from(context);
			convertView = lif.inflate(R.layout.lv_person, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
			holder.iv_pid = (ImageView) convertView.findViewById(R.id.iv_pid);
		}else{
			holder =(ViewHolder) convertView.getTag(); //得到上次发送的数据
		}
		
		//为这一列的数据设置属性
		holder.tv_name.setText(s.getName());
		holder.tv_phone.setText(s.getTel());
		holder.iv_pid.setImageResource(s.getResId());
		
		//隔行变色效果
		convertView.setBackgroundColor(Color.WHITE);
		if (position == 0)
			convertView.setBackgroundColor(Color.RED);
		if (position % 2 != 0)
			convertView.setBackgroundColor(Color.GRAY);
		
		convertView.setTag(holder);//数据发送出去为下一次
		return convertView;
	}

	private ViewHolder holder;

	class ViewHolder {
		TextView tv_name;
		TextView tv_phone;
		ImageView iv_pid;
	}

}

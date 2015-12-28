package com.example.work5;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter2 extends BaseAdapter {

	private List<Student> datas;
	private Context context;

	public static ArrayList<CheckBox> cbs;

	public MyAdapter2(Context context, ArrayList<Student> datas) {
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

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertview == null) {
			holder =new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertview = inflater.inflate(R.layout.lv_stu, null);
			holder.tvName = (TextView) convertview.findViewById(R.id.tv_name);
			holder.cbIsChose = (CheckBox) convertview.findViewById(R.id.cb_ischose);
			SecondActivity.addCheckBox(holder.cbIsChose);
		}else{
			holder = (ViewHolder) convertview.getTag();
		}
		Student stu = datas.get(position);
		holder.tvName.setText(stu.getName());
		holder.cbIsChose.setChecked(stu.getIsChecked());
		
		convertview.setTag(holder);
		return convertview;
	}

	class ViewHolder {
		TextView tvName;
		CheckBox cbIsChose;
	}
}

package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;
import io.barryliu.work13_contact.bean.Person;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseExpandableListAdapter {
	List<ContactBean> mList;
	Context mContext;

	public ContactAdapter(List<ContactBean> mList, Context mContext) {
		super();
		this.mList = mList;
		this.mContext = mContext;
	}

	// 组的数量
	@Override
	public int getGroupCount() {
		return mList.size();
	}

	// 一个组里面的组员的数量
	@Override
	public int getChildrenCount(int groupPosition) {
		return mList.get(groupPosition).getChildList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mList.get(groupPosition).getChildList().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	/*
	 * 视图的主视图的 isExpanded :是否展开
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.group_layout, null);
		}
		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		tv_name.setText(mList.get(groupPosition).getTitle());

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView =LayoutInflater.from(mContext).inflate(R.layout.child_layout, null);
		}
		
		Person person = mList.get(groupPosition).getChildList().get(childPosition);
		TextView tv1 = (TextView) convertView.findViewById(R.id.textView1);
		TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);
	 
		tv1.setText(person.getName());
		tv2.setText(person.getTel());
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}

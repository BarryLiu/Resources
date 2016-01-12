package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;

import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

/**
 * ContentResolver:内容处理器
 * BaseExpandableListAdapter:   是 BaseExpandableListAdapter 的适配器的父接口
 * ContactManager :联系人管理器  :  用于查询联系人的数据库
 * @author Barry
 * 
 */
public class MainActivity extends Activity {
	private ContactManager manager;
	private ExpandableListView elv;
	
	//    ContactBean 有title，List<persion>
	List<ContactBean> beans;
	ContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		manager = new ContactManager();
		beans = manager.getContentList(cr);

		adapter = new ContactAdapter(beans, MainActivity.this);
		for (ContactBean cb : beans) {
			Log.d("ContactBean", cb.toString());
		}
		initContactList();

	}

	private void initContactList() {
		elv = (ExpandableListView) findViewById(R.id.expandableListView1);

		elv.setAdapter(adapter);

	}

}

package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;
import io.barryliu.work13_contact.bean.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
		//对beans里面的数据进行排序
		paixu();

		adapter = new ContactAdapter(beans, MainActivity.this);
		initContactList();

	}

	private void paixu() {
		Collections.sort(beans.get(0).getChildList(), new Comparator<Person>() {

			@Override
			public int compare(Person lhs, Person rhs) {
				String name1 = lhs.getName();
				String name2 = rhs.getName();
				
				char c1,c2;
				String[] arr1 = PinyinHelper.toHanyuPinyinStringArray(name1.charAt(0));
				String[] arr2 = PinyinHelper.toHanyuPinyinStringArray(name2.charAt(0));
				
				if(arr1==null){
					c1=name1.charAt(0);
				}else{
					c1=arr1[0].charAt(0);
				}
				if(arr2==null){
					c2=name2.charAt(0);
				}else{
					c2=arr2[0].charAt(0);
				}
				
				if(c1>c2){
					return 1;
				}else if(c1<c2){
					return -1;
				}
				
				return 0;
			}
		});
	}

	private void initContactList() {
		elv = (ExpandableListView) findViewById(R.id.expandableListView1);
		elv.setAdapter(adapter);
	}
	
	public void toSecond(View v){
		Intent intent =new Intent(MainActivity.this,SecondActivity.class);
		
		this.startActivity(intent);
	}

}

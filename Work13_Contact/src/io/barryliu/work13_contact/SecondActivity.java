package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;
import io.barryliu.work13_contact.bean.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class SecondActivity extends Activity {
	private ContactManager2 manager ;
	private ContactAdapter adapter;
	private  List<ContactBean> beans;
	
	private ExpandableListView elv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		ContentResolver cr = getContentResolver();
		manager = new ContactManager2();
		beans = manager.getContentList(cr);
		
		//排序
		paixu();
	
		//去掉父项中的空的
		for (int i = 0; i < beans.size(); i++) {
			if(beans.get(i).getChildList().size()==0){
				beans.remove(i);
				i--;
			}
		}
		
		adapter = new ContactAdapter(beans, SecondActivity.this);
		elv = (ExpandableListView) findViewById(R.id.expandableListView1);
		elv.setAdapter(adapter);
		
		for (int i = 0; i < beans.size(); i++) {
			elv.expandGroup(i);
		}
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


}

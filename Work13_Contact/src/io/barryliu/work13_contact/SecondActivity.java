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
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
/**
 * 
 * @author Barry
 *
 */
public class SecondActivity extends Activity {
	private ContactManager2 manager ;
	private ContactAdapter adapter;
	private  List<ContactBean> beans;
	
	private ExpandableListView elv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		manager = new ContactManager2();
		beans = manager.getContentList(getContentResolver());
		
		//排序
		paixu();
		//删除没有子选项的父选项
		removeNullParent();
		
		setAdapter();
		
		setLisenter();
	}

	private void setLisenter() {
		//实现点击事件
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Person person = (Person) adapter.getChild(groupPosition, childPosition);
				Intent intent =new Intent(SecondActivity.this,EditActivity.class);
				intent.putExtra("person1", person);
//				startActivity(intent);
				startActivityForResult(intent, 100);
				
				return false;
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){//更新了数据重新加载
			beans = manager.getContentList(getContentResolver());
			//排序
			paixu();
			//删除没有子选项的父选项
			removeNullParent();
			
			setAdapter();
			
			setLisenter();
		}
	}
	private void setAdapter() {
		adapter = new ContactAdapter(beans, SecondActivity.this);
		elv = (ExpandableListView) findViewById(R.id.expandableListView1);
		elv.setAdapter(adapter);
		
		//展开数据
		for (int i = 0; i < beans.size(); i++) {
			elv.expandGroup(i);
		}
	}

	private void removeNullParent() {
		//去掉父项中的空的
		for (int i = 0; i < beans.size(); i++) {
			if(beans.get(i).getChildList().size()==0){
				beans.remove(i);
				i--;
			}
		}
	}
	
	
	/**
	 * 为beans 添加排序功能 
	 * 
	 */
	private void paixu() {
		Collections.sort(beans.get(0).getChildList(), new Comparator<Person>() {
			@Override
			public int compare(Person lhs, Person rhs) {
				//根据姓名排序 得到姓名
				String name1 = lhs.getName();
				String name2 = rhs.getName();
				//存放它们的首字母
				char c1,c2;
				//拼英包
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

	public void showTips(String text){
		Toast.makeText(SecondActivity.this, text, Toast.LENGTH_SHORT).show();
	}
}

package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;
import io.barryliu.work13_contact.bean.Person;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;


public class ContactManager2 {
	
	private static String [] bigLetters=
		{"#","A","B","C","D","E","F","G","H","I","J","K","L","M","N",
		"O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	
	public static List<ContactBean> getContentList(ContentResolver cr) {
		// 获取访问联系人的数据库

		List<ContactBean> list = new ArrayList<ContactBean>();
		
		for (int i = 0; i < bigLetters.length; i++) {
			ContactBean cb1 = new ContactBean();
			cb1.setTitle(bigLetters[i]);
			list.add(cb1);
		}

		 Uri raw_uri = ContactsContract.Contacts.CONTENT_URI ;
		//Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
		// 获取联系人id
		// args=下面也可以用 : Phone.CONTENT_URI
		Cursor c_id = cr.query(raw_uri, new String[] { "_id" }, null, null,
				null);
		// 遍历每一个联系人
		while (c_id.moveToNext()) {
			int id = c_id.getInt(0);
			String name = "未知", tel = "未知 ";
			// 根据id 得到相应的字段属性
			Uri data_uri = Uri
					.parse("content://com.android.contacts/raw_contacts/" + id
							+ "/data");
			// 根据id 获取联系人的相关属性 param2加 'deleted =0'
			// 就不会出现已经删除的查询不出来的问题,如果是自己敲的字符串就要写
			Cursor c_data = cr.query(data_uri, new String[] { "data1",
					"mimetype" }, null, null, null);
			// 遍历每一个的所有数据
			while (c_data.moveToNext()) {
				// 获取数据
				String data = c_data.getString(0);
				// 获取类型
				String mimeType = c_data.getString(1);

				if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
					tel = data;
				} else if ("vnd.android.cursor.item/name"
						.equalsIgnoreCase(mimeType)) {
					name = data;
				}
			}
 			c_data.close();//关闭资源
			Log.d("contact", " id " + id + " 姓名 " + name + " 电话" + tel);
			Person person = new Person(name, tel);
			 person.setId(id);
			String[] arr = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0));
			char tit;
			if(arr==null){
				tit = name.toUpperCase().toUpperCase().charAt(0);
				if(tit<'A'||tit>'z'){
					list.get(0).getChildList().add(person);
				}else{
					list.get(tit-'A'+1).getChildList().add(person);
				}
			}else{
				 tit = arr[0].toUpperCase().charAt(0);
				 list.get(tit-'A'+1).getChildList().add(person);
			}
		}
//		c_id.close();//关闭资源
		return list;
	}
	
	public static void updateConact(Person p ,ContentResolver cr){
//		Uri uri_data = ContactsContract.Contacts.Data.
		//AUTHORITY 主机
		Uri uri_data =Uri.parse( "content://"+ContactsContract.AUTHORITY+"/data");
		
		
		//更新电话
		ContentValues values =new ContentValues();
		values.put("data1", p.getName());
		cr.update(uri_data, values," raw_contact_id  = ? and mimetype_id = ? ",new String[]{p.getId()+"","7"});
		
		//更新名字
		ContentValues values1 = new ContentValues();
	    values1.put("data1", p.getTel());
	    //update
	    cr.update(uri_data,values1,"raw_contact_id = ? and mimetype_id = ?",new String[]{
	                p.getId()+"",
	                5+""});
	}
}

package io.barryliu.work13_contact;

import io.barryliu.work13_contact.bean.ContactBean;
import io.barryliu.work13_contact.bean.Person;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactManager {
	public static List<ContactBean> getContentList(ContentResolver cr) {
		// 获取访问联系人的数据库

		List<ContactBean> list = new ArrayList<ContactBean>();
		ContactBean cb1 = new ContactBean();
		cb1.setTitle("我的好友");
		ContactBean cb2 = new ContactBean();
		cb2.setTitle("我的家人");

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
//			c_data.close();//关闭资源
			Log.d("contact", " id " + id + " 姓名 " + name + " 电话" + tel);
			Person person = new Person(name, tel);
			if (id % 2 == 0)
				cb1.getChildList().add(person);
			else
				cb2.getChildList().add(person);
		}
//		c_id.close();//关闭资源
		list.add(cb1);
		list.add(cb2);
		return list;
	}
}

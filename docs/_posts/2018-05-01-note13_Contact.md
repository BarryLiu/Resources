---
layout: post
title:  "note13_Contack"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
#联系人关系器
- M:数据的来源,从手机中获取联系人列表
- V:拼音排序,滑动切换界面
- C:查询算法

##获取手机联系人数据
- 手机上的联系人是存放在某个数据库中
- 手机上的联系人数据,是可以被共享的
- ContentProvider:内容提供者,是为不同应用共享相同的数据
- 从com.android.providers.contacts中可以看到系统联系人的数据库
- raw_contacts表:存放联系人信息的主表,主要是联系人的id
- data:存放联系人具体的数据的表
- mimetype:存放数据的类型
###如何对provider中的数据进行操作
- 获取contentResovler:是在Activity中获取

     ContentResolver cr = getContentResolver();

- 添加联系人的权限

     	<uses-permission android:name="android.permission.READ_CONTACTS" />
- 通过cr.query对相关的数据库进行查询
- uri:统一标识符号,用于获取唯一的资源;由格式、主机、路径组成

         //获取联系人的数据
        Uri uri_contact = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor c = cr.query(uri_contact,//联系人的uri
                new String[]{"_id"},//获取数据库中的字段
                null,//条件
                null,//条件参数
                null);//orderby 排序

- 查询出联系人的详细数据

         //遍历游标获取所有的联系人的数据
      while (c.moveToNext()){
          int id = c.getInt(0);
          //更具id去data表中进行查询
          Uri uri_data = Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data");
          Cursor c_data =  cr.query(uri_data, new String[]{"data1", "mimetype"}, null, null, null);
          String tel = "未知";
          String name = "未知";
          //遍历获取联系人的相关数据
          while (c_data.moveToNext()){
              String data = c_data.getString(0);
              String mimeType = c_data.getString(1);
              //根据mimeType的类型判数据
              //电话号码
              if (mimeType.equals("vnd.android.cursor.item/phone_v2")){
                  tel = data;
              }else if(mimeType.equals("vnd.android.cursor.item/name")){
                  name = data;
              }
          }
          //关闭游标
          c_data.close();
          Log.d(TAG,"id:"+id+";姓名："+name+";电话:"+tel);
      }

- 关闭游标
    ​      
        c.close();

data:数据
date:日期
Contact:联系人
Content:内容
Resolver:解决者
provider:提供者
projection:列名字
selection:where条件语句
selectionArgs:条件语句的参数


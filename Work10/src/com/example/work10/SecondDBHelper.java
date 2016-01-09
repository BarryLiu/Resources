package com.example.work10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SecondDBHelper extends SQLiteOpenHelper{
	private Context mContext;
	public SecondDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	/**
     * 只有数据库被创建的时候才运行
     * @param db
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//
		SecondDao.createTable(db);
	}
	 /**
     * 数据库升级的时候调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 //在版本2要添加一个teacher表
        if(newVersion <= 2) {
            db.execSQL("create table teacher(_id integer primary key autoincrement,name varchar(20))");

            Toast.makeText(mContext, "表升级成功", Toast.LENGTH_SHORT).show();
       }
	}

}

package com.example.work11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private Context mContext;

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			//开始事务
			db.beginTransaction();

			BankAccountDao.createTable(db);
			BankHistoryDao.createTable(db);

			db.setTransactionSuccessful();//事务正常结束
			MainActivity.showTips(mContext, "创建表成功");
		} catch (Exception e) {
			e.printStackTrace();
			MainActivity.showTips(mContext, "创建表失败");
		}
		db.endTransaction(); //结束事务
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

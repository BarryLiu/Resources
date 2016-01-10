package com.example.work11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BankAccountDao {
	private final static String TABLE_NAME = "bankaccount";
	private final static String COL_ID = "_id";
	private final static String COL_NAME = "name";
	private final static String COL_MONEY = "money";	
	
	public static boolean  createTable(SQLiteDatabase db){
		try{
			db.execSQL("create table "+TABLE_NAME+" ("+COL_ID+" integer primary key autoincrement , "+COL_NAME+" varchar(20) ,"+COL_MONEY+" double )");
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public static long insert(SQLiteDatabase db, String name, Double money) {
		ContentValues values =new ContentValues();
		values.put(COL_NAME, name);
		values.put(COL_MONEY, money);
		return db.insert(TABLE_NAME, null, values);
	}

	public static Object[] queryById(SQLiteDatabase db , String id) {
		Cursor c = db.query(TABLE_NAME, new String[]{COL_ID,COL_NAME,COL_MONEY}, COL_ID+" = ? ", new String[]{id}, null, null, null);
		Object [] user = new Object[c.getColumnCount()];
		if(c.moveToNext()){
			user[0]=c.getInt(0);
			user[1]=c.getString(1);
			user[2]=c.getDouble(2);
		}
		return user;
	}

	public static int update(SQLiteDatabase db, String id, String name,
			Double money) {
		ContentValues values =new ContentValues();
		values.put(COL_NAME, name);
		values.put(COL_MONEY, money);
		return db.update(TABLE_NAME, values, COL_ID, new String[]{id});
	}

	public static Cursor queryAll(SQLiteDatabase db) {
		return db.query(TABLE_NAME, null, null, null, null, null, null);
	}
	
}

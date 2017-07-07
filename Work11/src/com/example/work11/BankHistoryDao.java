package com.example.work11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BankHistoryDao {
	private static final String TABLE_NAME = "bankhistory";
	private static final String COL_ID = "_id";
	private static final String COL_NAME = "username";
	private static final String COL_DATA = "data";
	private static final String COL_MONEY = "money";
	private static final String COL_USERID = "userid";

	public static boolean createTable(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME + "( " + COL_ID
					+ " integer primary key autoincrement ," 
					+ COL_NAME + " varchar(20) , "
					+ COL_DATA + " varchar(20) , "
					+ COL_MONEY + " double ,"
					+COL_USERID+ " integer )");
			return true;
	}

	public static long insert(SQLiteDatabase db, String userId, Double money,
			String userName, String dataStr) {
		ContentValues values =new ContentValues();
		values.put(COL_NAME, userName);
		values.put(COL_DATA, dataStr);
		values.put(COL_MONEY, money);
		values.put(COL_USERID, userId);
		
		return db.insert(TABLE_NAME, null, values);
	}

	public static Cursor queryAll(SQLiteDatabase db) { 
		return db.rawQuery(" select * from "+TABLE_NAME, null);
	}

 	
	
}

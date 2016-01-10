package com.example.work11;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class BankHistoryDao {
	private static final String TABLE_NAME = "bankhistory";
	private static final String COL_ID = "_id";
	private static final String COL_NAME = "username";
	private static final String COL_DATA = "data";
	private static final String COL_MONEY = "money";
	private static final String COL_USERID = "userid";

	public static boolean createTable(SQLiteDatabase db) {
		try{
			db.execSQL("create table " + TABLE_NAME + " values( " + COL_ID
					+ " integer autoincrement ," 
					+ COL_NAME + " varchar(20) , "
					+ COL_DATA + " varchar(20) , "
					+ COL_MONEY + " double ,"
					+COL_USERID+ " integer )");
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
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
	
	
}

package com.example.cruddemo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BookDao {
	public static final String TABLE = "books";

	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_AUTHOR = "author";
	public static final String COL_DATA = "data";

	public static void create(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE
				+ " ("+COL_ID+" integer primary key autoincrement ," + COL_NAME
				+ " varchar(20) ," + COL_AUTHOR + " varchar(20) ," + COL_DATA
				+ " varchar(20) " + ")");
	}

	public static long insert(SQLiteDatabase db, BookBean bb) {

		ContentValues values = new ContentValues();
		values.put(COL_AUTHOR, bb.getAuthor());
		values.put(COL_DATA, bb.getData());
		values.put(COL_NAME, bb.getName());

		db.insert(TABLE, null, values);

		return 1;
	}

	public static int delete(SQLiteDatabase db, String _id) {
		return db.delete(TABLE, COL_ID + " = " + _id, null);
	}

	public static int update(SQLiteDatabase db, BookBean bb) {
		ContentValues values = new ContentValues();
		values.put(COL_NAME, bb.getName());
		values.put(COL_AUTHOR, bb.getAuthor());
		values.put(COL_DATA, bb.getData());

		return db.update(TABLE, values, COL_ID + " = " + bb.getId(), null);
	}

	public static List<BookBean> query(SQLiteDatabase db, String whereSql) {
		Cursor c = db.query(TABLE, null, whereSql, null, null, null, null);

		List<BookBean> list = new ArrayList<BookBean>();
		while (c.moveToNext()) {
			BookBean bb = new BookBean();

			bb.setId(c.getInt(c.getColumnIndex(COL_ID)));
			bb.setName(c.getString(c.getColumnIndex(COL_NAME)));
			bb.setAuthor(c.getString(c.getColumnIndex(COL_AUTHOR)));
			bb.setData(c.getString(c.getColumnIndex(COL_DATA)));

			list.add(bb);
		}

		return list;
	}

}

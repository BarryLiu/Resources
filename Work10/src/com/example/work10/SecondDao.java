package com.example.work10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * Dao层   静态的增删改查方法
 * @author Barry
 *
 */
public class SecondDao {
	// 表名和字段
	private static final String TABLENAME = "table1";
	private static final String COL_ID = "_id";
	private static final String COL_COLNUM1 = "colnum1";

	public static void createTable(SQLiteDatabase db) {
		// db.execSQL("create table table1(_id integer primary key autoincrement,colnum1 varchar(20))");
		db.execSQL("create table " + TABLENAME + "(" + COL_ID
				+ " integer primary key autoincrement," + COL_COLNUM1
				+ " varchar(20))");
	}

	public static long insert(SQLiteDatabase db, String text) {
		ContentValues values = new ContentValues();
		values.put(COL_COLNUM1, text);
		return db.insert(TABLENAME, null, values);
	}

	public static int delete(SQLiteDatabase db, String id) {

		return db.delete(TABLENAME, COL_ID + " = ? ", new String[] { id });
	}

	public static int update(SQLiteDatabase db, String id) {
		ContentValues values = new ContentValues();
		values.put(COL_COLNUM1, "colnum新改的值");

		return db.update(TABLENAME, values, COL_ID + " = ? ",
				new String[] { id });
	}

	public static List select(SQLiteDatabase db, String text_id) {
		ContentValues values = new ContentValues();
		values.put(COL_COLNUM1, "colnum新改的值");

		//db.query("表名", new String[]{"字段1，字段2"}, "条件1=? and 条件2=?", new String[]{"条件1的值，条件2的值"},null,null,null)
		
		Cursor c = null;
		if(text_id.isEmpty()){
			c = db.query(TABLENAME, new String[] { COL_ID, COL_COLNUM1 },
				null, null, null, null, null);
		}else{
			c = db.query(TABLENAME, new String[] { COL_ID, COL_COLNUM1 },
					COL_ID+" = ? ",new String[]{text_id} , null, null, null);
		}
		List<String[]> list = new ArrayList<String[]>();
		int columnCount = c.getColumnCount();
		String[] rowDatas = null;
		while (c.moveToNext()) {
			rowDatas = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				rowDatas[i] = c.getString(i);
			}
			Log.d("secondActivity", Arrays.toString(rowDatas));
			list.add(rowDatas);
		}
		return list;
	}
	/***
	 *db.query方法: 
	  query(table, columns, selection, selectionArgs, groupBy, having, orderBy,
	 * limit)方法各参数的含义： table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。
	 * columns：要查询出来的列名。相当于select语句select关键字后面的部分。
	 * selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符“?”
	 * selectionArgs：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。
	 * groupBy：相当于select语句group by关键字后面的部分 having：相当于select语句having关键字后面的部分
	 * orderBy：相当于select语句order by关键字后面的部分，如：personid desc, age asc;
	 * limit：指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分。
	 */
}

package com.example.work11;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DictionaryDao {
	private static final String TABLENAME = "t_words";

	private static final String COL_ENGLISH = "english";

	private static final String NO_WORD_CHINESE = "没有找到这个单词";

	public static  Cursor queryEnglishLike(SQLiteDatabase db, String word) {
		Cursor cursor = db.rawQuery(
				"select english as _id from t_words where english like ?",
				new String[] { word + "%" });
		return cursor;
	}

	public static  String queryChinese(SQLiteDatabase db, String word) {
		Cursor cursor = db.rawQuery(
				"select * from t_words where english = ?",
				new String[] { word });
		if (cursor.moveToNext())
			return cursor.getString(1);
		else
			return NO_WORD_CHINESE;
	}

}

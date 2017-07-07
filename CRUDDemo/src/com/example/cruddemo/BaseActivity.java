package com.example.cruddemo;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class BaseActivity extends Activity {
	public SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DBHelper helper = new DBHelper(BaseActivity.this, "curd3.db", null, 1);
		db = helper.getWritableDatabase();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db!=null)
			db.close();
	}

}

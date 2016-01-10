package com.example.work11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends Activity{
	private SQLiteDatabase db;
	
	private ListView lv_users ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_users);
		
		/*DBHelper helper =new DBHelper(SecondActivity.this, "bank2.db", null, 1);
		db = helper.getWritableDatabase();
		
		
		initView();
		
		setAdapter();*/
	}

	private void setAdapter() {
		
	
		Cursor cursor = BankAccountDao.queryAll(db);
		CursorAdapter adapter =new CursorAdapter(SecondActivity.this,cursor) {
			
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				View vUser = LayoutInflater.from(SecondActivity.this).inflate(R.layout.lv_user, null);
				return vUser;
			}
			
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				TextView tv_userid = (TextView) view.findViewById(R.id.tv_userid);
				TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
				TextView tv_money = (TextView) view.findViewById(R.id.tv_money);
				
				tv_userid.setText(cursor.getInt(0)+"");
				tv_name.setText(cursor.getString(1));
				tv_money.setText(cursor.getString(2));
			}
		};
		
		
		lv_users.setAdapter(adapter);
		
	}


	private void initView() {
		  lv_users = (ListView) findViewById(R.id.lv_users);		
	}
}

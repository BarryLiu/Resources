package com.example.work10;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 用 openOrCreateDatabase 最基本的增删改查 
 * 
 * @author Barry
 *
 */
public class MainActivity extends Activity {
	//必要控件
	private SQLiteDatabase db;
	private Button btn_createTable;
	private Button btn_add;
	private Button btn_modify;
	private Button btn_query;
	private Button btn_remove;
	private Button btn_toSecond;
	private EditText et_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = openOrCreateDatabase("test1.db", 1, null);
		initView();
		setLisenter();
		
		
	}

	private void setLisenter() {
		setbtnCreateTableLisenter();

		setbtnAddLisenter();

		setbtnQueryLisenter();

		setbtnModifyLisenter();

		setbtn_removeLisenter();
		
		btn_toSecond.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent(MainActivity.this,SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setbtn_removeLisenter() {
		btn_remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text_id = et_text.getText().toString();
				if (text_id.isEmpty()) {
					et_text.setError("要删除的id");
					return;
				}
				db.execSQL("delete from table1 where _id = ? ",
						new Object[] { text_id });
				showTips("删除成功");
			}
		});
	}

	private void setbtnModifyLisenter() {
		btn_modify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text_id = et_text.getText().toString();
				if (text_id.isEmpty()) {
					et_text.setError("要修改的数据的编号");
					return;
				}
				db.execSQL("update table1 set colnum1 = ? where _id = ?",
						new Object[] { "新的colnum1值", text_id });
				showTips("修改成功");
			}
		});
	}

	private void setbtnQueryLisenter() {
		btn_query.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = et_text.getText().toString();
				String[] args = null;
				String sql = " select * from table1 ";
				if (!text.isEmpty()) {
					sql = " select * from table1 where colnum1 like ? ";
					args = new String[] { text + "%" };
				}

				Cursor c = db.rawQuery(sql, args);
				while (c.moveToNext()) {
					int id = c.getInt(0);
					String colnum1 = c.getString(c.getColumnIndex("colnum1"));

					Log.d("MainActivity", "id=" + id + " ;colnum1=" + colnum1);
				}

				showTips("查询成功");
			}
		});
	}

	private void setbtnAddLisenter() {
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = et_text.getText().toString();
				if (text.isEmpty()) {
					et_text.setError("写colnum1列要插入的内容");
					return;
				}
				db.execSQL(" insert into table1 values(null , ? ) ",
						new Object[] { text });
				showTips("插入成功");
			}
		});
	}

	private void setbtnCreateTableLisenter() {
		btn_createTable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db.execSQL(" create table table1 (_id integer primary key autoincrement , colnum1 varchar(20) ) ");
				showTips("创建表成功");
			}
		});
	}

	protected void showTips(String text) {
		Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
		;
	}

	private void initView() {
		et_text = (EditText) findViewById(R.id.et_text);
		btn_createTable = (Button) findViewById(R.id.btn_createTable);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_modify = (Button) findViewById(R.id.btn_modify);
		btn_query = (Button) findViewById(R.id.btn_query);
		btn_remove = (Button) findViewById(R.id.btn_remove);
		btn_toSecond = (Button) findViewById(R.id.btn_toSecond);
	}

}

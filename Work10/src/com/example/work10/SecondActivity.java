package com.example.work10;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 用继承的	SQLiteOpenHelper 创建数据库
 * ContentValues 携带数据增删改查
 * @author Barry
 *
 */
public class SecondActivity extends Activity {

	// 必要控件
	private SQLiteDatabase db;
	private Button btn_add;
	private Button btn_modify;
	private Button btn_query;
	private Button btn_remove;
	private EditText et_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		SecondDBHelper dbHelper = new SecondDBHelper(SecondActivity.this,
				"test2.db", null, 1);
		db = dbHelper.getWritableDatabase();

		initView();
		setLisenter();
	}
	private void setLisenter() {

		setbtnAddLisenter();

		setbtnQueryLisenter();

		setbtnModifyLisenter();

		setbtn_removeLisenter();
		
		 
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
				SecondDao.delete(db, text_id);
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
				SecondDao.update(db, text_id);
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

				SecondDao.select(db, text);
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
				long id =SecondDao.insert(db,text);
				showTips("插入成功");
			}
		});
	}
	private void initView() {
		et_text = (EditText) findViewById(R.id.et_text);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_modify = (Button) findViewById(R.id.btn_modify);
		btn_query = (Button) findViewById(R.id.btn_query);
		btn_remove = (Button) findViewById(R.id.btn_remove);
	}

	public void showTips(String text){
		Toast.makeText(SecondActivity.this, text, Toast.LENGTH_SHORT).show();
	}
}

package com.example.cruddemo;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	
	private Spinner spinner;
	private EditText etInput;
	private Button btnQuery;
	private Button btnAdd;
	private ListView listView;

	
	List<BookBean> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		

		initView();

		setAdapter();

		setLisenter();
	}

	private void setLisenter() {
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final View view = getLayoutInflater().inflate(R.layout.book,
						null);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setView(view);

				builder.setNegativeButton("取消", null);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@SuppressLint("NewApi")
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								EditText etName = (EditText) view
										.findViewById(R.id.et_name);
								EditText etAuthor = (EditText) view
										.findViewById(R.id.et_author);
								EditText etData = (EditText) view
										.findViewById(R.id.et_data);

								BookBean bb = new BookBean(etName.getText()
										.toString(), etAuthor.getText()
										.toString(), etData.getText()
										.toString());

								if (etName.getText().toString().isEmpty()) {
									Toast.makeText(MainActivity.this, "插入失败",
											Toast.LENGTH_SHORT).show();
									return;
								}
								// 插入
								BookDao.insert(db, bb);
								
								query();
							}
						});
				builder.show();
			}
		});

		btnQuery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				query();
			}

		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);

				builder.setItems(new String[] { "编辑", "删除" },
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								BookBean bb = list.get(position);
								switch (which) {
								case 0:// 编辑
									Intent intent =new Intent(MainActivity.this,EditActivity.class);
									intent.putExtra("bb", bb);
									startActivityForResult(intent, 100);
									query();
									break;
								case 1:// 删除
									BookDao.delete(db, bb.getId()+"");
									query();
									break;
								}
							}
						});

				builder.show();
			}
		});
	}
	private void query() {
		String input = etInput.getText().toString();
		String whereSql = "";
		
		switch (spinner.getSelectedItemPosition()) {
		case 0: // 姓名
			whereSql = BookDao.COL_NAME;
			break;
		case 1: // 作者
			whereSql = BookDao.COL_AUTHOR;
			break;
		case 2:// 时间
			whereSql = BookDao.COL_DATA;
			break;
		}
		
		whereSql = whereSql + " like '%" + input + "%' ";
		list = BookDao.query(db, whereSql);
		BookAdapter bookAdapter = new BookAdapter(MainActivity.this,
				list);
		listView.setAdapter(bookAdapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			  list = BookDao.query(db, null);
				BookAdapter bookAdapter = new BookAdapter(MainActivity.this,
						list);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void setAdapter() {

		ArrayAdapter spinnerAdapter = new ArrayAdapter(MainActivity.this,
				android.R.layout.simple_spinner_item, new String[] { "姓名",
						"作者", "时间" });
		spinner.setAdapter(spinnerAdapter);

	}

	private void initView() {
		spinner = (Spinner) findViewById(R.id.spinner);
		etInput = (EditText) findViewById(R.id.et_input);
		btnQuery = (Button) findViewById(R.id.btn_query);
		btnAdd = (Button) findViewById(R.id.btn_add);
		listView = (ListView) findViewById(R.id.listView1);

	}

}

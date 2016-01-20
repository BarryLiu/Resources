package com.example.cruddemo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends BaseActivity {

	private Button btnOk;
	private Button btnCanel;

	EditText etName;
	EditText etAuthor;
	EditText etData;

	BookBean bb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		initView();

		Bundle bundle = getIntent().getExtras();
		bb= (BookBean) bundle.get("bb");
	
		etName.setText(bb.getName());
		etAuthor.setText(bb.getAuthor());
		etData.setText(bb.getData());
		
		setLisenter();
	}

	private void setLisenter() {
		btnCanel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btnOk.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

				bb.setName(etName.getText().toString());
				bb.setAuthor(etAuthor
						.getText().toString());
				bb.setData( etData.getText().toString());
				if (etName.getText().toString().isEmpty()) {
					Toast.makeText(EditActivity.this, "插入失败",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 修改
				BookDao.update(db, bb);
				finish();
			}
		});
	}

	private void initView() {
		btnOk = (Button) findViewById(R.id.btn_ok);
		btnCanel = (Button) findViewById(R.id.btn_canel);

		etName = (EditText) findViewById(R.id.et_name);
		etAuthor = (EditText) findViewById(R.id.et_author);
		etData = (EditText) findViewById(R.id.et_data);
	}

}

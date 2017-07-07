package com.example.work15_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {

	private EditText et_name;
	private EditText et_tel;
	private Button btn_ok;
	private Button btn_canel;
	

	private Person person ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
	
		initView();
		setLisenter();
		
		Intent intent = getIntent();
		  person = intent.getParcelableExtra("person1");
		et_name.setText(person.getName());
		et_tel.setText(person.getTel());
		
	}

	private void setLisenter() {
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				confirm();
			}
		});
		btn_canel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK,null);
				finish();
			}
		});
	}
	public void confirm(){
		person.setName(et_name.getText().toString());
		person.setTel(et_tel.getText().toString());
		 ContactManager2.updateConact(person, getContentResolver());
	        Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
	        //关闭当前Activity
	        setResult(RESULT_OK,null);
	        finish();
	}
	private void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_tel = (EditText) findViewById(R.id.et_tel);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_canel = (Button) findViewById(R.id.btn_canel);
	}

 

}

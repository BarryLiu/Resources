package com.example.work9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends Activity {

	private TextView tv_two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		tv_two = (TextView) findViewById(R.id.tv_two);

		// receiveOne();

		// receiveTwo();

		// receiveThree();

		// receiveFour();

		Toast.makeText(TwoActivity.this, "来了额", Toast.LENGTH_SHORT).show();
		et_two= (EditText) findViewById(R.id.et_two);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String text = bundle.getString("text");
		et_two.setText(text);

	}
	EditText et_two =null;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Toast.makeText(this, "返回了", Toast.LENGTH_SHORT).show();
			// 将文字传递回去
			Intent intent = new Intent();
			 

			intent.putExtra("text2", et_two.getText().toString());
			// 将数据返回回去// 返回的id号
			setResult(110,intent);
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void receiveFour() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		Teacher teacher = (Teacher) bundle.get("teacher");
		tv_two.setText(teacher.toString());
	}

	private void receiveThree() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		Student stu1 = (Student) bundle.get("stu1");
		tv_two.setText(stu1.toString());
	}

	private void receiveTwo() {
		Intent intent = getIntent();
		// 两种接收的方式
		// Bundle bundle = intent.getExtras();
		Bundle bundle = intent.getBundleExtra("bundle1");
		String name = bundle.getString("name");
		int age = bundle.getInt("age");
		tv_two.setText("name=" + name + "; age=" + age);
	}

	/** 接收第一种方式的传值 */
	private void receiveOne() {
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int age = intent.getIntExtra("age", 0);

		tv_two.setText("name:" + name + "  age:" + age);
	}

}

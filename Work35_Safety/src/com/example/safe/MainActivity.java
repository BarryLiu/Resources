package com.example.safe;

import com.example.work35_md5.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText tvInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	 
	 public void click1(View v){
		 Intent intent =new Intent(this,MD5Activity.class);
		 startActivity(intent);
	 }
	 public void click2(View v){
		 Intent intent =new Intent(this,AesActivity.class);
		 startActivity(intent);
	 }
	 public void click3(View v){
		 Intent intent =new Intent(this,RsaActivity.class);
		 startActivity(intent);
	 }
	 public void click4(View v){
		 Intent intent =new Intent(this,HttpsActivity.class);
		 startActivity(intent);
	 }
	 
}

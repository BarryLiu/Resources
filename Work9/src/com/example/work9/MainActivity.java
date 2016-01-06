package com.example.work9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v){
    	switch (v.getId()) {
		case R.id.btn_one:
			clickOne();
			break;
		case R.id.btn_two:
			
			break;
		case R.id.btn_three:
			
			break;
		case R.id.btn_four:
			
			break;
		}
    	
    }

	private void clickOne() {
		Intent intent =new Intent(MainActivity.this, TwoActivity.class);
		intent.putExtra("name", "jack");
		intent.putExtra("age", 22);
		
		this.startActivity(intent);
	}
    
    
}

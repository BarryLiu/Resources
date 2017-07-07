package com.example.work9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
			clickTwo();
			break;
		case R.id.btn_three:
			clickThree();
			break;
		case R.id.btn_four:
			clickFour();
			break;
		case R.id.btn_result:
			clickForResult();
			break;
		}
    	
    }
    private EditText et_text =null;
    /**文本框数据带到TwoActivity显示改变后带回来*/
    private void clickForResult() {
    	et_text= (EditText) findViewById(R.id.et_text);
    	Intent intent =new Intent(MainActivity.this,TwoActivity.class);
    	String text =et_text.getText().toString();
    	intent.putExtra("text", text);
    	startActivityForResult(intent, 101);
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Bundle bundle = data.getExtras();
    	String text2= (String) bundle.get("text2");
    	et_text.setText(text2);
    	super.onActivityResult(requestCode, resultCode, data);
    } 
	/**使用Parcelable  传值*/
    private void clickFour() {
    	Intent intent = new Intent(MainActivity.this,TwoActivity.class);
    	
    	Teacher teacher =new Teacher("tom", "man", 45);
    	intent.putExtra("teacher", teacher);
    	startActivity(intent);
    }
    

	/**直接传递对象(需要别序列化) */
    private void clickThree() {
		Intent intent =new Intent(MainActivity.this,TwoActivity.class);
		
		Student stu1=new Student("jack", "man", 22);
		intent.putExtra("stu1", stu1);
		startActivity(intent);
	}

	/** 用bundle传值    */
	private void clickTwo() {
		Intent intent =new Intent(MainActivity.this,TwoActivity.class);
		Bundle bundle =new Bundle();
		bundle.putString("name", "jack");
		bundle.putInt("age", 28);
		
		//两种方式都可以
		//intent.putExtras(bundle);
		intent.putExtra("bundle1", bundle);
		
		startActivity(intent);
	}
	/**普通方式传值*/
	private void clickOne() {
		Intent intent =new Intent(MainActivity.this, TwoActivity.class);
		intent.putExtra("name", "jack");
		intent.putExtra("age", 22);
		
		this.startActivity(intent);
	}
    
    
}

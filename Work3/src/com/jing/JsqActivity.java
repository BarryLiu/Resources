package com.jing;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JsqActivity extends Activity {
	private static final Object[] Double = null;
	private EditText et_view =null;
	private ArrayList<Double> arrs=new ArrayList<Double>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsq);
		  et_view = (EditText) this.findViewById(R.id.et_view);
	}
	
	//点击=
	public void jisuan(View v){
		Double res=0.0;
		
		if(arrs.size()/2!=0&&arrs.size()>=2) //解决重复加减问题
			arrs.remove(arrs.size()-1);
		
		Double[] ddd = (java.lang.Double[]) arrs.toArray(Double);
		Toast.makeText(JsqActivity.this,  Arrays.toString(ddd), Toast.LENGTH_SHORT);
		
		for (int i = 0; i < arrs.size(); i+=2) {
			switch (arrs.get(i+1).intValue()){
			case 1:
				res+=arrs.get(i+1);
				break;
			case 2:
				res-=arrs.get(i+1);
				break;
			case 3:
				res*=arrs.get(i+1);
				break;
			case 4:
				res/=arrs.get(i+1);
				break;
			}
		}
		et_view.setText(""+res);
	}
	
	//点击1-9 
	public void click(View v){
		Button btn=(Button) v;
		et_view.setText(et_view.getText().toString()+btn.getText().toString());;
	}
	//+-*/
	public void method(View v){
		String numStr = et_view.getText().toString();
		if(numStr!=null&&!"".equals(numStr)){
			arrs.add(new Double(numStr));
		}

		if(arrs.size()/2!=0&&arrs.size()>=2) //解决重复加减问题
			arrs.remove(arrs.size()-1);
		
		switch (v.getId()) {
		case R.id.btn_add:
			arrs.add(1.0);
			break;
		case R.id.btn_remove:
			arrs.add(2.0);
			break;
		case R.id.btn_mult:
			arrs.add(3.0);
			break;
		case R.id.btn_divide:
			arrs.add(4.0);
			break;
		}
		et_view.setText("");
	}
	public void clear(View v){
		et_view.setText("");
		arrs.clear();
	}
	

	 
}

package com.example.work19_custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.view.AssortView;
import com.example.view.AssortView.OnTouchAssortListener;

public class FourActivity extends Activity {

	private AssortView assortView;
	private PopupWindow popupWindow;
	private TextView content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		
		View view = LayoutInflater.from(FourActivity.this).inflate(R.layout.alert_dialog_menu_layout, null);
		  content = (TextView) view.findViewById(R.id.content);
		popupWindow = new PopupWindow(view, 100, 100);
		assortView = (AssortView) findViewById(R.id.assortView1);
		assortView.setOnTouchAssortListener(new OnTouchAssortListener() {
			
			@Override
			public void onTouchAssortUP() {
				popupWindow.dismiss();
			}
			
			@Override
			public void onTouchAssortListener(String s) {
				content.setText(s);
				
				popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
			}
		}); 
		
	}


}

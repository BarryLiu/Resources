package com.example.work19_custom;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.view.ChartView;

public class ThreeActivity extends Activity {

	private ChartView chartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		
		chartView = (ChartView) findViewById(R.id.chartView1);
		chartView.setMax(7);
	}

	Random ran =new Random(1000);
	 
	public void getJson(View v){
		int i = ran.nextInt(100)*10;
		
		chartView.update(i);
	}
}

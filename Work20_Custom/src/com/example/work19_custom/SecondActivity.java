package com.example.work19_custom;

import com.example.view.ChartView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

    private ChartView chartView1;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        chartView1 = (ChartView) findViewById(R.id.chartView1);
        
        
    }

    
    public void getJson(View v){
    	
    	
    }
}

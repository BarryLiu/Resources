package com.example.json;

import java.io.IOException;
import java.io.InputStream;

import com.example.xmljson.R;
import com.example.xmljson.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
	
		try {
			InputStream is = getAssets().open("news.json");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 

}

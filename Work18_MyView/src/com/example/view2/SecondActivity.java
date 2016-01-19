package com.example.view2;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		VoiceView voiceView =new VoiceView(SecondActivity.this);
		
		setContentView(voiceView);
		
	}
 

}

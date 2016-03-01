package com.example.upload;

import com.example.du.R;
import com.example.du.R.layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UpLoadActivity extends Activity {
	ProgressBar pb;
	TextView tvProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_up_load);

		pb = (ProgressBar) findViewById(R.id.pb);
		tvProgress = (TextView) findViewById(R.id.tv_progress);
	}

	public void upload(View v) {

	}

	public void pause(View v) {

	}
	class UpLoadTask extends AsyncTask{
		@Override
		protected Object doInBackground(Object... params) {

			return null;
		}
		
	}
}

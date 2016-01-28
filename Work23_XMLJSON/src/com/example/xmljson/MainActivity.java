package com.example.xmljson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		testPullParser();
		
		initView();
		
		readShared();
		
	}
	
	private void readShared() {
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		String content = sp.getString("content", "");
		editText.setText(content);
	}
	
	private void initView() {
		editText = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				click(v);
			}
		});
	}

	//点击按钮
	public void click(View v){
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("content", editText.getText().toString());
		edit.commit();
		
		Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
	}
	/**
	 * pull 解析器解读 students.xml
	 */
	private void testPullParser() {
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			InputStream is = getAssets().open("students.xml");
			xmlParser.setInput(is, "utf-8");

			int type = xmlParser.getEventType();
			Student student = null;
			List<Student> list = new ArrayList<Student>();

			while (type != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				case XmlPullParser.START_TAG://开始读标签
					String name = xmlParser.getName();
					String text = "";
					if ("student".equals(name)) {
						student = new Student();
						text  = xmlParser.getAttributeValue(0);
						student.setId(Integer.parseInt(text));
					} else if ("name".equals(name)) {
						xmlParser.next();
						
						student.setName(text);
					} else if ("age".equals(name)) {
						xmlParser.next();
						text = xmlParser.getText();
						student.setAge(Integer.valueOf(text));
					}
					break;
				case XmlPullParser.END_TAG://标签结束的时候
					name = xmlParser.getName();
					text = xmlParser.getText();
					if("student".equals(name))
						list.add(student);
					break;
				}
				type = xmlParser.next();
			}
			Log.d("student", list.size() + "");
			for (int i = 0; i < list.size(); i++) {
				Log.d("student", list.get(i).toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.example.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.xmljson.R;

/**
 * json 得到 assets中的文件进行json方式解析
 * @author Barry
 *
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
	
		try {
			InputStream is = getAssets().open("news.json");
			
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer sb= new StringBuffer();
			
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			

			JSONArray arrays = new JSONArray(sb.toString());
			List<NewsBean> list = new ArrayList<NewsBean>();
			for (int i = 0; i < arrays.length(); i++) {
				JSONObject object = arrays.getJSONObject(i);
				NewsBean news = new NewsBean();
				news.setDetail(object.getString("detail"));
				news.setImagePath(object.getString("imagePath"));
				news.setTime(object.getString("time"));
				news.setCommit(object.getInt("commit"));
				 	 	
				list.add(news);
			}
			//
			for (NewsBean nb : list) {
				Toast.makeText(SecondActivity.this, nb.toString(), Toast.LENGTH_SHORT).show();
				Log.d("secondActivity", nb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	 

}

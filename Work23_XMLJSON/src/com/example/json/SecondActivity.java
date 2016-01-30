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
import com.google.gson.reflect.TypeToken;

/**
 * json 得到 assets中的文件进行json方式解析 运用Gson包解析 json数据成对象
 * 
 * @author Barry
 * 
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		try {

			// 得到json类型的数据
			InputStream is = getAssets().open("news.json");

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			// 转换成javaBean的对象

		/*	JSONArray arrays = new JSONArray(sb.toString());
			List<NewsBean> list = new ArrayList<NewsBean>();
			for (int i = 0; i < arrays.length(); i++) {
				JSONObject object = arrays.getJSONObject(i);
				NewsBean news = new NewsBean();
				news.setDetail(object.getString("detail"));
				news.setImagePath(object.getString("imagePath"));
				news.setTime(object.getString("time"));
				news.setCommit(object.getInt("commit"));

				list.add(news);
			}*/
			// 打印输出

			// 运用Gson包解析 json数据
			List<NewsBean> list = (List<NewsBean>) JsonUtils.fromJson(
					sb.toString(), new TypeToken<ArrayList<NewsBean>>() {
					});
			// 打印输出
			for (NewsBean nb : list) {
				Toast.makeText(SecondActivity.this, nb.toString(), Toast.LENGTH_SHORT).show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

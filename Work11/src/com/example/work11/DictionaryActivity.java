package com.example.work11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 词典
 * @author Barry
 *
 */
public class DictionaryActivity extends Activity {
	public static String dbName = "dictionary2.db";;
	private TextView tv_note;
	private AutoCompleteTextView actv_word;
	private Button btn_query;
	private SQLiteDatabase db2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);

	 	moveDatabase();
	 	db2 = openOrCreateDatabase(dbName, 1, null);
		initView();
		setLisenter();
		 setAdapter(); 
		 
		 
	}

	private void setLisenter() {
		btn_query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String word = actv_word.getText().toString();
				String note = DictionaryDao.queryChinese(db2, word);
				tv_note.setText(note);
			}
		});
	}

	private void setAdapter() {
		
		actv_word.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				String word = s.toString();
				//创建适配器
				Cursor cursor = db2.rawQuery(
						"select english as _id from t_words where english like ?",
						new String[] {word + "%" });
				
				CursorAdapter adapter =new CursorAdapter(DictionaryActivity.this,cursor) {
					  /**
                     * 将cursor转换成String
                     * @param cursor
                     * @return
                     */
                    @Override
                    public CharSequence convertToString(Cursor cursor) {
                        return cursor.getString(cursor.getColumnIndex("_id"));
                    }
				    /**
                     * 创建View
                     * @param context
                     * @param cursor
                     * @param parent
                     * @return
                     */
					@Override
					public View newView(Context context, Cursor cursor, ViewGroup parent) {
						View view =LayoutInflater.from(DictionaryActivity.this).inflate(R.layout.word_list_item, null);
						return view;
					}
					 /**
                     * 绑定View
                     * @param view
                     * @param context
                     * @param cursor
                     */
					@Override
					public void bindView(View view, Context context, Cursor cursor) {
						 TextView tv = (TextView) view;
	                     tv.setText(cursor.getString(cursor.getColumnIndex("_id")));
					}
				};
				actv_word.setAdapter(adapter);
			}
		});
	}

	/** 将自己 raw包下的数据库装到程序中 */
	private void moveDatabase() {
		File file2 = getDatabasePath(dbName);
		if(file2.exists())
			return ;
		
		
		File folder = file2.getParentFile();
		if (!folder.isDirectory())
			folder.mkdir();

		 

			InputStream is = null;
			OutputStream os = null;
			try {
				is = getResources().openRawResource(R.raw.dictionary);
				os = new FileOutputStream(file2);

				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}

				showTips("移动数据库成功");
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	/**准备控件*/
	private void initView() {
		tv_note = (TextView) findViewById(R.id.tv_note);
		actv_word = (AutoCompleteTextView) findViewById(R.id.actv_word);
		btn_query = (Button) findViewById(R.id.btn_query);
	}

	/**Toast输出*/
	public void showTips(String text) {
		Toast.makeText(DictionaryActivity.this, text, Toast.LENGTH_SHORT)
				.show();
	}
}

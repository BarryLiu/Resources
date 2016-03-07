package com.example.ed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ServerSocketFactory;

import org.apache.http.entity.FileEntity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter implements ListAdapter {
	private List<NewsBean> mList;
	private Context mLontext;

	private ExecutorService service;
	LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>(
			1024 * 1024 * 4) {
		protected int sizeOf(String key, Bitmap value) {
			return value.getRowBytes() * value.getHeight();
		};
	};

	public MyAdapter(List<NewsBean> list, Context context) {
		super();
		this.mList = list;
		this.mLontext = context;
		service =  Executors.newFixedThreadPool(2);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup parent) {
		if (currentView == null) {
			currentView = LayoutInflater.from(mLontext).inflate(
					R.layout.lv_item_news, null);
		}
		final ImageView iv = (ImageView) currentView.findViewById(R.id.iv);
		TextView tv = (TextView) currentView.findViewById(R.id.tv);

		final NewsBean nb = mList.get(position);
		tv.setText(nb.getName());

		iv.setTag(nb.getIconPath());

		Bitmap bmCache = imageCache.get(nb.getIconPath());
		if (bmCache != null) {
			iv.setImageBitmap(bmCache);
		} else {
			final File file = new File(Environment.getExternalStorageDirectory(),
					nb.getIconPath());
			if (file.exists()) {
				bmCache = BitmapFactory.decodeFile(file.getPath());
				iv.setImageBitmap(bmCache);
				
			} else {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						String path = "http://192.168.8.10:8080/Work39Server/images/"
								+ nb.getIconPath();
						try {
							URL url = new URL(path);
							URLConnection conn = url.openConnection();
							InputStream is = conn.getInputStream();
							Bitmap bitmap = BitmapFactory.decodeStream(is);

							DataHolder holder = new DataHolder();
							Message msg = handler.obtainMessage();

							holder.iv = iv;
							holder.bm = bitmap;
							holder.iconName = nb.getIconPath();

							msg.obj = holder;
							handler.sendMessage(msg);

							//Ð´ÈëÎÄ¼þ
							OutputStream os =new FileOutputStream(file);
							bitmap.compress(CompressFormat.PNG, 100, os);
							os.flush();
							os.close();
							
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				service.execute(r);
			}
		}
		return currentView;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			DataHolder holder = (DataHolder) msg.obj;
			String iconPath = (String) holder.iv.getTag();

			if (iconPath.equals(holder.iconName)) {
				holder.iv.setImageBitmap(holder.bm);
			}
		};
	};

	class DataHolder {
		Bitmap bm;
		ImageView iv;
		String iconName;
	}
}

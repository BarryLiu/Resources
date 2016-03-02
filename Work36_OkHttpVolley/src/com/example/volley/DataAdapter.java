package com.example.volley;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.example.ov.MainActivity;
import com.example.ov.R;

/**
 * 三级缓存缓存文件 如果本地有文件缓存就无需发送数据从网络中的到数据
 * 
 * 普通LruMap 实现存储
 * 
 * @author Barry
 * 
 */
public class DataAdapter extends BaseAdapter {
	private List<DataBean> mList;
	private static Context mContext;
	private ImageLoader imageLoader;
	// 创建一个内存缓存
	@SuppressLint("NewApi")
	static LruCache<String, Bitmap> imageCeche = new LruCache<String, Bitmap>(
			1024 * 1024) {
		// 用于计算每一个缓存中图片的大小
		protected int sizeOf(String key, Bitmap value) {
			return value.getRowBytes() * value.getHeight();
		};
	};

	public DataAdapter(Context context, List<DataBean> list, RequestQueue queue) {
		mContext = context;
		mList = list;

		imageLoader = new ImageLoader(queue, new ImageCache() {
			// 将图片放入到缓存中
			@SuppressLint("NewApi")
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				imageCeche.put(url, bitmap);
			}

			// 获取缓存中的图片
			@SuppressLint("NewApi")
			@Override
			public Bitmap getBitmap(String url) {

				return imageCeche.get(url);
			}
		});
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
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.lv_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.textView1);
			holder.iv = (ImageView) convertView.findViewById(R.id.imageView1);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final DataBean dBean = mList.get(position);
		holder.tv.setText(dBean.getStr());
		holder.iv.setImageResource(R.drawable.ic_launcher);

		// 指定当前控件显示那张图片
		String imageUrl = MainActivity.PATH + "images/" + dBean.getImage();
		holder.iv.setTag(imageUrl);

		imageLoader.get(imageUrl, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}

			@Override
			public void onResponse(ImageContainer response, boolean isImmediate) {
				// 获取到返回的图片的地址是否和imageView的一样
				String srcUrl = (String) holder.iv.getTag();
				String returnUrl = response.getRequestUrl();
				System.out.println("srcUrl="+srcUrl+"; returnUrl="+returnUrl);
				 if (returnUrl.equals(srcUrl)) {
					holder.iv.setImageBitmap(response.getBitmap());
				 }

			}
		});

		convertView.setTag(holder);
		return convertView;
	}

	private ViewHolder holder;

	class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	class ReflushData {
		Bitmap bitmap;
		String imageName;
		ImageView iv;
	}
}
package com.example.three;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.DataBean;
import com.example.Util;
import com.example.huc.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  三级缓存缓存文件
 *  如果本地有文件缓存就无需发送数据从网络中的到数据
 * @author Barry
 * 
 */
public class DataAdapter extends BaseAdapter {
	private List<DataBean> mList;
	private Context mContext;

	public DataAdapter(Context context, List<DataBean> list) {
		mContext = context;
		mList = list;
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
		holder.tv.setText(dBean.getName());
		// 封装类用来传递数据
		final ReflushData rd = new ReflushData();

		new Thread() {
			public void run() {
				try {

					String imagePath = Util.path + "images/"
							+ dBean.getImageName();
					Log.d("image", imagePath);
					URL url = new URL(imagePath);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);

					// 将图片的名字进行保存
					rd.bitmap = bitmap;
					rd.imageName = dBean.getImageName();
					rd.iv = holder.iv;
					// 指定当前控件显示那张图片
					holder.iv.setTag(dBean.getImageName());

					Message msg = handler.obtainMessage();
					msg.obj = rd;
					handler.sendMessage(msg);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();

		convertView.setTag(holder);
		return convertView;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 更新界面
			ReflushData rd = (ReflushData) msg.obj;

			// 判断当前的图片是否和需要被显示的图片一致
			// 获取控件对应的图片
			String imageSrcName = (String) rd.iv.getTag();

			if (imageSrcName.equals(rd.imageName)) {
				rd.iv.setImageBitmap(rd.bitmap);
			}
		};
	};

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
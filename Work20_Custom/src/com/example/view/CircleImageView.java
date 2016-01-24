package com.example.view;

import com.example.work19_custom.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CircleImageView extends View {
	Bitmap bm;
	Path path;
	public CircleImageView(Context context){
		super(context);
		init(context);
	}
	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	@SuppressLint("NewApi")
	private void init(Context context) {
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		bm = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.about_bg4);
		path = new Path();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// 最后设置的具体大小
		int width = 0;
		int height = 0;
		if (widthMode == MeasureSpec.EXACTLY) {// 大小直接从xml中，获取
			width = widthSize;
		} else {// 大小需要自己计算，比如wrap_content,0等等
			width = 400;
			height = 400;
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (isInEditMode()) {
			// 绘制占地区域
			canvas.drawColor(Color.RED);
			return;
		}
		// canvas.scale(0.5f, 0.5f);//缩小
		// canvas.rotate(45, bm.getWidth()/2, bm.getHeight()/2);//旋转
		// canvas.translate(0, 100);;//移动
		// canvas.skew(.5f, .5f);//扭曲了

		canvas.save();// 保存
		// 设置圆形路径
		path.addCircle(300, 100, 150, Direction.CCW);
		canvas.clipPath(path); // 裁剪路径
		canvas.drawBitmap(bm, 0, 0, new Paint());// 绘制图片
		canvas.restore();// 回滚
		//  canvas.drawBitmap(getClip(), 0, 0,new Paint());
		// canvas.drawPath(path, new Paint());
	}

	public Bitmap getClip() {
		Bitmap output = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Config.ARGB_8888);

		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);
		final Rect rect = new Rect(0, 0, bm.getWidth(), bm.getHeight());

		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(bm.getWidth() / 2, bm.getWidth() / 2, 100, paint);

		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bm, rect, rect, paint);
		return output;
	}
}

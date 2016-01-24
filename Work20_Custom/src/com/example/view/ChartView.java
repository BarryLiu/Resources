package com.example.view;


import com.example.work19_custom.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View{
	
	private int max;
	private final Path mPath=new Path();
	private Paint mPaint =new Paint();
	
	public ChartView(Context context) {
		super(context);
		init(context);
	}
	public ChartView(Context context,AttributeSet attrs) {
		super(context,attrs);
		init(context);
	}
	private void init(Context context) {
		mPaint.setColor(Color.RED);//设置颜色
		mPaint.setStrokeWidth(2f);//设置笔的宽度
		mPaint.setAntiAlias(true);//设置反锯齿
		mPaint.setStyle(Style.STROKE);//设置描边
		mPaint.setTextSize(15);//设置字体大小
	}

	int []lineValue=new int[7];
	public void setMax(int max) {
		this.max = max;
	}
	public void update(int value) {
		//将每一个数字都向前推进一位  最后一个是新生成的
		for(int i=0;i<lineValue.length-1;i++){
			lineValue[i] = lineValue[i+1];
		}
		lineValue[lineValue.length-1]=value;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int x= getWidth()/(lineValue.length+1);
		mPath.reset();
		mPath.moveTo(x, 364 - (lineValue[0] > 1000 ? 1000 : lineValue[0]) * 330 / 1000);
		for (int i = 1; i < lineValue.length; i++)
			mPath.lineTo((i + 1) * x, 364 - (lineValue[i] > 1000 ? 1000
					: lineValue[i]) * 330 / 1000);
		canvas.drawPath(mPath, mPaint);
		BitmapDrawable bmpDraw1 = (BitmapDrawable) getResources().getDrawable(
				R.drawable.pm2_point);
		Bitmap bmp1 = bmpDraw1.getBitmap();
		BitmapDrawable bmpDraw2 = (BitmapDrawable) getResources().getDrawable(
				R.drawable.pm2_point_);
		Bitmap bmp2 = bmpDraw2.getBitmap();
		for (int i = 0; i < lineValue.length; i++) {
			canvas.drawText(lineValue[i] + "", x * (i + 1), 364
					- (lineValue[i] > 1000 ? 1000 : lineValue[i]) * 330 / 1000
					- 15, mPaint);
			if (lineValue[i] > max)
				canvas.drawBitmap(bmp2, x * (i + 1) - bmp2.getWidth() / 2, 364
						- (lineValue[i] > 1000 ? 1000 : lineValue[i]) * 330
						/ 1000 - bmp2.getHeight() / 2, null);
			else
				canvas.drawBitmap(bmp1, x * (i + 1) - bmp1.getWidth() / 2, 364
						- (lineValue[i] > 1000 ? 1000 : lineValue[i]) * 330
						/ 1000 - bmp1.getHeight() / 2, null);
		}
		canvas.drawText("天气com", 10, 10, mPaint);
	}

	public void drawImage(Canvas canvas ,Bitmap bitmap ,int x,int y ){
		canvas.drawBitmap(bitmap, x, y,null);
	}
}

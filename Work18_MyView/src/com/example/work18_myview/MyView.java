package com.example.work18_myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * 跑马灯效果的图片
 * @author Barry
 *
 */
public class MyView extends View {

	int x = 0;
	Thread thread ;
	String text ;
	int color ;
	float size;
	
	public MyView(Context context) {
		super(context);
		init();
	}
	public MyView(Context context,AttributeSet attrs){
		super(context,attrs);
		init();
		//获取属性的数组
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.myview_layout);

		//1.数值中获得具体的值
		text = typedArray.getString(R.styleable.myview_layout_text);
		color = typedArray.getColor(R.styleable.myview_layout_textColor, Color.BLUE);
		size = typedArray.getDimension(R.styleable.myview_layout_textSize,  10);
		
		//2.直接xml中得到值（不推荐）
		text = attrs.getAttributeValue(null, "text");//arr1 :命名空间 ;arr2:name
		int colorId = attrs.getAttributeResourceValue(null, "textColor",0);//arr1：命名空间；arr2：名字；arr3：默认值
		//根据id获取值
		if(colorId>0){
			color =getResources().getColor(colorId);
		}
		
	}
	
	
	public void init(){
		thread = new Thread() {
			public void run() {
				boolean flag = true;
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					x += 10;
					if (x > 400) {
						x=0;
					}
					// postInvalidate();
					handler.sendEmptyMessage(0);
				}
			};
		};
		thread.start();
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			invalidate();
		};
	};

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint();

		p.setColor(color);
		p.setTextSize(size);
		canvas.drawText(text, x, 200, p);
	}
	

	public void stop() {
//		thread.stop();
	}
}

package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class AssortView extends Button {
	
	public interface OnTouchAssortListener{
		public void onTouchAssortListener(String s);
		public void onTouchAssortUP();
	}
	
	public AssortView(Context context) {
		super(context);
	}
	public AssortView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public AssortView(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs,defStyle);
	}
 
	private String[] assort = { "?", "#", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
	private Paint mPaint =new Paint();
	private int selectIndex = -1;
	private OnTouchAssortListener onTouch;
	
	public void setOnTouchAssortListener(OnTouchAssortListener onTouch) {
		this.onTouch = onTouch;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int width = getWidth();
		int height = getHeight();
		
		int interval = height / assort.length;
		
		for(int i=0;i< assort.length;i++){
			
			mPaint.setAntiAlias(true);
			mPaint.setTypeface(Typeface.DEFAULT_BOLD);
			mPaint.setColor(Color.WHITE);
			
			if(i==selectIndex){
				mPaint.setColor(Color.BLUE);
				mPaint.setTextSize(30);
				
				mPaint.setFakeBoldText(true);
			}
			
			float xPos = width / 2 - mPaint.measureText(assort[i]) / 2;
			float yPos = interval * i + interval;
			canvas.drawText(assort[i], xPos, yPos, mPaint);
			mPaint.reset();

		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		
		float y = event.getY();
		int index = (int) (y / getHeight() * assort.length);
		if (index >= 0 && index < assort.length) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				if (selectIndex != index) {
					selectIndex = index;
					if (onTouch != null) {
						onTouch.onTouchAssortListener(assort[selectIndex]);
					}

				}
				break;
			case MotionEvent.ACTION_DOWN:
				selectIndex = index;
				if (onTouch != null) {
					onTouch.onTouchAssortListener(assort[selectIndex]);
				}

				break;
			case MotionEvent.ACTION_UP:
				if (onTouch != null) {
					onTouch.onTouchAssortUP();
				}
				selectIndex = -1;
				break;
			}
		} else {
			selectIndex = -1;
			if (onTouch != null) {
				onTouch.onTouchAssortUP();
			}
		}
		invalidate();

		return true;
	}
}

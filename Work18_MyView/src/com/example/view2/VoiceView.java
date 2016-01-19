package com.example.view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 声音控件 
 * 用于 显示声音图片
 * @author Barry
 *
 */
public class VoiceView extends View{

	
	Paint paint =new Paint();
	Paint lightPaint ;
	public VoiceView(Context context) {
		super(context);
		init();
		
	}
	public VoiceView (Context context, AttributeSet attrs){
		super(context, attrs);
		init();
	}
	int x=50;
	int y=50;
	
	int width = 30;
	int height = 10;
	
	int maxSize = 15;
	//它们之间的间隔
	int offY = 3;
	
	int position = 0;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		//黑色部分
		
		for(int i=0;i<maxSize;i++){
			int startX = x;
			int startY = y+(height+offY)*i;
			
			canvas.drawRect(startX, startY,startX+width,startY+height,lightPaint);//arg1 左上角x；arg2：左上y  arg3：右下角x； arg4：右下角y
		}
		
		
		//亮色部分 
		
		for(int i=position;i<maxSize;i++){
			int startX = x;
			int startY = y+(height+offY)*i;
			canvas.drawRect(startX, startY,startX+width,startY+height,paint);
		}
		
	}
	
	public void init(){
		paint =new Paint();
		paint.setColor(Color.BLACK);
		
		lightPaint =new Paint();
		lightPaint.setColor(Color.WHITE);
	}
	
	//点击事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//计算出点击位置
		float _y =  event.getY()-y;
		//每个格子的高度
		int temp = height+offY;
		//position 第几个位置
		position= (int) (_y/temp);
		
		//小于0 的时候让他= 0 ,解决拖上去不停的问题
		if(position <0 )
			position = 0;
		//刷新事件
		invalidate();
		
		//返回true 可拖动
		return true;
	}	
}

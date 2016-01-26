package com.example.work22_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

/**
 * 主要实现两个页面的跳转    treen动画 与  帧动画 
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//设置重右边滑出来的样式
		overridePendingTransition(R.anim.left_in, R.anim.right_out);
		////Tween动画 放大
		testScaleAnimation();
	}

	private void testScaleAnimation() {
		imageView = (ImageView) findViewById(R.id.imageView);
		//创建放大缩小动画 	arg1 :动画启动的时候大小   arg2:动画结束的时候大小  	arg5:arg6://设置锚点
		Animation animation =new ScaleAnimation(1.0f,2.0f,1.0f,2.0f,imageView.getWidth()/2, imageView.getHeight()/2);
		animation.setDuration(2000); //设置动画的时间
		animation.setFillAfter(true);//保持放大之后的效果
		imageView.setAnimation(animation);
	}
	//点击button从下面加载出来的窗口
	PopupWindow popupWindow;
	public void jump(View view) {//点击按钮
		View popView = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.pop_layout, null);
		popupWindow = new PopupWindow(popView,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT);
		
		Button btn_canel = (Button) popView.findViewById(R.id.btn_canel);
		btn_canel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		popupWindow.setAnimationStyle(R.style.dialog_style);
		popupWindow.showAtLocation(popView, Gravity.BOTTOM, 400, 400);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float startX = 0;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (startX > event.getX() - 100) {
				 Intent intent =new Intent(MainActivity.this,SecondActivity.class);
				  startActivity(intent);
			}
			break;
		}

		return super.onTouchEvent(event);// 默认为true
	}

}

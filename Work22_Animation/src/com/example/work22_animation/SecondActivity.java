package com.example.work22_animation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class SecondActivity extends Activity {

	private ImageView imageView;
	private ImageView imageView2;
	private ImageView ivLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	
		//Tween动画 旋转
		testRotateAnimation(); 
		
		//帧动画
		testZhen(); 
		
	}
	/**帧动画*/
	private void testZhen() {
		ivLoading = (ImageView) findViewById(R.id.iv_loading);
		 AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getBackground();
		animationDrawable.start();
	}

	/**
	 * 雷达效果
	 */
	private void testRotateAnimation() {
		imageView = (ImageView) findViewById(R.id.iv_scanning);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		
        //获取image控件中图片的高度和宽度
		 int width=imageView.getDrawable().getIntrinsicWidth();
          int heigth=imageView.getDrawable().getIntrinsicHeight();
        //创建旋转动画
         Animation animation = new RotateAnimation(0,
                360,
                width/2,
                heigth/2
        );
        animation.setDuration(1000);
        //设置重复次数   Animation.INFINITE:无限次
        
        LinearInterpolator lin = new LinearInterpolator();  //设置匀速旋转
        animation.setInterpolator(lin);  
        animation.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(animation);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float startX =0;
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if(startX<event.getX()-100){
				jump();
				finish();
			}
			break;
		}
		
		
		return super.onTouchEvent(event);
	}

	private void jump(){
		Intent  intent =new Intent(SecondActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	//鼠标的点击    实现属性动画的效果
	public void show(View v){
		 FragmentManager fm = getFragmentManager();
	        Fragment fragment = fm.findFragmentById(R.id.right_content);

	        FragmentTransaction ft = fm.beginTransaction();
	        if(fragment.isHidden()){
	            //自带动画
	           // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	            //自定义动画
	            ft.setCustomAnimations(
	                    R.animator.fragmet_open,//进来的
	                    R.animator.fragmet_open);//出去的动画

	            ft.show(fragment);
	        }else{
	          //  ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

	            ft.hide(fragment);
	        }
	        ft.commit();
	}
}

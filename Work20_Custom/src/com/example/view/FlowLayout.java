package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 一个相似于{@link LinearLayout} 的控件 ,  当其横着宽度自动占满后自动换行
 * @author Barry
 *
 */
public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 把所有的子元素的尺寸进行测量
		measureChildren(widthMeasureSpec, heightMeasureSpec);
	}

	// 用于设置布局的规则
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 当前的设置点
		int currentX = 0;
		int currentY = 0;

		for (int i = 0; i < getChildCount(); i++) { // 遍历所有的子元素，设定规则
			// 获取子元素
			View childView = getChildAt(i);
			// 得到子元素的额位置
			int width = childView.getMeasuredWidth();
			int height = childView.getMeasuredHeight();

			// childView.layout(width*i,currentY, width*i+width,
			// currentY+height);//arg1:左上角的x arg3：右下角的x

			if (currentX > getWidth()) {// 当开始绘制的位置大于父容器的大小设置让它换行
				currentY += height;
				currentX = 0;
			}

			//每个控件的开始位置
			childView.layout(currentX, currentY, currentX + width, currentY
					+ height);// arg1:左上角的x arg3：右下角的x
			
			currentX += width;
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		return super.dispatchTouchEvent(ev);
	}
}

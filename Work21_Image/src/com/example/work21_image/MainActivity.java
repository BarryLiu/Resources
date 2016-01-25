package com.example.work21_image;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d";
	    static final String SCALE_TOAST_STRING = "Scaled to: %.2ff";

	    private TextView mCurrMatrixTv;

	    private PhotoViewAttacher mAttacher;

	    private Toast mCurrentToast;

	    private Matrix mCurrentDisplayMatrix = null;
	    ImageView mImageView ;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	          mImageView = (PhotoView) findViewById(R.id.iv_photo);
	        mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);

	        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
	        mImageView.setImageDrawable(bitmap);
	        
	        // The MAGIC happens here!
	        mAttacher = new PhotoViewAttacher(mImageView);

	        // Lets attach some listeners, not required though!
	        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
	        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
	    }


	    @Override
	    public void onDestroy() {
	        super.onDestroy();

	        // Need to call clean-up
	        mAttacher.cleanup();
	    }

	    

	  

	    private class PhotoTapListener implements OnPhotoTapListener {

	        @Override
	        public void onPhotoTap(View view, float x, float y) {
	            float xPercentage = x * 100f;
	            float yPercentage = y * 100f;

	            showToast(String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage, view == null ? 0 : view.getId()));
	        }
	    }

	    private void showToast(CharSequence text) {
	        if (null != mCurrentToast) {
	            mCurrentToast.cancel();
	        }

	        mCurrentToast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
	        mCurrentToast.show();
	    }

	    private class MatrixChangeListener implements OnMatrixChangedListener {

	        @Override
	        public void onMatrixChanged(RectF rect) {
	            mCurrMatrixTv.setText(rect.toString());
	        }
	    }
	    
	    
	    int dex =0; //旋转的角度
	    /**
	     * 点击旋转按钮
	     * @param v
	     */
	    @SuppressLint("NewApi")
		public void toSecond(View v){
	    	dex = dex + 30;
	    	if(dex >360)
	    		dex =0;
	    	mImageView.setRotation(dex); //设置旋转
	    }
}

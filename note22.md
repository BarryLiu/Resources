#18动画
###使用第三方控件
- 从网上下载第三方控件
- sample:用于学习第三方控件
- library:要被使用的第三方工具,用eclipse可以直接导入library;Android studio需要创建一个library的module
- 要将开发中工程(module)和library关联起来
- 直接使用library中提供的控件
###Bitmap的使用
- 从res/drawable目录,assets目录和手机中加载Bitmap图片
- Bitmap和BitmapFacotry的区别
- BitmapFacotry:内存从无到有的过程,从各种文件中加载图片到内存
- Bitmap:从内存中,再拷贝出新的一张图片
###OOM(内存溢出)
- Cursor和数据需要关闭避免内存溢出
- Activity不能直接传递,要使用接口
####图片加载的时候,要使用缩略图
- 通过options加载图片的大小
- 和目标大小进行比较获取到缩小的size
- 通过options再次加载缩小以后的图片
####Drawable和Bimap的区别
- 所有的可以绘制的内容bitmap,shape都是要被封装成drawable才会被绘制
- 直接加载Drawable效率最高
####Image缓存的使用
- 避免加载重复的图片
- 创建一个HashMap容器,用于存放图片的缓存
- 每次在加载的时候,先从缓存中判断,看图片是否已经加载了
- 如果加载了那么则直接从缓存中获取图片
- 如果没有那么加载图片的同时随便将图片放入到缓存中

#安卓中的动画
##Tween动画
- 计算机自动生成的动画效果
###使用代码加载动画效果
- 创建出一个动画效果
- 设置动画效果的参数
- 让控件执行动画效果

           iv_scanner = (ImageView) findViewById(R.id.iv_scanner);

        iv_scanner.post(new Runnable() {
            @Override
            public void run() {
                //创建一个旋转的动画
                Animation animation = new RotateAnimation(
                        0,
                        360,
                        iv_scanner.getWidth() / 2,
                        iv_scanner.getHeight() / 2
                );
                //设置动画的时
                animation.setDuration(1000);
                //设置次数
                animation.setRepeatCount(Animation.INFINITE);

                //开始动画
                iv_scanner.startAnimation(animation);
            }
        });


> 不可以再onCreate中,获取控件的大小,因为在oncreate的时候界面并没有创建出来,所以无法知道控件的大小。可以使用控件.post执行一个runnable来获取控件的大小

###使用Xml的方式加载动画
- 在res目录下创建一个anim文件夹
- 创建旋转动画的xml,在其中设置动画的相关属性
- 在代码中通过AnimationUtils加载动画
- 通过控件startAnimation

###动画的应用Activity的动画效果

      Intent intent = new Intent();
        intent.setClass(this,Main2Activity.class);
        startActivity(intent);
        //设置Activity的动画效果
        overridePendingTransition(
                R.anim.right_in,
                R.anim.left_out
        );
- 手势滑动切换Activity

        float startX;
    	@Override
    	public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取按下去的x坐标
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                //从右向左边滑动
                if(startX - event.getX()>100){
                    jump(null);
                }
                break;
        }

        return super.onTouchEvent(event);}
###PopupWindows的动画效果
- popupwindwos:在任意位置弹出的对话框

- 1，创建动画xml
- 2，在style中设置进入和出去的动画效果
    	<style name="popUpWindowsStyle">
        <item name="android:windowEnterAnimation">
            @anim/fadein
        </item>
        <item name="android:windowExitAnimation">
            @anim/fadeout
        </item>
    	</style>
- 3, 设置对话框的动画样式

		popUpWindows.setAnimationStyle(R.style.PopupAnimation);


##帧动画
- 需要多张图片生成的动画
- 将所有的图片拷贝到drawable目录下
- 在drawable目录下创建一个animation-list的xml进行绘制
- 在layout的xml中设置创建的animation的drawable为控件的background
- 在代码中获取到Animationdrawable开始动画

        ImageView iv_loading = (ImageView) findViewById(R.id.iv_loading);
        //获取到背景动画
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getBackground();
        animationDrawable.start();

##属性动画
- 是3.0之后出现的动画效果,配合Fragment出现的动画效果
- 使用xml创建属性动画
- 在res的animator目录创建属性动画
- 主要是使用在Fragment的动画效果中
##自定动画
- 第三方的动画效果
- NineOldAndroids:大神写的第三方效果




Enter:进入
Animation:动画
Interpolator:振幅器
INFINITE:无限次
inJust:仅仅
Decode:解析
Bounds:边框



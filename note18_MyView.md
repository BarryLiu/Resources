#自定义View
- 在安卓中所有的控件和布局都是继承于View
##跑马灯效果的view
- 继承一个view,实现其onDraw方法

        int x =0;
    	@Override
    	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        //绘制文字
        canvas.drawText("你好世界",x,100,paint);}
- 在构造方法中创建一个线程动起来

        public MyView(Context context) {
        super(context);
        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x += 10;
                    if (x>400){
                        x =-80;
                    }
                    //子线程更新
                    postInvalidate();
                    //发送一个消息到主线程中更新
                    //handler.sendEmptyMessage(0);
                }
            }
        };
        thread.start();
    }

> 1,主线程不能进行耗时操作,让子线程运行
> 
> 2,子线程不能更新UI,使用handler在主线程中更新

##在xml中使用自定义控件
- 实现由2个参数以上的构造方法
###从xml中给自定义view传值
- 在res目录下创建一个attrs.xml在其中声明模板

        <declare-styleable name="myview_style">
        <!-- 声明属性-->
        <attr name="text" format="string"></attr>
        <attr name="color" format="color"></attr>
        <attr name="size" format="dimension"></attr>
    </declare-styleable>
- 在xml中通过模板传递数值
> 1在根布局中声明命名空间

     xmlns:custom="http://schemas.android.com/apk/res-auto"
> 2在控件中传递xml的数值

        <com.geek.com.customviewa9.MyView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        custom:text  = "欢迎来到英雄联盟"
        custom:color = "#00ff00"
        custom:size  = "10sp"
        />
- 在自定义控件中,接受参数

      	//获取属性的数组
       	TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myview_style);

        //从数值中获取具体的数值
        text = typedArray.getString(R.styleable.myview_style_text);
        color = typedArray.getColor(R.styleable.myview_style_textColor, Color.BLACK);
        size = typedArray.getDimension(R.styleable.myview_style_textSize,10);
  
  



invalidate:使xxx无效
dimension:尺寸
Attribute:属性
Rect:矩形

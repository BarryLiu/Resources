#9Shape,selector,样式(style.xml)

##intent
- 信使:用于组件和组件之间的传递信息
- Activity之间的相互调用
- Intent传递数值的四种方法
##Activity的方法
- 第一个Activity是startActivityForResult(intent,requestCode)
- 第二个Activity返回的时候是setResult(responseCode,intent)
- 在第一个Activity响应onActivityForResult方法
##Intent的隐式调用
- 组件是可以取外号的
-  <intent-filter>:intent的条件过滤器

     	Intent intent = new Intent();
        //设置调用打开的动作
                       intent.setAction(Intent.ACTION_VIEW);
        //设置使用那个App进行打开，打开那个文件
                        intent.setDataAndType(
                                Uri.fromFile(file),//打开的文件
                                "audio/x-mpeg"//设置打开的类型
                        );
                        startActivity(intent);





#样式(style.xml) 
		<!--如果parent 有的话就是一个样式,activity可用不同的样式,没有的话 可以让不同的控件有这样的属性 -->
		<style name="DarkTheme" parent="Theme.AppCompat.NoActionBar" >
        <item name="android:colorForeground">@color/foreground_material_dark_1</item>
    </style>


#安卓资源文件
- 安卓res目录下的相关文件
##Values目录
- 用于设置文字,大小和尺寸以及样式的地方
- 所有的xml名字可以任意取,关键是所有的文件中都有resource标签
- <color>:颜色
- <dimen>:尺寸
- <string>:文字
- style:样式和主题
###样式
- 可以将重复的设置放在一个样式中,便于对控件的控制
- 对一个控件进行控制
###主题 
- 对一组控件进行控制
- 主题必须要有一个父类
####白天和夜晚切换
- 在onCreate方法中使用setTheme方法设置主题
- 在点击事件的时候用一个static的变量保存改变主题的状态
- 点击之后调用recreate方法重新创建Activity

##Selector
- 在控件不同的状态下,显示不同的样子

        <!--设置不同状态下显示的不同的图片 -->
    	<item android:state_pressed="true"
        	android:drawable="@drawable/btn_add_deal_down">
    </item>

    	<item android:state_pressed="false"
        android:drawable="@drawable/btn_add_deal_normal">
    </item>
##shape
- 形状:计算机绘制的图,不需要外部的图片
- solid:填充
- stroke:描边
- corners:圆角
##layer-list
- 层关系
- 其中的每一个item是有层的概念的

       	<!--底层的黑色-->
    	<item>
        <shape
            >
            <solid
                android:color="#000000"
                ></solid>
        </shape>
    	</item>
    	<!-- 顶部的白色 -->
    	<item
        android:bottom="1dp"
        >
        <shape
            >
            <solid
                android:color="#ffffff"
                ></solid>
        </shape>
    </item>


layer:层
Light:灯
Holo:神圣
Black:黑
Dark:暗黑
state:状态
shape:形状
corners:边角
gradient:渐变



##安卓的工程目录
- src:代码存放
- res:资源文件存放
- Androidmanifest.xml:配置注册文件
- gen:自定生成代码存放的位置
##方法
- Activity的中onCreate这个是Activity的入口方法,也是我们代码的入口
- setContentView:让Activity可以显示一个layout布局
- findViewById:用于从布局中找到一个特定控件
#布局和控件
##布局
- 按照一定的规则在空间中摆放物体
- 五大布局
##RelativeLayout(相对布局)
- 必须有一个参照物作为对象
- 第一大类属性:相对于parent(屏幕或者是父类容器)的上,下,左右,中间的位置;数值是有true和false
- 第二大类属性:相对于兄弟元素的方向;数值是兄弟元素的id;修改id的时候要利通工具
- 第三大类属性:就是精确的数值,margin外边距
- 相对布局的属性是会冲突

##LinearLayout(线性布局)
- 要么横着;要么竖着
- orientation:方向其实数值(V是竖的,H是横)
###layout_width和layout_height
- 是所有View的爹妈设置的属性,必须得有
- match_parent:和父类容器一样大
- wrap_content:自有有多大就占多大空间
- 也可以自定义设置长度,单位是dp
> 布局是可以嵌套的
###gravity和layout_gravity
gravity:控件自身内部的重心方向
layout_gravity:控件在其他容器中的重心
##TableLayout表格布局
- 必须和TableRow配合使用
##FrameLayout帧布局
- 内部的元素都是左上角对齐
- 播放最大化视频
##绝对布局
- 通过layout_x和layout_y来设置坐标
#控件
##TextView
- 文字的大小用sp做单位;其他的用dp做单位
- singleLine:保存只有一行
- lines:可以设置多行
- android:ellipsize="end"保证多行超出的部分用...表示
##Button
- 响应点击事件
- duration:持久力,持续时间

    	btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(
						MainActivity.this,//activity 
						"我来了",//弹出的内容 
						Toast.LENGTH_SHORT).show();
			}
		});
##EditText
- android:hint="请输入数字"
- android:inputType:设置输入的类型
- android:maxLength:输入的最大长度
##ImageView
- 先将图片资源拷贝到drawable目录下
- 然后通过src设置图片的路径
##CheckBox复选对话框
- isChecked()

##RadioButton单选对话框
- 必须和RadioGroup配合使用

    	RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		//获取被选中的button的id
		int id = rg.getCheckedRadioButtonId();
		RadioButton rb = (RadioButton) findViewById(id);
		Toast.makeText(MainActivity.this,
				rb.getText().toString(), Toast.LENGTH_SHORT).show();
##9png
- 可扩展图片
- sdk\tools\lib\draw9patch.jar可以直接制作9
png的图片
	

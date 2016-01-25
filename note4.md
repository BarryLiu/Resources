##五大布局
- 按照一定的规则在空间中摆放元素
- 线性布局:横h和竖v;layout_weight权重
- 相对布局:要有参照物;1,父类元素.2,兄弟元素.3,详细数值margin
- 帧布局:左上角对齐
- 表格布局:必须和TableRow同时使用
- 绝对布局:根据x和y的坐标进行定位
- layout_width和layou_height:必须有
##基础控件
- TextView:存放文字的
- Button:setOnClickListener实现点击事件仿佛
- EditText:inputType输入数字和密码
- ImageView:首先将图片拷贝到drawable目录下;然后配置src
- CheckBox:复选对话框,可以选择多个isCheck()
- RadioButton:一定要和RadioGroup配合起来使用;RadioButton的id不能相同
- 布局和控件的父类都是View
##9png
- 可扩展的图片
##scrollView
- 只能有一个子元素控件,必须设置一个布局
#高级View的使用
- 可以显示多条数据的控件spinner,listView,gridView
- 必须要和适配器同时使用
##适配器使用的三步走
1. 获取数据
2. 创建适配器
3. 绑定适配器
##ArrayAdapter的使用方法
    ArrayAdapter adapter = new ArrayAdapter
				(MainActivity.this, 
				android.R.layout.simple_dropdown_item_1line,//每一条元素的布局样式
						cities);//数据
##SimpleAdapter的使用
- 使用List<Map<String,Object>>构建数据

    	SimpleAdapter adapter = new SimpleAdapter(
				this, 
				data, //数据
				R.layout.lv_item,//每一行数据的布局元素
				new String[]{"id","name","status"}, 
				new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_status});//被映射的控件
##点击事件

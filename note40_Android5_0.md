#TabLayout
- 选项卡布局,一般是和ViewPager
 	
	 	tabLayout.setupWithViewPager(mViewPager);
- 设置是否可以滑动

    	app:tabMode="scrollable"

#BaseActivity

#RecyclerView
- 用于取代ListView的控件
- listView的四个方法:设置长度,获取item,获取id,getView
- getView:创建子视图和填充
- 两个优化

##四个步骤
- 1创建数据
- 2设置布局方式

     	rv.setLayoutManager(gridLayoutManager);
- 3创建适配器
- 4绑定适配器

##三个方法
- getItemCount:设置长度
- onCreateViewHolder:创建视图并且保持在ViewHolder中
- onBindViewHolder:从Viewhodler中找到控件绑定数据

#CardView
- 卡片

     	app:cardCornerRadius="3dp"

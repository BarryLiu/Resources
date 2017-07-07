##ListView显示三部曲
1. 获取数据
2. 创建适配器(创建每一个子元素的视图)
3. 绑定适配器
#自定义适配器(BaseAdapter)
- 四大方法
- 显示数据的长度
- getCount(重点):用于设置当前的长度
- getItem():得到当前位置的对象
- getItemId():获取当前位置的id
- getView(重点):创建当前数据的视图

    	//将视图创建出来
		//获取视图构造器
		LayoutInflater lif = LayoutInflater.from(mContext);
		//构造视图
		View view = lif.inflate(R.layout.item, null);
		//将数组中的数据添加到对应控件中
		Student s = mList.get(position);
		//从构建的view中找到控件
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_tel  = (TextView) view.findViewById(R.id.tv_tel);
		ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
		//将数据放入到控件中
		tv_name.setText(s.getName());
		tv_tel.setText(s.getTel());
		iv_icon.setImageResource(s.getResId());
		
		
		if(position%2 == 0){
			view.setBackgroundColor(Color.YELLOW);
		}
		
		if(position == 0){
			view.setBackgroundColor(Color.RED);
		}
		
		//将View返回
		return view;
- ListView元素的点击事件

    		//4,实现点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(
					AdapterView<?> parent, //点击控件的适配器视图
					View view,//当前点击的视图
					int position, //当前点击的位置
					long id) {
				//获取适配器
				MyAdapter adapter = (MyAdapter) parent.getAdapter();
				Student s = (Student) adapter.getItem(position);
				
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						s.getName(), Toast.LENGTH_SHORT).show();
			}
		});
- 在getView方法中,创建了每一个元素视图,并且将数据填充对应的控件中,最后将视图返回
##适配器的优化
- 避免重复创建View

    	if (convertView == null) {
			LayoutInflater lif = LayoutInflater.from(mContext);
			// 构造视图
			convertView = lif.inflate(R.layout.item, null);
		}

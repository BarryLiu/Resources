
#联系人管理器3
#ViewPager
- 先在xml中创建出viewpager

        <android.support.v4.view.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    	</android.support.v4.view.ViewPager>
- 获取List<View>的数组

        //1,获取数据
        list = new ArrayList<View>();
        View v1 = getLayoutInflater().inflate(R.layout.layout1,null);
        View v2 = getLayoutInflater().inflate(R.layout.layout2,null);
        View v3 = getLayoutInflater().inflate(R.layout.layout3,null);
        list.add(v1);
        list.add(v2);
        list.add(v3);
	
- 实现PagerAdapter

	   	public class MyAdapter extends PagerAdapter{
        /**
         * 获取view的数量
         * @return
         */
        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 判断view是否来自对象
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 创建每一个view
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //将对应位置的view放入到vp中
            container.addView(list.get(position));

            //返回添加的view
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            //去掉出去的view
            container.removeView(list.get(position));
        }
- 绑定适配器

    	vp.setAdapter(adapter);

##RadioGroup的点击事件

            //实现RadioGroup的切换事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //判断点击的位置
                int position = 0;
                switch (checkedId) {
                    case R.id.rb_group://组的位置
                        vp.setCurrentItem(0);
                        position = 0;
                        break;
                    case R.id.rb_contact://联系人的位置
                        vp.setCurrentItem(1);
                        position = 1;
                        break;
                    case R.id.rb_love://收藏的位置
                        vp.setCurrentItem(2);
                        position = 2;
                        break;
                }
                //遍历所有的line，根据点击的位置判断line是否可见
                for (int i = 0; i < listLines.size(); i++) {
                    TextView tv = listLines.get(i);
                    if (i == position) {
                        //当前的line是点击的位置所有可以见
                        tv.setVisibility(View.VISIBLE);
                    } else {
                        tv.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
##ViewPager的滑动事件

       //实现ViewPager的滑动事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 当界面已经被选中之后
             * @param position 被选中的位置
             */
            @Override
            public void onPageSelected(int position) {
                //根据位置获取当前应该被选中的radioButton
               RadioButton rb = (RadioButton) rg.getChildAt(position);
                //设置当前的butto被选中
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

##实现SearchView的点击事件

        SearchView searchView = (SearchView) listPagers.get(1).findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * 文字发生变化的时候进行查询
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {

                initContactList("sort_key like '%"+newText+"%'");
                return false;
            }
        });



instantiate:初始化
destroy:删除
visibility:可见度

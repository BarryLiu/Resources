
##getView中的子布局抢焦点的问题
- 在子布局控件的布局中,添加属性
       android:descendantFocusability="blocksDescendants"
#相关问题
- 界面刷新

    	adapter.notifyDataSetChanged();
- 数据遗留:重置数据
- 丢失问题:保存数据

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s.setIsDelete(isChecked);
            }
        });
- ListView,GridView之间会相互嵌套,其解决问题的难点关键在于嵌套数据的构造

       listData = new ArrayList<Data>();
        for (int i=0;i<20/nums;i++){
            Data data = new Data();
            List<Movie> listMovie = new ArrayList<Movie>();

            //创建每一行的所有的电影的数据
            for (int j=0;j<nums;j++) {
                int pos = i*nums + j;
                Movie m = new Movie();
                m.setName("电影" + pos);
                m.setResId(imageIds[pos % imageIds.length]);
                listMovie.add(m);
            }
            data.setList(listMovie);
            listData.add(data);
        }
- listView的addHeadView和addFootView;只有ListView有这个方法

#联系人管理器2

##如何获取系统联系人
- 通过ContentResovler获取系统提供的数据库中的联系人的信息,在Activity通过getContentResovler获取
- 授予app读取联系人的相关权限
- cr.query(uri)对提供的数据库
进行查询
    	
		Uri.parse("content://com.android.contacts/raw_contacts");
> 因为联系数据库存放在三个表中,先获取用户的id,然后通过id获取联系人的相关的信息
- Uri:统一资源标识符,就是可以通过固定格式获取特定的内容
##ExpandableListView
- 三个步骤
- BaseExpandableListAdapter:十个方法(8+2),4个方法是用于创建grouplist,4个方法是创建每个group的childlist的视图

#排序
##Collection和Collections的区别
- Collection:集合list,set
###list
- ArrayList是采用数组方式存储数据，实现了可变大小的数组
- Vector也是采用数组方式存储数据，由于使用了synchronized方法（线程安全）所以性能上比ArrayList要差。
- LinkedList使用双向链表实现存储。
###Set
- HashMap:效率高,允许空值,线程不同步
- HashTable:效率低,不允许空值,线程同步
- TreeMap:所有元素保持一个固定的顺序，可用于Map集合中元素排序，不允许键对象是null
###Collections排序
- Collections.sort();排序方法
- Comparator:实现比较器,为排序设置一个规则

       	Collections.sort(list.get(0).getList(), new Comparator<PersonBean>() {
            @Override
            public int compare(PersonBean lhs, PersonBean rhs) {
                int id1 = lhs.get_id();
                int id2 = rhs.get_id();
				//比较两个对象的id号,进行排序
                if (id1 > id2){
                    return -1;
                }else if (id1 <id2){
                    return 0;
                }

                return 0;//1,0,-1
            }
        });
###拼音排序
- 使用第三方的jar包
- 先获取第一个首写字母的字符

             //获取第一个字符的拼音
            String[] str1= PinyinHelper.toHanyuPinyinStringArray(pb.getName().charAt(0));
            if (str1 == null){
                //将所有的字符转换成大写
                String l = pb.getName().toUpperCase().substring(0,1);
                pb.setFirstLetter(l);
            }else{
                pb.setFirstLetter(str1[0].toUpperCase().substring(0,1));
            }

- 再使用首写字母进行排序

         Collections.sort(list.get(0).getList(), new Comparator<PersonBean>() {
            @Override
            public int compare(PersonBean lhs, PersonBean rhs) {
                //获取首字符的char
                char letter1 = lhs.getFirstLetter().charAt(0);
                char letter2 = rhs.getFirstLetter().charAt(0);
                if(letter1<letter2){
                    return -1;
                }
                return 0;//1,0,-1
            }
        });
- 先创建出26个字母的group

        public static final String[] Letters = {
            "#",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    	};
- 将对应字母放入到数组中

             if(pb.getFirstLetter().charAt(0)<('A'-1)){
                list.get(0).getList().add(pb);
            }else{
                list.get(pb.getFirstLetter().charAt(0)-'A'+1).getList().add(pb);
            }
- 将对应字母数组为0的数组删除

         //遍历list集合发现有size为0的就
       	for (int i = 0;i<list.size();i++){
           int count = list.get(i).getList().size();
           if(count == 0){
               list.remove(i);
               i--;
           }
       }
- 将所有的ExpandableListView展开

           for (int i = 0;i<list.size();i++){
           int count = list.get(i).getList().size();
           if(count == 0){
               list.remove(i);
               i--;
           }
       }
- 去掉expandListView的指示标签

        android:groupIndicator="@null"
- 取消点击展开的事件,在getGroupView的时候抢事件

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            //去抢父类的点击事件
            convertView.setClickable(true);
        }
###要实现expandableListView的点击事件
- 在Adapter中将isChildSelectable方法的返回值修改成true
		
		//要将返回修改成true
        public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    	}
- 在Activity中实现elv的点击事件
            
		elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PersonBean pb = (PersonBean) adapter.getChild(groupPosition, childPosition);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditActivity.class);
                //将数据传递过去
                intent.putExtra(Contants.TAG_PERSON,pb);
                startActivity(intent);
                return false;
            }
        });
###更新系统联系人
- 提供系统联系人的写入权限

    	<uses-permission android:name="android.permission.WRITE_CONTACTS"/>
- 通过ContentResovler更新数据库

        Uri uri = Uri.parse("content://com.android.contacts/data");
        //更新名字
        ContentValues values = new ContentValues();
        values.put("data1",pb.getName());
        //update
        cr.update(uri,values,"raw_contact_id = ? and mimetype_id = ?",new String[]{
                pb.get_id()+"",
                7+""
        });
        ContentValues values1 = new ContentValues();
        values1.put("data1", pb.getTel());
        //update
        cr.update(uri,values1,"raw_contact_id = ? and mimetype_id = ?",new String[]{
                pb.get_id()+"",
                5+""});
- 更新完毕之后要刷新界面,使用startActivityForResult进行刷新

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //重新刷新
        if(resultCode == RESULT_OK){
            initContactListView();
        }}
> 如果是取消的时候是不会刷新界面的

- 更新之后,expandlistView的位置要和之前的位置保持一致

		//先将之间的位置进保存
        int selection = elv.getFirstVisiblePosition();
		//设置适配器	
		elv.setAdapter(adapter);
		// 回到之间的位置
        elv.setSelection(selection);

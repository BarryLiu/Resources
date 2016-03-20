#48_2ContentProvider
#ContentProvider
- 内容提供者
- 用于安卓app之间数据共享
- /data/data/包名：数据库，文件等等，只有当前的app可以访问
- sd卡的目录
- android:authorities=""：设置provider的域名
- android:exported="true"：设置可以输出内容
##onCreate方法
- 打开数据库
- contentProvider创建的时候被调用
- 1，启动App的时候会被创建 2，当有contentResolver的时候会被创建
- 如果返回的是true，那么之后又contentResolver访问的时候就不会执行onCreate方法
##实现crud方法

##URI
- 统一资源标识符
- 是链接contentProvider和contentResolver之间的纽带
- 由三个部分组成:格式、主机和路径
- Uri.parse:将字符串转换成uri
- ContentUris.withAppendedId(uri,id)：将id放入到uri中
-  ContentUris.parseId(uriBack);解析ur中的id

##有多张表的时候的区分
- 使用urimarch进行注册

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //注册uri
        uriMatcher.addURI("www.baidugeek.com","stu",1);
        uriMatcher.addURI("www.baidugeek.com","teacher",2);
- 在接受到不同uri地址的时候进行匹配

        int type = uriMatcher.match(uri);
        long id = 0;
        switch (type){
            case 1:
                id = StuDao.insert(db,values);
                break;
            case 2:
                 id = TeacherDao.insert(db,values);
                break;
        }
        return  ContentUris.withAppendedId(uri,id);

#ContentObserver
- 观察者

     	new ContentObserver(handler) {
                    @Override
                    public void onChange(boolean selfChange) {
                        super.onChange(selfChange);
                        handler.sendEmptyMessage(0);
                    }
                }
- provider被观察

           getContext().getContentResolver().notifyChange(uri,null);

- 在Activity中调用观察者

      	//注册一个观察者
       	getContentResolver().registerContentObserver(
                uri,
                true,
              observer
        );
- 解除注册

    	getContentResolver().unregisterContentObserver(observer);

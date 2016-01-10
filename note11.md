#4SQLite数据库事务

- SqliteDatabase:小型关系数据库,被广泛的应用在嵌入式和手机中作为存储工具,并不是android可有的,ios一样的用sqlitedatabase;是第三方的数据库
- 数据的创建数据库,创建表,CRUD
- 第一种创建数据库的方式直接通过openOrCreateDatabase
- 第二种是通过SqliteOpenHelper进行创建
- 使用面向对象的方式创建数据库,使得程序员可以不用编写SQL语句
- 使用完数据库和Cursor的时候都需要关闭
##数据库的事务操作
- 打开事务

   		 db.beginTransaction();;
- 设置事务操作成功

          db.setTransactionSuccessful();
- 结束事务

      	db.endTransaction();
> 事务一旦打开之后,必须要执行了设置事务成功才可以被提交
-　事务的面试:现在要插入10000条数据,如何提高插入的效率 -> 打开事务

#电子词典
- 如何将数据库打开
- res/raw:用于存放二进制文件的目录,例如视频,音频,数据库
- 安卓app所有的代码和资源都会被打包到apk文件中,apk文件再安装到手机中
- 将数据库拷贝到/data/data目录下进行打开

            //文件夹不存在
        File folder = new File(dbPath);
        if (!folder.exists()){
            //如果文件夹不存在的话，要先创建文件夹
            folder.mkdir();
        }

        //首先要将数据库拷贝到手机中
        File dbFile = new File(dbPath,dbName);
        //文件不存在
        if(!dbFile.exists()){
            //创建文件
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //使用流操作
            //从raw目录打开文件的流
            InputStream is = getResources().openRawResource(R.raw.dictionary);
            try {
                OutputStream os = new FileOutputStream(dbFile);

                byte[] buffer = new byte[1024];
                int len = -1;
                while ( (len = is.read(buffer)) != -1){
                    os.write(buffer,0,len);
                }
                os.close();
                is.close();
                Log.d("dicitonary","拷贝数据库成功");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("dicitonary","创建失败");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("dicitonary", "创建失败");
            }
        }
- 数据库可以存放在手机中,也可以存放在sdcard目录下
- 如何打开Sdcard目录下的文件


> this.openOrCreateDatabase(name)是打开/data/data/包名/databases/下的数据库
> SQLiteDatabase.openOrCreateDatabase(path)是打开path目录下的数据库
- 实现AutoCompleteTextView变化的监听事件

     actv.addTextChangedListener();
- 通过模糊查询找到Cursor

        Cursor cursor = db.rawQuery(
                "select english as _id from t_words where english like ?",
                new String[]
                        {s.toString() + "%"});

##CursorAdapter
- 可以显示Cursor数据的适配器
- newView:创建视图
- bindView:将数据放入到视图中
- 传入适配器的Cursor数据中,必须要有一个_id的字段,CursorAdapter才可以正常工程

            //创建适配器
        CursorAdapter adapter = new CursorAdapter(this,cursor,true) {
            
            @Override
            public CharSequence convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndex("_id"));
            }

            
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                 View view =  LayoutInflater.from(context).inflate(R.layout.word_list_item,null);
                return view;
            }

           
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView tv = (TextView) view;
                tv.setText(cursor.getString(cursor.getColumnIndex("_id")));
            }
        };



begin:开始
Transaction:事务
Date:日期
Data:数据
picker:取

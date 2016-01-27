#5Xml,json
#xml和json
##在android中解析xml
- 获取到xml的解析器

     	XmlPullParser xmlPullParser = Xml.newPullParser();
- 通过解析器,读取xml的流

       	InputStream is = getAssets().open("person.xml");
            xmlPullParser.setInput(is,"utf-8");
- 遍历xml中的标签从中获取相关的数据

           int type = xmlPullParser.getEventType();
            Person person = null;
            List<Person> list = new ArrayList<Person>();
            while (type != XmlPullParser.END_DOCUMENT){
                //判断type是什么
                switch (type){
                    case XmlPullParser.START_TAG:
                        //判断当前标签的名字
                        String tagName = xmlPullParser.getName();
                        if(tagName.equals("person")){
                            //创建出一个person
                            person = new Person();
                            //获取person的id
                            String str= xmlPullParser.getAttributeValue(0);
                            person.setId(Integer.parseInt(str));
                        }else if(tagName.equals("name")){
                            //移动到下一个tag
                            xmlPullParser.next();
                            //获取数值
                            String name = xmlPullParser.getText();
                            person.setName(name);
                        }else if (tagName.equals("age")){
                            //移动到下一个tag
                            xmlPullParser.next();
                            //获取数值
                            String age = xmlPullParser.getText();
                            person.setAge(Integer.parseInt(age));
                        }

                        break;
                    case XmlPullParser.END_TAG://标签结束的时候
                        tagName = xmlPullParser.getName();
                        if (tagName.equals("person")){
                            list.add(person);
                        }
                        break;
                }
                //获取下一个表签
                type = xmlPullParser.next();
            }
> 1,什么时候创建实体类

> 2,什么时候赋值

> 3,什么时候将实体类放入到集合中

- 用于存储数据的
###使用xml快速存储数据
- sharedPreference本质就是在data/data/app包名的目录下创建了一个xml文件快速的存储信息
- 打开读取内容

     	SharedPreferences sp = getSharedPreferences("data1",MODE_PRIVATE);
        String reslut = sp.getString("qq","");	
- 快速的存储数据

        //将数据存储到文件
        SharedPreferences sp = getSharedPreferences(
                "data1",
                this.MODE_PRIVATE//私有模式
        );
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //将数据写入
        editor.putString("qq",input);
        //提交数据
        editor.apply();


       

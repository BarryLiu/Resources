#intent
- 信使:用于组件和组件之间传递信息的媒介
##实现Activity之间的跳转
###创建一个新的Activity
- 创建一个继承Activity的类
- 在Androidmenifest.xml中注册
- 在res/layout中创建界面
- 在onCreate中使用setContentView方法调用界面
###使用startActivity进行跳转

        //创建一个信使
        Intent intent = new Intent();
        //指定跳转到哪个Activity
        intent.setClass(this,OtherActivity.class);
        //跳转Activity
        startActivity(intent);
##Activity之间的数值传递
###第一种方法
- 直接传递

     	intent.putExtra("name",name);

- 获取数值

    	Intent intent  = getIntent();
    	String name = intent.getStringExtra("name");
###第二种使用bundle
- 将数值放入到bundle中

        Bundle bundle = new Bundle();
        bundle.putString("et_name",name);
        bundle.putString("et_tel",tel);

        //将bundle放入到intent中
        intent.putExtras(bundle);
- 从bundle中取出数值

         //第二种取值方式
        Bundle bundle = intent.getExtras();
        name = bundle.getString("et_name");
        tel  = bundle.getString("et_tel");

###第三种方式,直接传递对象(需要别序列化)
- Activity之间是不可以传递引用的,只可以传递数值
- public class Student implements Serializable 
###第四种方式使用Parcelable
- 安卓中特有的传递数值的方式
- 使用的时候有两个步骤:第一个是将数据放入到Parcel中;第二是将数据从Parcel中取出来
- 写入数据
	
        public void writeToParcel(Parcel dest, int flags) {
        //将数据写入到dest中
        dest.writeString(name);//写入名字
        dest.writeString(tel);//写入tel
        dest.writeInt(age);
    }
- 获取数据:获取数据的顺序要和读取数据的顺序保持一致

       	protected Teacher(Parcel in) {
        //需要和写入的顺序一致
        name = in.readString();
        tel = in.readString();
        age = in.readInt();

   		 }

    	//创建一个构造器,获取数据
    	public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }};
- 熟练使用IDE的代码自动生成

##Activity的数值的返回
- 在第一个Activity中使用:

    	 startActivityForResult(intent,100);
- 在第二个Activity中调用:

         setResult(101,backIntent);
- 在第一个Activity中回去返回值

        protected void onActivityResult(
            int requestCode,//请求的id
            int resultCode,//返回的id
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Teacher t = data.getParcelableExtra("backteacher");

        et_name.setText(t.getName());
        et_tel.setText(t.getTel());
    }
##Intent的隐式调用
- 在手机中的任意的app中Activity都可以通过别名被调用

        <activity android:name=".SuanmingActivity">
            <intent-filter>
                <!--设置别名 -->
                <action
                    android:name="com.geek.suanming"
                    ></action>
                <category
                    android:name="android.intent.category.DEFAULT"
                    ></category>
            </intent-filter>
        </activity>

#新单词
- intent:信使
- bundle:一捆

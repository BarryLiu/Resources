#安卓工程目录结构
- src:代码存放路径
- bin:编译之后,文件存放的位置
- res:存放资源的文件(图片,声音等等)
- AndroidMenifest.xml:用于安卓注册和配置组件的地方
- libs:存放第三方jar的地方
- : 自定生成的代码存放区域
- asset:资源存放区域

##了解安卓工程目录之间的联系
- Activity是安卓的窗口界面组件
- 点单(创建一个工程)
- 有个厨师(创建一个继承Activity的类)
- 注册厨师到系统(在Androidmenifest.xml中注册Activity)
- 构思食材(在res/layout/创建一个xml文件)
- 找到老王要食材的单子(在gen目录下会自动生成一个R.java文件,控件的地址都在里面)
- 将食材搬到厨房来(使用setContentView显示layout中的视图)

#开发安卓程序的一般流程
- 设计界面
- 实现逻辑

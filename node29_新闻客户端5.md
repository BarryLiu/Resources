#安卓项目
- 项目的类型:资讯类,生活类,理财类,商城类,招聘类等等
##资讯类项目的构架
###开机的Logo
- Runable是Thread的一部分,必须依赖一个线程运行
- onDestory方法是在Activity退出的时候调用

        protected void onDestroy() {
        super.onDestroy();
        //将handler中的runnable移除
        handler.removeCallbacks(r);}
- 安卓的程序的入口时Application,然后再启动Activity;可以通过主题修改Application的背景

###向导界面(第一次才有的)
- 内部类一般使用static,为了防止内存泄漏;非static类为默认持有Activity的引用,当Activity推出,而这个类产生的对象没有被释放的时候,会导致内存泄漏
- 整体的滑动界面使用Viewpager+Fragment实现
- 在向导界面结束之后,通过SharedPreferences将进入的状态进行保持
####向导的指引点如何实现?
- Indicator:指引
- View:
- Mode:模式
- 创建一个自定义View实现
- 通过使用viewpagerindicator第三方控件实现

###主界面
####顶部的Toolbar
- 创建一个相对布局,顺寻左右中
####选项卡部分
- FragmentTabhost的使用
- 选项内容部分,创建一个FrameLayout
- FragmentTabhost标签是选项卡部分,width要是match_parent
####导航栏
- RadioGroup和HorizontalScrollView实现滑动的选项卡
- 灵活使用shape,selector和layer-list

- ellipsize:折叠
- Adjust:适应
- View:视图
- Bounds:边框

####正文部分
#####滑动广告
- 不能直接使用ViewPager,因为高度是位置的
- 要重写onMeasure方法,测量出Viewpager的高度

#####List内容
- 使用listView.addHeadView();

####侧滑部分
- 使用SlideMenu


Splash:闪屏
Delayed:延时
attach:链接到
Shadow:影子

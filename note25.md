#3项目-新闻客户端,新闻客户端1

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


###主界面
####顶部的Toolbar
####导航栏
####正文部分
#####滑动广告
#####List内容
####选项卡部分
####策划部分


Splash:闪屏
Delayed:延时

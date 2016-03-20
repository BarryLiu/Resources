#46_1Activity生命周期和启动模式
#项目开发流程
- 项目需求
- 项目大体界面（估算项目时间）
- 项目框架:界面，网络，文件管理，图片，路径，安全，第三方包
- 项目细节代码:完成界面开发->完成服务器端的业务逻辑和数据库->发请求获取数据->显示数据，完成相关的业务逻辑
- 植入第三方的企业技术:第三方登录，第三方数据，短信验证，推送技术，地图
- 打包，混淆（gradle），上线，配置管理（svn，git）
- 移植不同机型，上线不同的平台

#Activity
- 窗口，界面，交互
#生命周期
- 开始，运行，结束
- 四个状态切换的过程中触发的不同的方法
- 七大方法
##启动程序的状态:onCreate->onStart->onResume
- onCreate：Activity被创建的时候调用，主要功能创建界面
- onStart：界面正在被用户看见
- onResume:程序获取焦点，可以和用户进行交互（开启动画，打开摄像头）
##失去焦点,最小化:onPuase->onStop
- onPuase:失去焦点的时候,来电话的时候（关闭动画，关闭摄像头）
- onStop:不再看见界面的时候

##重新获取焦点:onRestart->onStart->onResume
- onRestart:用户重新看到界面的时候
##退出Activity:onPuase->onStop->onDestory
- onDestory：程序关闭的时候，释放内存，关闭正在运行的线程

#任务栈
- 安卓系统中有栈，所有Activity启动以后都会进入到栈中，先进后出

#启动模式
- standard：任何情况下都会创建一个新的Activity
- singleTop:当前Activity在栈的顶部的时候，如果再次调用不会被创建新的Activity
- singleTask:在当前Activity的上面有其他的Activity，调用的时候全部干掉
- singleInstance:真正的单例

#aidl与ndk
##Aidl
- Android Interface Define Language：用于运程调用
###被调用者
- 创建一个Aidl，开发被调用方法的接口
- make会生成对应的远程调用的代码
- 在Service中实现远程调用的方法
- 在Service的onBind方法中返回
###调用者
- 使用被调用者创建的aidl
- 在Service中绑定接口
- 使用接口中开发的方法
##aidl的使用场景
- 获取程序的缓存大小
- 拦截电话
- aidl和反射配合使用，调用framework层中隐藏的代码
#NDK
- Native Development Kit:源生编程，基于linux的C\C++编程
##NDK如何使用
- 在java中创建一个native的方法
- 使用javah将native方法编译成对应的.h头文件
- 在.c文件中实现方法
- 在gradle中对NDK的编译尽心配置

	ndk{
		libname: "aaa"
		filter:"x86"
	}
- make会将.h和.c文件编译成.so文件
- 在java代码中，首先通过System.loadlibrary是对.so文件进行加载
- 直接使用native的方法

##NDK的使用
- 算法：OpenCV是专业的图像和人脸识别的开源算法
- 游戏: cocos2d-x引擎
- 安全：将一些安全算法使用ndk编写
- 影视: 流媒体编程Vitamio，FFMGE编程

#CMS
##创业
- 盈利:公司的目的
- CRM:为了更有效的挣钱
- 数据库和后台：业务的核心
- 二次开发：在原有的系统上做开发
- 前台开发：HTML+js+PS
- 移动客户端:界面，网络，企业技术；二次开发
- IOS开发

#打包
- 安卓程序都必须被打包成apk文件才可以发布
- 要生成签名，才可以打包
##签名文件
- 用于识别Apk身份的

#破解
- 可以通过一些工具将apk文件进行反编译，修改代码
#混淆
- 将代码的名字搞乱，让人无法识别
#gradle
- 基于Maven和ant的构建工具
- 用于工程的配置和打包，依赖
#多平台打包
- 设置productFlavors，实现多个平台的打包



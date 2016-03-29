#Lotus
- domino
- java 
- C++\C
- js
- C#
- 打包，部署
#java
- 数据库
- 业务逻辑
#hmtl5
- web界面（前台开发工程师）
- Mobile Jquery
#android
- 四大组件
- 界面
- 网络
- 第三方技术
#实施
- linux
- 数据库
#ios
- xcode

#构架师
- 中间件
- 设计模式

#课前复习
##定位
- LocationClient开始定位，获取当前的坐标(wifi，基站，GPS)

##地图
- MapView和AMap
- 移动摄像头
- 地理Search
- marker可以在地图上显示图标

##路径
- Router：通过查询三种方式的路劲获取结果
- Overlay:图层，将查询的结果在图层中显示

##地图应用
- 定位：获取当前的所在城市和手机的经度和维度
- 可以在地图上显示周边情况的信息
- 实现路径功能

#FrameWork
##应用层
- 界面
- 四大组件
- 网络
##FrameWork层
- 各种服务存在层
###对安卓的各种服务进行操作
####闹钟
- AlarmManager
- RTC_WAKEUP：定时操作

    	public void bomb(View view) {
        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        	alarmManager.setRepeating(
               	AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+2000,//2秒钟之后触发
                50,//间隔多长时间触发
                pendingIntent
            );
    	}

    	public void save(View view) {
        alarmManager.cancel(pendingIntent);
    	}
##
- 连接应用层和Linux之间的纽带
##虚拟机和library
##Linux层
- 直接和硬件打交道
- 安卓真正的操作系统
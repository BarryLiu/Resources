---
layout: post
title:  "note52_NotificationWidget"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
#Notification
- 通知
- PendingIntent：延时的intent,在intent外部封装一层，让其他控件触发释放

##Notification应用
- 下载的进度
- 音乐播放的进度

##自定义View
- RemoteViews

         RemoteViews remoteViews = new RemoteViews(
             getPackageName(),//包名
             R.layout.notification_views //布局
     );
- 必须要制定smallicon，否则不出来

         Notification notification =
             new Builder(this).setContent(remoteViews)
             .setSmallIcon(R.mipmap.ic_launcher)
             .setTicker("正在播放音乐~~~~~")
                     .build();
- 通过Broadcast达到notification和其他组件之间的同行

         //设置播放广播
       remoteViews.setOnClickPendingIntent(
               R.id.btn_play,
               PendingIntent.getBroadcast(this, 1, (new Intent("com.geek.notifyb1")).putExtra("type",2), PendingIntent.FLAG_UPDATE_CURRENT)
       );
> requestCode：必须不同
>
> flag:PendingIntent.FLAG_UPDATE_CURRENT


##Widget的开发
- 可以使用AS中直接创建一个widget
- widget的原理就是系统发出一个广播，应用程序中实现一个广播的接收者，为当前的widget提供代码和资源

![](image1.png)


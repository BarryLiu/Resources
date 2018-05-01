---
layout: post
title:  "note51_Service"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
- eclipse（免费）：IBM 50%
- NetBean（免费）: sun公司（甲骨文）45%

- idea（收费）:goole 5% php最牛，Html5最牛


#Service
- 在后台运行的服务，没有界面

#Service的应用
- 播放音乐
- 后台下载

##Activity和Service之间的通信
- BindService通过intent进行绑定

        Intent intent = new Intent();
        intent.setClass(this,MyService.class);
        bindService(intent,
                serviceConnection,
                BIND_AUTO_CREATE //绑定的时候如果服务没有启动，那么自动创建
                );
- 在绑定过程中获取Service的引用

       	serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = ((MyService.MyBinder)service).getService();
            }
        
            @Override
            public void onServiceDisconnected(ComponentName name) {
        
            }
        };
- 在Service中要通过binder返回引用

        public IBinder onBind(Intent intent) {
        return new MyBinder();
        }
        
        public class  MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
        }

#Service的生命周期
- onCreate：
- onStartCommand：
- ondestroy：
- onbind：
- onunbind ：
> 1，onStartCommand在调用startService的时候必定调用
> 2，onUnbind在一个Activity退出的时候，如果当前Activitybind在Service，会自动调用

#后台下载
- 通过使用线程进行下载
- 考虑在没有新的任务的时候，老任务执行完毕之后才关闭服务
- 对线程进行控制
- IntentService就出来解决这些问题

##Thread和Service的区别
- 生存时间不同：虽然在Activity退出之后，两个都可以存在，但是存在的时间长度并不一样。Service要长一些，而且Service被kill以后还可以自动重启
- 控制方式不同:更换Activity之后，只能对原来的Service进行控制，而线程则失控
- Thread可以进行耗时操作，但是Service不可以进行耗时操作
- ?

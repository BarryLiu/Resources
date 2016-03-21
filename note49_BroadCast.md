#Broadcast
- 广播:app之间传递信息的，开机启动，短信拦截，监听当前网络情况，关闭所有的activity
- 广播的发送者:系统发送的，自己的发送的

        sendBroadcast(intent);

- 广播的接收者
- 热注册:在Activity内部创建一个继承广播的类；让Activity注册当前广播

     	receiver = new MyRece();
        registerReceiver(receiver,new IntentFilter("com.oox.xxx"));
> 有注册，就必须有解注册unregister

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
- 冷注册:在Androidmenifest.xml中注册，即便Activity不启动，可以单独启动一个广播

##有序广播和无序广播
- 有顺序的广播通过priority设置有限度
- 无序广播一次性大家都接受

##广播的应用
- 关闭所有的Activity
- 实现在不同app之间的通信
- 获取当前网络的状态
- ConnectivityManager可以获取当前手机的网络情况
- 如何获取动态验证码（1,实现broadcast对短信进行截取。2，使用ContentObserver和ContentProivder）

#intent和intentFilter的区别

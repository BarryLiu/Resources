#52_6Alarm��widget,Notification

#Notification
- ֪ͨ
- PendingIntent����ʱ��intent,��intent�ⲿ��װһ�㣬�������ؼ������ͷ�

##NotificationӦ��
- ���صĽ���
- ���ֲ��ŵĽ���

##�Զ���View
- RemoteViews

            RemoteViews remoteViews = new RemoteViews(
                getPackageName(),//����
                R.layout.notification_views //����
        );
- ����Ҫ�ƶ�smallicon�����򲻳���

            Notification notification =
                new Builder(this).setContent(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("���ڲ�������~~~~~")
                        .build();
- ͨ��Broadcast�ﵽnotification���������֮���ͬ��

          //���ò��Ź㲥
        remoteViews.setOnClickPendingIntent(
                R.id.btn_play,
                PendingIntent.getBroadcast(this, 1, (new Intent("com.geek.notifyb1")).putExtra("type",2), PendingIntent.FLAG_UPDATE_CURRENT)
        );
> requestCode�����벻ͬ
> 
> flag:PendingIntent.FLAG_UPDATE_CURRENT


##Widget�Ŀ���
- ����ʹ��AS��ֱ�Ӵ���һ��widget
- widget��ԭ�����ϵͳ����һ���㲥��Ӧ�ó�����ʵ��һ���㲥�Ľ����ߣ�Ϊ��ǰ��widget�ṩ�������Դ

![](image1.png)


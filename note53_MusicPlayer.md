#���ֲ�����
- Service������mediaplay�����ֽ��п���
- Activity:�Դ���ģʽ��ʾ����
- Broadcast:�齨֮�䴫����Ϣ
- Notification:��֪ͨ����ʽ��ʾ����
- AppWidget:������Сͼ��ķ�ʽ��ʾ����
- ContentProivder:��ȡӦ�ù��������

##Activity��Service֮���ͨ��
- Activityͨ����Service��ȡService�����ã�����Service
- Activity����ͨ����ͣ�ķ�����Ϣ����ȡService�ĸ���������Ӷ�ʵ��Activity�еĽ�����

##Service��Notification֮���ͬ��
- Serviceͨ����ȡNotificationManagerʵ�ֺ�Notification֮���ͨ��
- ͨ��SetContentView�Զ�����Notification�Ľ���
- ��Service�д���PendingIntent���͸�Notification,���佫PengIntent�ж�Ӧ��intent�Թ㲥����ʽ���ͳ���
- ��Service�ж�̬ע��һ��Receiver������Notification�����Ĺ㲥���Ӷ��ﵽ֪ͨ����Service�Ĺ���
##Widget��Service֮���ͨ��
- Serviceͨ��AppWidgetManager��AppWidget���и���
- ��Notificationһ����Service����һ��RemoteViews����Widget
- ��RemoteViews�и����ֿؼ����PengdingIntent��������Է��͹㲥��Service���ܹ㲥�ﵽ�����Ƶ�Ч��

![](image1.png)
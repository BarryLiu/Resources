#Broadcast
- �㲥:app֮�䴫����Ϣ�ģ������������������أ�������ǰ����������ر����е�activity
- �㲥�ķ�����:ϵͳ���͵ģ��Լ��ķ��͵�

        sendBroadcast(intent);

- �㲥�Ľ�����
- ��ע��:��Activity�ڲ�����һ���̳й㲥���ࣻ��Activityע�ᵱǰ�㲥

     	receiver = new MyRece();
        registerReceiver(receiver,new IntentFilter("com.oox.xxx"));
> ��ע�ᣬ�ͱ����н�ע��unregister

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
- ��ע��:��Androidmenifest.xml��ע�ᣬ����Activity�����������Ե�������һ���㲥

##����㲥������㲥
- ��˳��Ĺ㲥ͨ��priority�������޶�
- ����㲥һ���Դ�Ҷ�����

##�㲥��Ӧ��
- �ر����е�Activity
- ʵ���ڲ�ͬapp֮���ͨ��
- ��ȡ��ǰ�����״̬
- ConnectivityManager���Ի�ȡ��ǰ�ֻ����������
- ��λ�ȡ��̬��֤�루1,ʵ��broadcast�Զ��Ž��н�ȡ��2��ʹ��ContentObserver��ContentProivder��

#intent��intentFilter������

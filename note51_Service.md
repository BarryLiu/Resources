
#IDE
- eclipse����ѣ���IBM 50%
- NetBean����ѣ�: sun��˾���׹��ģ�45%

- idea���շѣ�:goole 5% php��ţ��Html5��ţ


#Service
- �ں�̨���еķ���û�н���

#Service��Ӧ��
- ��������
- ��̨����

##Activity��Service֮���ͨ��
- BindServiceͨ��intent���а�

        Intent intent = new Intent();
        intent.setClass(this,MyService.class);
        bindService(intent,
                serviceConnection,
                BIND_AUTO_CREATE //�󶨵�ʱ���������û����������ô�Զ�����
                );
- �ڰ󶨹����л�ȡService������

       	serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = ((MyService.MyBinder)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
- ��Service��Ҫͨ��binder��������

        public IBinder onBind(Intent intent) {
        return new MyBinder();
    	}

    	public class  MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    	}

#Service����������
- onCreate��
- onStartCommand��
- ondestroy��
- onbind��
- onunbind ��
> 1��onStartCommand�ڵ���startService��ʱ��ض�����
> 2��onUnbind��һ��Activity�˳���ʱ�������ǰActivitybind��Service�����Զ�����

#��̨����
- ͨ��ʹ���߳̽�������
- ������û���µ������ʱ��������ִ�����֮��Źرշ���
- ���߳̽��п���
- IntentService�ͳ��������Щ����

##Thread��Service������
- ����ʱ�䲻ͬ����Ȼ��Activity�˳�֮�����������Դ��ڣ����Ǵ��ڵ�ʱ�䳤�Ȳ���һ����ServiceҪ��һЩ������Service��kill�Ժ󻹿����Զ�����
- ���Ʒ�ʽ��ͬ:����Activity֮��ֻ�ܶ�ԭ����Service���п��ƣ����߳���ʧ��
- Thread���Խ��к�ʱ����������Service�����Խ��к�ʱ����
- 

#Lotus
- domino
- java 
- C++\C
- js
- C#
- ���������
#java
- ���ݿ�
- ҵ���߼�
#hmtl5
- web���棨ǰ̨��������ʦ��
- Mobile Jquery
#android
- �Ĵ����
- ����
- ����
- ����������
#ʵʩ
- linux
- ���ݿ�
#ios
- xcode

#����ʦ
- �м��
- ���ģʽ

#��ǰ��ϰ
##��λ
- LocationClient��ʼ��λ����ȡ��ǰ������(wifi����վ��GPS)

##��ͼ
- MapView��AMap
- �ƶ�����ͷ
- ����Search
- marker�����ڵ�ͼ����ʾͼ��

##·��
- Router��ͨ����ѯ���ַ�ʽ��·����ȡ���
- Overlay:ͼ�㣬����ѯ�Ľ����ͼ������ʾ

##��ͼӦ��
- ��λ����ȡ��ǰ�����ڳ��к��ֻ��ľ��Ⱥ�ά��
- �����ڵ�ͼ����ʾ�ܱ��������Ϣ
- ʵ��·������

#FrameWork
##Ӧ�ò�
- ����
- �Ĵ����
- ����
##FrameWork��
- ���ַ�����ڲ�
###�԰�׿�ĸ��ַ�����в���
####����
- AlarmManager
- RTC_WAKEUP����ʱ����

    	public void bomb(View view) {
        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        	alarmManager.setRepeating(
               	AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+2000,//2����֮�󴥷�
                50,//����೤ʱ�䴥��
                pendingIntent
            );
    	}

    	public void save(View view) {
        alarmManager.cancel(pendingIntent);
    	}
##
- ����Ӧ�ò��Linux֮���Ŧ��
##�������library
##Linux��
- ֱ�Ӻ�Ӳ���򽻵�
- ��׿�����Ĳ���ϵͳ
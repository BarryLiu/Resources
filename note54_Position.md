#54_8��λ�͵�ͼ
#��λ
- ��վ��λ:�����ֻ���sim��ȷ���ֻ����ڵ�λ�ã�ͨ��TelephoneManager��ȡ��ǰ���ֻ����ڵĻ�վ����Ϣ������Ϣ���͵��ۺ����ݻ�ȡ�����λ��
- wifi��λ:�����ֻ������wifi��ȡwifi��mac��ַ���Ӻ��ж��ֻ����ڵ�λ�ã�ʹ��WifiManager
- GPS��λ:���Ƕ�λ��ʹ���ֻ��Դ���gps���ܽ��ж�λ��ʹ��LocationManager���ж�λ����ȡ��ǰ�ֻ��ľ��ȣ�γ�Ⱥͺ���

#��ͼ
- �ٶȺ͸ߵµ�ͼ
- ����ǩ��:����������ȷ��һ��appΨһ��ݱ�ʶ֮һ������ǩ��Ҳ������ȷ��app��ݵı�ʶ
- Ĭ������£�ideʹ�õ�ǰ������c:user/�������/.android/debug.keystore
##ǰ��׼������
- ȥ��̨����key
- ����jar����libs��
- ��.so��ص��ļ�������jniLibsĿ¼��
- ��Androidmenifest.xml������Ȩ�޺�appkey
##��ʾ��ͼ
- ��xml������һ��mapView,���ҳ�ʼ��

        mapView  = (MapView) findViewById(R.id.mapView);
        //��ʼ��
        mapView.onCreate(savedInstanceState);// �˷���������д

#����
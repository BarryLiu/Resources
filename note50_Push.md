#broadcast
- ����app��ϵͳ֮�䴫����Ϣ
- ������
- ���ܷ�
- ��̬ע��:��Activity��ע�ᣬ����Ҫ��Activity�󶨣������޸�Activity�Ľ���
- ��̬ע��:��Androidmenifest.xml�н���ע��,û��Activity��ʱ��Ҳ��������һ��BroadcastReceiver
- ����㲥������㲥
- �ر����е�Activity
- ��ȡ��ǰ����״��

#���ͼ���
- ����pull�����ͻ�����������Ϣ����������
- �ƣ�push��������������������Ϣ���ͻ���
- ���͹��ܣ�΢������Ϣ����ʾ�������ŵ�ʱ�������������Ϣ
- ��ʱ����:΢�ţ�QQ������
- ʵ��Ч��:��ʡ���������粻�ȶ�
##����ʵ��
- xmppЭ��:ʹ�ÿ�Դ�ķ�����openFire��ͨ��asmarkʵ���û��ĵ�½ע�������
- webSocket:html5�����µ�˫��ͨ�й淶
- ���������ͼ���:��������

###��������
- http://jpush.cn
- ȥ��̨����һ��Ӧ��
- ����صİ���.so�ļ�������libsĿ¼��,androidstudio����Ҫ��gradle������·��

    	android {
	    	compileSdkVersion 23
	    	buildToolsVersion "23.0.2"
	
	    	defaultConfig {
	        applicationId "com.geek.pushA3test"
	        minSdkVersion 15
	        targetSdkVersion 23
	        versionCode 1
	        versionName "1.0"
	    	}
	    	buildTypes {
	        release {
	            minifyEnabled false
	            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
	        }
	    	}
	
	    	sourceSets{
	        main {
	            jniLibs.srcDirs = ['libs'] // <-- Set your folder here!
	        }
	    	}
		}
- ��androidmenifest.xmlҪ���úð�����AppKey
- ����һ���̳�Application���࣬������ʵ�ֳ�ʼ�����ǵ�Ҫע�����application
- ��Activity������õ�onResume�����е���Jpush.resume()

- ����һ���Լ���BroadcastReceiver�����ڽ�����Ϣ���Ҵ���,���Դ�Exmple��ȡ��ͬ��Ϣʱ��������

- ����Ϣ�з���key-values���ڿͻ��˻�ȡ��Ϣ����ת
- ����:����
- ��ʱ����:���ţ�����

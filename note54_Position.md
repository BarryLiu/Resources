#54_8定位和地图
#定位
- 基站定位:利用手机的sim卡确定手机所在的位置，通过TelephoneManager获取当前的手机所在的基站的信息，将信息发送到聚合数据获取具体的位置
- wifi定位:利用手机连入的wifi获取wifi的mac地址，从何判定手机所在的位置，使用WifiManager
- GPS定位:卫星定位，使用手机自带的gps功能进行定位，使用LocationManager进行定位，获取当前手机的经度，纬度和海拔

#地图
- 百度和高德地图
- 数字签名:包名是用于确定一个app唯一身份标识之一；数字签名也是用于确定app身份的标识
- 默认情况下，ide使用的前面是在c:user/你的名字/.android/debug.keystore
##前期准备工作
- 去后台申请key
- 拷贝jar包到libs中
- 将.so相关的文件拷贝到jniLibs目录下
- 在Androidmenifest.xml中设置权限和appkey
##显示地图
- 在xml中设置一个mapView,并且初始化

        mapView  = (MapView) findViewById(R.id.mapView);
        //初始化
        mapView.onCreate(savedInstanceState);// 此方法必须重写

#导航
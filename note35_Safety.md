#35_6安卓网络通信安全机制
#网络安全
- 加密
- 身份鉴别
- 数据的完整性
- 数据的不可以否认性

##加密
- 明文:直接将消息发送出去
- 密文:将消息按照一定的算法加密之后发送出去
- 算法:将明文转换成密文
- 密钥:将密文变成明文的关键钥匙

 

###MD5算法
- 唯一性
- 不可逆性
- 使用场景:密码保护,文件长度完整性

###AES算法
- 可逆性
- 对称加密
- 使用场景:用于加密消息报文

####BASE64
- 用于将二进制文件转换成字符流在网络中传递

###RSA算法
- 非对称性算法

###Https
- 一种加密http

####使用Https
- 在Tomcat的Server.xml中配置Https的属性

    	<Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol"
			maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
			clientAuth="true" sslProtocol="TLS"
			/> 
- 在生成服务器端的证书(私钥)

    	keytool -validity 36500 -genkey -v -alias server -keyalg RSA -keystore server.keystore

#####让服务器端信任客户端
- 在客户端生成私钥证书

		keytool -validity 36500 -genkeypair -v -alias client -keyalg RSA -storetype PKCS12 -keystore client.p12
- 通过私钥到处客户端的公钥证书
	
    	keytool -export -v -alias client -keystore client.p12 -storetype PKCS12 -storepass 123456 -rfc -file client.cer

- 将公钥证书发送到服务器端,让服务器端的私钥库信任客户端

    	keytool -import -v -alias client -file client.cer -keystore server.keystore -storepass 123456

####让客户端信任服务器端
- 在服务器端导出公钥证书发送到客户端

    	keytool -export -v -alias server -keystore server.keystore -storepass 123456 -rfc -file server.cer

- 在客户端将服务器端的公钥证书添加私钥的信任库中

    	keytool -keystore server.keystore -export -alias server -file server.cer
- 在安卓中使用HttpsUrlConnection

     	HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //设置信任的站点名字
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            //加载SSL层
            conn.setSSLSocketFactory(HttpsUrlSSLHelper.getSSLContext(this).getSocketFactory());

- 公钥用来加密,可以在网络中传递
- 私钥用于解秘
- android不识别JKS,需要转换成BKS
- Https就是加密过的Http,多一层SSL
- 使用HttpsUrlConnection的时候必须加载SSL层




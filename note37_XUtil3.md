#Xutil3
- 3以前的版本底层都是使用httpclient实现的,所以废弃了。3以后底层都换成了httpUrlConnection

- DbManager:实现了数据库的ORM
- HttpManager:实现网络模块
- ImageManager:图片模块
- ViewInjector:控件的IOC模型,实现了注解编程

##获取网络JSON

           String jsonUrl = "http://192.168.1.116:8080/ServerA9/GetJsonServlet";
        //发送请求
        RequestParams requestParams = new RequestParams(jsonUrl);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, result,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


##网络获取图片

     x.image().bind(iv,imageUrl);
##下载和上传
- 下载

          String downPath = "http://192.168.1.116:8080/ServerA9/images/1.jpg";
        RequestParams params = new RequestParams(downPath);
        //自动继续
        params.setAutoResume(true);
        //自定更替名字
        params.setAutoRename(true);
        //设置文件的路径
        params.setSaveFilePath(Environment.getExternalStorageDirectory().getPath()+"/xxx1.jpg");
        x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this,"下载"+result.getPath()+"文件中",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
- 上传

        String upLoadUrl = "";
        RequestParams params = new RequestParams(upLoadUrl);
        params.addBodyParameter("xxx.jpg",Environment.getDataDirectory()+"/ooxx.jpg");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    


##注册和登陆
##Https的SSL


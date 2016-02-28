#下载
- 普通下载的实现

                URL url = new URL("http://192.168.1.116:8080/ServerA9/apk/zhihui.apk");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                int len = -1;
                byte[] buffer = new byte[1024];

                File file = new File(Environment.getExternalStorageDirectory(),"ooxx.apk");
                if (!file.exists()){
                    file.createNewFile();
                }

                OutputStream os = new FileOutputStream(file);
                while (  (len = is.read(buffer)) != -1){
                    os.write(buffer,0,len);
                }
                os.flush();
                os.close();
                is.close();
- 使用intent进行自动安装

            //安装apk
            Intent intent = new Intent();
            //指定动作
            intent.setAction(Intent.ACTION_VIEW);
            //指定数据
            File file = new File(Environment.getExternalStorageDirectory(),"ooxx.apk");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            startActivity(intent);
- 当网络不问题的时候,不要重新下载,可以继续下载

##实现进度条
- progressBar
- setMax
- setProgress

##断点继续下载
- 将之前下载的位置保存起来
- 继续下载的时候可以从之前的位置进行下载

	  	conn.setRequestProperty("Range", "bytes=" + downLength + "-" + downMax);
- 将下载的内容继续保存在之前的文件的后面
	
                 RandomAccessFile raf = new RandomAccessFile(file,"rw");
                //下载之前移动到上次下载的位置
                raf.seek(downLength);

##版本更新
- 发送请求到服务器端获取最新的版本号
- 获取当前软件的版本号,进行比较

      	int versionCode = getPackageManager()
                        .getPackageInfo(getPackageName(), 0)
                        .versionCode;
- eclipse在AndroidMenifest.xml中设置版本大小
- AndroidStudio在gradle文件中配置版本大小

#上传
- Get:获取数据
- Post:提交,上传
- 在conn中设置requestMode为POST
- 将setDoOutput(true)可以向外发送内容
- post请求必须要调用getReposeCode才会被发送出去
- 知道tomcat的路径

    	this.getServletContext().getRealPath("ooxx.apk")







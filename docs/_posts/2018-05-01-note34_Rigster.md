---
layout: post
title:  "note34-Rigster"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
#注册
- 将用户名和密码放入到JSON来发送

#登陆
- 登陆成功之后访问所有的界面,都需要再次登陆么?
- 需要在客户端保存一个全局的变量来存储用户信息


- Session:在服务器端用于保存用户信息内容的
- Cookie:用于识别客户端身份的
##如何使用Cookie
- 在第一次登陆的时候需要将Cookie保存起来
  ​	
      Map<String,List<String>> keys = conn.getHeaderFields();
                if (keys.containsKey("Set-Cookie")){
                    cookie = conn.getHeaderField("Set-Cookie");
                }

- 在以后访问的时候需要在http头中设置Cookie让服务器端识别客户端的身份

       //发送请求之前带上cookie
            conn.setRequestProperty("Cookie",cookie);

##新闻客户端四个网络功能
- 新闻数据要以json方式从服务器端传递到客户端
- 在ListView适配器中获取网络的图片
- 每次进入的时候检查是否存在最新版本,下载更新
- 注册和登陆

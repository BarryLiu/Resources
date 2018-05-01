---
layout: post
title:  "note56_AidlNdk"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
- Android Interface Define Langage
##被调用者
- 开放一个可以被调用的接口，创建aidl的文件
- 在Service中实现接口
- 在onBinder方法中返回实现的接口
##调用者
- 将被调用者的接口文件放入到自己的项目中
- 在BindService时候的时候，通过接口获取远程调用的对象
- 通过接口开发的方法，进行调用

##Aidl的应用
- 获取app的缓存大小
- 清除缓存大小
- 挂断电话

#NDK（Native Development Kit）
- 高级算法（美图秀秀，人脸识别，音频编码）
- 游戏，Cocos2d-x开发游戏的，具备跨平台的特点，性能又好
- 安全
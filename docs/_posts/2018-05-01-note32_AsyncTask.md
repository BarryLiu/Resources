---
layout: post
title:  "note32_AsyncTask"
date:   2014-11-17 13:31:01 +0800
categories: android
tag: android
---

* content
{:toc}
- 1,重复下载图片的问题
> 使用内存缓存

- 2,图片乱跳的问题
> 由于数据遗留导致

- 3,图片错位的问题
> 使用tag确保必定是当前图片

- 4,退出之后,又需要重新下载
> 使用文件缓存

##image的三级缓存
- 1,从内存缓存中获取
- 2,从文件缓存中获取
- 3,从网络中获取

#其他问题
##5,OOM(Out Of Memory)
- 内存缓存中不断的有图片加入
> 在一定的条件下释放内存,内存不足的时候
- 软引用:在java默认情况下,被引用指向的对象是不会被自动释放;软引用会在内存不足的时候自动释放
- 问题:软件引用释放内存的情况是不确定的
##LruCeche
- 最终解决方案使用:LruCeche(最近使用最少的内存)
- 1,可以设置缓存的大小
- 2,通过算法去释放使用最少次数的内存
- 3,使用的时候需要重写sizeof方法用于计算每一个缓存中对象的大小

     protected int sizeOf(String key, Drawable value) {
            BitmapDrawable bd = (BitmapDrawable) value;
            return bd.getBitmap().getRowBytes() * bd.getBitmap().getHeight();
        }

##Bitmap和Drawable的问题
- 优先使用Drawable,来绘制图片


###导致内存溢出的原因
- Cursor没有关闭
- Activity的引用被其他类占用会导致内存溢出->使用context或者接口
- Bitmap的内存占用->使用缩小的图片

##6,线程无法被控制
new:
Fixed:固定
ThreadPool:线程池
###创建一个线程池对发送的请求线程进行控制

    public class ThreadManaager {
    //创建一个线程管理器
    ExecutorService executorService;
    private static ThreadManaager instance;
    private  ThreadManaager(){
        //获取当前设备的cup的数量
        int num = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(1);
    
    }
    public static ThreadManaager getInstance(){
        if(instance == null ){
            instance = new ThreadManaager();
        }
        return  instance;
    }
    
    public void execute(Runnable r){
        executorService.execute(r);
    }}

##AsyncTask
- handler+Runnable+executorService的综合体
###分开使用
- 创建一个线程池
- 创建一个Runnable,在Runnable的run方法中实现操作
- 执行Runnable
- 通过handler发送请求到handlerMessage中更新
###AsynckTask的用法
####首先创建一个继承AsynckTask的类
- doInBackground():run方法
- onPostExecute():handlerMessage方法
####在代码中调用

    //创建一个线程
        TaskGetData task = new TaskGetData();
        //执行线程
        task.execute();
####


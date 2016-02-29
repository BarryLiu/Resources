package com.example.atar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 
 * @author Barry
 * 
 */
public class ThreadPoolManager {
	private static ThreadPoolManager manager;
	// 创建一个线程管理器
	private ExecutorService executorService;

	private ThreadPoolManager() {
		// 获取当前设备的cup的数量
		int num = Runtime.getRuntime().availableProcessors();
		executorService = Executors.newFixedThreadPool(1);
	}

	public static ThreadPoolManager getInstance() {
		if (manager == null) {
			manager = new ThreadPoolManager();
		}
		return manager;
	}

	public void execute(Runnable r) {
		executorService.execute(r);
	}
}

package com.barry.fileeditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串的管理类 与 String.xml 有点像
 * @author Barry
 *
 */
public class ResUtils {
	//Menu的名称
	public static String [] menuNames={"手机","sd卡","新建","粘贴","退出"};
	//Menu对应的图片
	 public static final int[] menuIds={
         R.drawable.menu_phone,
         R.drawable.menu_sdcard,
         R.drawable.menu_create,
         R.drawable.menu_palse,
         R.drawable.menu_exit
 };
	//以什么结尾的文件
	public static String[] iconDex = { ".rc", ".txt", ".png", ".mp3", ".mp4" };
	//以什么结尾的文件对应的图标
	public static int[] iconImage = { R.drawable.zip_icon, R.drawable.txt,
			R.drawable.image, R.drawable.audio, R.drawable.video };
	
	//将以什么结尾的文件与图标以键值对存储
	public static Map<String, Integer> resMap;
	//根据传入的文件实体类找出他所对应的图标
	public static int getIconRes(FileBean fb) {
		String fileName = fb.getFileName();
		if (resMap == null) {
			resMap = new HashMap<String, Integer>();
			for (int i = 0; i < iconDex.length; i++) {
				resMap.put(iconDex[i], iconImage[i]);
			}
		}
		
		//放回上一层和根目录
		if(FileManager.BACK_TO_ROOT.equals(fileName))
			return R.drawable.back_to_root; 
		else if(FileManager.BACK_TO_UP.equals(fileName))
			return R.drawable.back_to_up;
		//如果是文件夹
		else if(new File(fb.getFilePath()).isDirectory())
			return R.drawable.folder;
		
		
		int pos = fileName.lastIndexOf(".");
		if(pos <=0) pos=0;
		String dexStr=fileName.substring(pos,fileName.length());
		
		if (resMap.containsKey(dexStr))
			return resMap.get(dexStr);
		else
			return R.drawable.others;
	}
}

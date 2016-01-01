package com.barry.fileeditor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ResUtils {
	public static String [] menuNames={"手机","sd卡","新建","粘贴","退出"};
	 public static final int[] menuIds={
         R.drawable.menu_phone,
         R.drawable.menu_sdcard,
         R.drawable.menu_create,
         R.drawable.menu_palse,
         R.drawable.menu_exit
 };
	
	public static String[] iconDex = { ".rc", ".txt", ".png", ".mp3", ".mp4" };
	public static int[] iconImage = { R.drawable.zip_icon, R.drawable.txt,
			R.drawable.image, R.drawable.audio, R.drawable.video };

	public static Map<String, Integer> resMap;

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

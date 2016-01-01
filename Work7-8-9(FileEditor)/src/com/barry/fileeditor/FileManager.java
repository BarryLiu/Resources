package com.barry.fileeditor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class FileManager {
	 public static final String BACK_TO_ROOT = "返回到根目录";
	 public static final String BACK_TO_UP   = "返回上一层";
	
	 public static String CopyPath;
	 public static String CurrPath;
	 
	 public static List<FileBean> getFileLists(String path) {
		 List<FileBean> datas =new ArrayList<FileBean>();
		 
		 if(!"/".equals(path)){
			 FileBean btr= new FileBean("/", FileManager.BACK_TO_ROOT);
			 datas.add(btr);
			 
			 FileBean btu =new FileBean(FileManager.CurrPath, FileManager.BACK_TO_UP);
			 datas.add(btu);
		 }
		 
		File file = new File(path);
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			FileBean fb = new FileBean();
			fb.setFileName(listFiles[i].getName());
			fb.setFilePath(listFiles[i].getPath());

			datas.add(fb);
		}
		return datas;
	}
	private static String getSdCard(){
		return Environment.getRootDirectory().getPath();
	}
	 
	 
}

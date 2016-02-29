package com.example;

import java.io.File;

import android.os.Environment;

public class Util {
	public static final String path = "http://192.168.8.14:8080/Http1/";

	public static File getDirPath() {
		File file = new File(Environment.getExternalStorageDirectory(),
				"imageCacheDir");
		if (!file.exists())
			file.mkdir();
		return file;
	}

	public static void clearFile() {
		File sdCardFold = Environment.getExternalStorageDirectory();
		File imageCacheFolder = new File(sdCardFold, "imageCacheDir");

		File[] fileList = imageCacheFolder.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			fileList[i].delete();
		}
	}
}

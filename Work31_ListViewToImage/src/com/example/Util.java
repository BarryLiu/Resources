package com.example;

import java.io.File;

import android.os.Environment;

public class Util {
	public static final String path = "http://192.168.1.103:8080/Http1/";

	public static File getDirPath() {
		File file = new File(Environment.getExternalStorageDirectory(),
				"imageCacheDir");
		if (!file.exists())
			file.mkdir();
		return file;
	}

}

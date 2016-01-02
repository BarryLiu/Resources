package com.barry.fileeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

/**
 * 文件管理器管理类 (单例)
 * 
 * @author Barry
 *
 */
public class FileManager {
	public static final String BACK_TO_ROOT = "返回到根目录";
	public static final String BACK_TO_UP = "返回上一层";

	public static String CopyPath;
	public static String CurrPath;

	private FileManager() {
	}

	private static FileManager fileManager;

	public static FileManager getInstance() {
		if (fileManager == null)
			return new FileManager();
		return fileManager;
	}

	/**得到当前目录的文件列表	 */
	public static List<FileBean> getFileLists(String path) {
		List<FileBean> datas = new ArrayList<FileBean>();

		if (!"/".equals(path)) {
			FileBean btr = new FileBean("/", FileManager.BACK_TO_ROOT);
			datas.add(btr);

			FileBean btu = new FileBean(FileManager.CurrPath,
					FileManager.BACK_TO_UP);
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
	/**得到sd卡的根目录*/
	public static String getSdCard() {
		return Environment.getRootDirectory().getPath();
	}
	/**点击menu的删除文件操作*/
	public boolean deleteFile(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				deleteFile(listFiles[i]);
			}
			file.delete();
		}
		file.delete();
		return false;
	}
	/**创建文件操作*/
	public static boolean createFile(File file) {
		try {
			return file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**创建文件夹*/
	public static boolean createFolder(File file) {
		return file.mkdir();
	}
	/**复制文件   更具当前目录与要复制的目录 如果是文件夹要用到递归*/
	public boolean copyFile() {
		File srcFile = new File(FileManager.CopyPath);
		File tagFile = new File(FileManager.CurrPath);
		try {
			return copyFile(srcFile, tagFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**复制文件的递归操作*/
	private boolean copyFile(File srcFile, File tagFile)
			throws FileNotFoundException {

		if (srcFile.isDirectory()) {
			tagFile.mkdir();
			File[] listFiles = srcFile.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				File newFile = new File(tagFile.getPath(), srcFile.getName());
				copyFile();
			}
			return true;
		} else {
			try {
				InputStream is = new FileInputStream(srcFile);
				OutputStream os = new FileOutputStream(tagFile);
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.close();
				is.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}

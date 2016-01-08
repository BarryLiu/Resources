#2文件管理器的实现

#访问手机sdcard
- 需要向系统申请权限

        <!-- 获取访问外接设备权限 -->
   		<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    	<!-- 申请sdcard写入权限 -->
    	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
- 使用Environment对sdcard进行相关操作

        //判断sdcard是否存在
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            //sdcard已经存在
            return Environment.getExternalStorageDirectory().getPath();
        }else{
            return null;
        }
##英文
permission:权限
STORAGE:存储
EXTERNAL:外部的
FILESYSTEMS:文件系统
MOUNTED:接上的,插入的
Environment:环境
Title:标题
AlertDialog:提示对话框
LayoutInflater:视图构造器
Negative:负面的
Positive:正面的

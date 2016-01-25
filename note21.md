#17Bitmap和Drawable
##Bitmap的加载
- BitmapFacotry
- res/drawable目录

        bitmapFace = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.pic_s1_0
        );
###assets目录
- 用于存放二进制文件的目录
- 和raw的区别:assets加载的时候是直接通过路径进行加载

      bitmapBrow = BitmapFactory.decodeStream(
                    getContext().getAssets().open("pic_s3_1.png"));
     

###sdcard加载

    BitmapFactory.decodeFile(new File(Environment.getExternalStorageDirectory(),"xx.png"));

###图片的保持
- 将图片转换成byte[]数组
 		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmFace.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] buffer = baos.toByteArray();

- 保持图片到文件中

            File file = new File(Environment.getExternalStorageDirectory(),"ali.png");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

##图片的缩小
- 直接调用缩小的方法

 			bm =  Bitmap.createScaledBitmap(bm,
                bm.getWidth() / 2, //缩小到多小宽度
                bm.getHeight() / 2, //缩小多小的高度
                true);//让图片不要失真

- 图片在不用的时候要手动进行释放

   	 	if(bm != null){
            bm.recycle();//释放图片的内存
        }

interval:间隔

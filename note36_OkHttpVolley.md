#第三方网络框架
- okHttp + Volley

##okHttp
- 用于取代HttpClient
- 如何导入,和配置
###从网络获取JSON

         //创建一个okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个request请求
        Request request = new Request.Builder().url(url).build();
        //创建一个Call
        Call call = okHttpClient.newCall(request);
        //发送Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }
            
            @Override
            public void onResponse(Response response) throws IOException {
                //result = response.toString();
                final String json = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("result",json);
            }
        });

###从ListView网络获取的图片
- okHttp并没有提供获取多图片的解决方法,使用Volley

        InputStream is = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
             
###使用Volley解决多图片显示问题
- 在Adapter是使用Volley中提供的ImageLoader类可以直接加载图片

            imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            //获取缓存中的图片
            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }
            //将图片放入到缓存中
            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lruCache.put(s,bitmap);
            }
        });
- imageLoad并没有提供缓存,需要直接实现一个lruCache缓存

        static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(1024*1024*4){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
- 在getView中直接进行获取,获取的时候需要使用tag来解决图片乱跳的问题

            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                //获取到返回的图片的地址是否和imageView的一样
                String srcUrl = (String) iv.getTag();
                String returnUrl = imageContainer.getRequestUrl();
                if(srcUrl.equals(returnUrl)){
                    iv.setImageBitmap(imageContainer.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
- 如果要使用文件缓存的话，需要在返回的http的头中设置Cache-control属性控制缓存的时间

    	response.addHeader("Cache-Control", "max-age=100");



queue:队列




- Xutils3

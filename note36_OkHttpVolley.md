#36_7������������
#������������
- okHttp + Volley

##okHttp
- ����ȡ��HttpClient
- ��ε���,������
###�������ȡJSON

         //����һ��okHttpClient
        OkHttpClient okHttpClient = new OkHttpClient();
        //����һ��request����
        Request request = new Request.Builder().url(url).build();
        //����һ��Call
        Call call = okHttpClient.newCall(request);
        //����Call
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

###��ListView�����ȡ��ͼƬ
- okHttp��û���ṩ��ȡ��ͼƬ�Ľ������,ʹ��Volley

        InputStream is = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
             
###ʹ��Volley�����ͼƬ��ʾ����
- ��Adapter��ʹ��Volley���ṩ��ImageLoader�����ֱ�Ӽ���ͼƬ

            imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            //��ȡ�����е�ͼƬ
            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }
            //��ͼƬ���뵽������
            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lruCache.put(s,bitmap);
            }
        });
- imageLoad��û���ṩ����,��Ҫֱ��ʵ��һ��lruCache����

        static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(1024*1024*4){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
- ��getView��ֱ�ӽ��л�ȡ,��ȡ��ʱ����Ҫʹ��tag�����ͼƬ����������

            imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                //��ȡ�����ص�ͼƬ�ĵ�ַ�Ƿ��imageView��һ��
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
- ���Ҫʹ���ļ�����Ļ�����Ҫ�ڷ��ص�http��ͷ������Cache-control���Կ��ƻ����ʱ��

    	response.addHeader("Cache-Control", "max-age=100");



queue:����




- Xutils3
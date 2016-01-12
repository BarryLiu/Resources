#对话框
##对话框的一般流程
- 创建一个builder
- 设置title
- 设置内容
- 设置按键
- show出来

###单选对话框

        public void danxuan(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(strs,
                position//默认第一个已经被选中
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = which;
                        Toast.makeText(MainActivity.this,"选项是"+strs[which],Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"选择的结果是"+strs[position],Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
> 注意是最后点击确定结果,而不是选择确定结果,所以在选择的时候需要将选择的结果保持起来


###复选对话框

      boolean[] selecteds = {
            true,
            true,
            false,
            false
    };
    public void duoxuan(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMultiChoiceItems(strs, selecteds, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which,//那个被点击
                                boolean isChecked) {
                //修改boolean数组中的状态
                selecteds[which] = isChecked;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for (int i= 0; i<selecteds.length;i++){
                    if (selecteds[i]){
                        result += strs[i] +" ";
                    }
                }
                Toast.makeText(MainActivity.this,"选择中的是"+result,Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
> 类似单选对话框,应该在选择的时候将状态保存起来,在点击确定的时候获取选项
###日期对话框
- DatePickerDialog,关键是如何获取当前系统的日期

        //获取当前系统的时间戳
        long time = System.currentTimeMillis();
        //获取日历对象
        Calendar c = Calendar.getInstance();
        //设置当前的时间
        c.setTimeInMillis(time);
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
###时间对话框
- TimePickerDailog
###进度条对话框
- ProgressDialog
- 不能够在主线程(UI线程)中进行耗时操作,如果主线程被阻塞10秒中以上,会报出ANR异常
- 如何避免ANR异常?将耗时操作放在子线程中运行
- 子线程不能够更新UI,使用handle发送消息到主线程中更新
####主线程不能进行耗时操作,需要通过子线程来运行
####子线程不能更新UI,需要通过handler在子线程发送请求,到主线程的handlerMessage方法中更新UI
- 在UI线程中创建Handler,并且实现handlerMessage方法

        Handler handler = new Handler(){

        //在handler.sendMessage的时候会被调用
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MainActivity.this,"下载完毕",Toast.LENGTH_SHORT).show();

        }
    };
- 在子线程中实现handler发送消息到主线程

     	Thread thread = new Thread(){
            //重写run方法
            @Override
            public void run() {
               
                //通过handler发送一条请求到主线程中更新ui
                handler.sendEmptyMessage(0);
            }};
##自定义对话框
- 设置背景为透明的主题

        <style name="dailogStyle" parent="@android:style/Theme.Dialog">
        <item name="android:windowBackground">
            @android:color/transparent
        </item>
    </style>

##PopupWindows任意弹出对话框
- popUpWindows的用法和daliog一样,有两个不同的地方
- 必须要设置对话框的大小
- 在show时候可以设置在对应的控件下方弹出

  		popupWindow.showAsDropDown(iv_more);


Multi:多个,复合
Single:单个
ChoiceItems:选择
transparent:透明

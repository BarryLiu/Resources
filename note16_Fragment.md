#13Fragment概述
#项目能力
- 从界面了解业务逻辑
- 判断一下又多少界面
- 考虑数据结构
- 业务逻辑

##Fragment
- 碎片,它是Activity的一部分,不但可以让界面和Activity分离,而且还可以让代码和Activity分离
###Fragment的使用
- 创建一个继承fragment的类
- 实现fragment中的onCreateView方法,创建一个view并且返回

    	public class LeftFragment extends Fragment {
    	/**
     	* 实现fragment的界面
     	* @param inflater
     	* @param container
     	* @param savedInstanceState
     	* @return
     	*/
    	@Nullable
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建出试图
        View  view = inflater.inflate(R.layout.left_layout,null);

        //通过View找到控件
        Button btn = (Button) view.findViewById(R.id.btn);
        final TextView tv = (TextView) view.findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("来一发");
            }
        });

        //将视图返回给fragment
        return view;}}
- 在Activity的layout中设置fragment标签,通过class指向fragment进行调用

        <fragment
        android:id="@+id/left_frag"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        class="com.geek.com.fragmenta9.LeftFragment"
        android:layout_weight="1"
        >
    </fragment>

###实现自定切换案例
- 当屏幕切换可以调用不同的布局
- 在res目录下创建一个layout-land的目录,当屏幕切换成横屏的时候就会调用当前目录下的资源


land:横的缩写

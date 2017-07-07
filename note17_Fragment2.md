##14Fragment的应用
- Fragment:碎片,是Activity的一部分,可以将代码和界面都分离到一个Fragment中
- 在res/layout目录下,创建一个layout-land文件夹。当屏幕切换成横屏的时候会调用其中xml
- java的内存回收机制:垃圾回收器:当对象的引用计数器等于0的时候,会选择性回收内存
- 最好不要再不同的Fragment中通过findViewbyid找到其他fragment中的控件

##Fragment和Activity之间相互传递数值
###Activity传递数值给Fragment
- 在Activity中找到Fragment

        //找到Fragment
        FragmentManager fm = getFragmentManager();

        RightFragment fragment = (RightFragment) fm.findFragmentById(R.id.right_fragment);

        fragment.setContent(content);
###Fragment传递数值给Activity
- 1.首先在Fragment中创建一个接口,声明一个传递数值的方法

        public interface IContent{
        public void sendData(String content);}
- 2,在Fragment中声明一个接口的引起

	 	IContent icontent;
- 3,在调用者一般Activity中实现传递数值的接口中方法

    	public class MainActivity extends AppCompatActivity implements LeftFragment.IContent{

    
    	@Override
    	public void sendData(String content) {
        //从Fragment中获取数值

        //将数值传递到rightfragment中
        FragmentManager fm = getFragmentManager();
        RightFragment fragment= (RightFragment) fm.findFragmentById(R.id.right_fragment);
        fragment.setContent(content);}}
- 4,在Fragment中调用数值的地方通过getActivity获取到接口,并且调用传递数值

        icontent = (IContent) getActivity();
        icontent.sendData("第"+position+"章");

-内存的四个区域:代码区,静态区,堆和栈

###使用V4包中的Fragment
- Fragment是3.0以后出现的版本,V4包的出现就是让3.0以前的版本也可以使用Fragment
- Fragment,FragmentManager,FragmentActivit都是v4包中

###通过FragmentTransaction对Fragment进行相关修改
- 获取FragmentManager fm
- 通过fm获取FragmentTransaction ft
- 对fragment进行add,hide,show,replace等相关操作
- 最后要调用ft.commit执行

###Fragment的应用
- 创建一个Fragment作为Viewpager的内容

    	public class PagerFragment extends Fragment {
    	@Nullable
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
      	// tv.setText();
        //获取传递过来的位置的数值
        int position = getArguments().getInt("pos");
        tv.setText("第"+position+"页");
        tv.setTextSize(40);
        return tv;}}
- 创建一个继承FragmentPagerAdapter的类

    	public class VPAdapter extends FragmentPagerAdapter {
    
    	public VPAdapter(FragmentManager fm) {
        super(fm);
    	}

    
   	 	@Override
    	public Fragment getItem(int position) {
        //创建一个fragment
        PagerFragment fragment = new PagerFragment();
        //创建一个bundle用于传递fragment的数值
        Bundle b = new Bundle();
        b.putInt("pos",position);
        //将bundle放入到fragment中
        fragment.setArguments(b);
        return fragment;
    }

   
    	@Override
    	public int getCount() {
        return 3;
    	}}
- 绑定适配器

###FragmnetTabhost的用法
- 在xml中创建一个FrameLayout和FragmentTabhost标签

        <!-- Fragment的内容区域-->
    	<FrameLayout
        android:id="@+id/realcontent"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        >
    </FrameLayout>

    	<--下面的选项卡-->
    	<android.support.v4.app.FragmentTabHost
        android:id="@+id/tabhost"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    	</android.support.v4.app.FragmentTabHost>

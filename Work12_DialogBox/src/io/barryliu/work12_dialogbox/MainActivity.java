package io.barryliu.work12_dialogbox;

import java.util.Calendar;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * 
 * @author Barry
 *
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTips(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    String arr[] =new String[]{"湖南","湖北","福建"};

    public void puTongBtn(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("普通");
        builder.setMessage("我是普通对话框");

        builder.setNegativeButton("退出", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });

        builder.show();
    }

    public void lieBiaoBtn(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("列表对话框");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showTips("选择的是 " + arr[which]);
            }
        });
        builder.show();

    }


    public void singleBtn(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("单选");
        builder.setSingleChoiceItems(arr, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showTips("选择的是 " + arr[which]);
            }
        });


        builder.setNegativeButton("退出", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showTips("确定");
            }
        });
        builder.show();
    }


    public void muiltBtn(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("多选");
        builder.setMultiChoiceItems(arr, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                showTips("选择的是 " + arr[which]);
            }
        });

        builder.setNegativeButton("退出", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showTips("");
            }
        });
        builder.show();
    }
    //日期对话框
    public void dateBtn(View view) {
        long time =System.currentTimeMillis();
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(time);

        DatePickerDialog dpd =new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showTips("year = "+year+" month="+(monthOfYear+1)+"day="+dayOfMonth);
            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

        dpd.show();
    }
    //时间对话框
    public void timeBtn(View view) {
        int hour = 15;
        int minute = 20;
        boolean flag=true;//是否24小时制的
        TimePickerDialog tpd =new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                showTips(hourOfDay+"点"+minute+"分");
            }
        },hour,minute,flag);

        tpd.show();
    }
    //进度条
    public void progressBtn(View view) {
        final ProgressDialog pd=new ProgressDialog(MainActivity.this);
        //修改进度条的样式
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("正在下载");
        final android.os.Handler osHandler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                showTips("下载完毕");
            }
        };
        //子线程才能
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pd.setProgress(i);
                }
                //取消对话框
                pd.dismiss();
                //子线程不能调用主线程的UI
                osHandler.sendEmptyMessage(0);
            }
        }.start();
        getIntent();
        pd.show();
    }

    //自定义对话框
    public void customBtn(View view) {
        View v = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        final Dialog dialog =new Dialog(MainActivity.this,R.style.dialogStyle);
        //设置主题 必须在   dialog.setContentView(v); 前
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //设置点击别不退出
        dialog.setCancelable(false);
        dialog.setContentView(v);
        dialog.show();

        //点击下一题
        Button btn_next = (Button) v.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消对话框
                dialog.dismiss();
            }
        });
    }

    public void showMore(View view) {
    	ImageView  iv_more = (ImageView) findViewById(R.id.iv_more);

        final PopupWindow popupWindow =new PopupWindow(this);
        //创建一个windows
        View v1 = getLayoutInflater().inflate(R.layout.popup_layout,null);
        v1.setBackgroundColor(color.black);
        
        //必须要设置显示的大小
        popupWindow.setWidth(200);
        popupWindow.setHeight(150);
        popupWindow.setContentView(v1);
        //显示的时候可以指定在特定的位置出来
         popupWindow.showAsDropDown(iv_more);
         
//         View root = findViewById(R.id.root);设置重别的地方出来
//        popupWindow.showAtLocation(root,Gravity.BOTTOM | Gravity.LEFT,0,-150);
        
        
         
        Button btn = (Button) v1.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	showTips("点击了");
                popupWindow.dismiss();
            }
        });
        //构建出listView
        ListView lv = (ListView) v1.findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,new String[]{"记一笔","查询"});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
            }
        });
      
    }
}

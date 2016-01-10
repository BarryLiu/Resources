package com.example.work11;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * {@link BankAccountDao}用户表
 * {@link BankHistoryDao}转账记录表
 * 添加用户和转账操作<br>
 * <h1>CursorAdapter用法 </h1>
 * @author Barry
 *
 */
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private SQLiteDatabase db;
	private Button btn_addUser;
	private Button btn_zhuanZhan;
	private Button btn_users;
	 private Button btn_userslog;
	private ListView lv_history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DBHelper helper =new DBHelper(MainActivity.this, DBHelper.DATABASE_NAME, null, 1);
		db = helper.getWritableDatabase();
		
		initView();
		
		setLisenter();
		
		setAdapter();
	}

	private void setAdapter() {
		Cursor cursor= BankHistoryDao.queryAll(db);
		
		//创建适配器
		CursorAdapter adapter =new CursorAdapter(MainActivity.this,cursor,false) {


            /**
             * 创建View
             * @param context
             * @param cursor
             * @param parent
             * @return
             */
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				View view =LayoutInflater.from(MainActivity.this).inflate(R.layout.lv_history, null);
				return view;
			}
			 /**
             * 绑定View
             * @param view
             * @param context
             * @param cursor
             */
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
				TextView tv_data = (TextView) view.findViewById(R.id.tv_data);
				TextView tv_money = (TextView) view.findViewById(R.id.tv_money);
				TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
				TextView tv_userid = (TextView) view.findViewById(R.id.tv_userid);
				
				
				tv_name.setText(cursor.getString(1));
				tv_data.setText(cursor.getString(2));
				 tv_money.setText(cursor.getString(3));
				tv_phone.setText("123456");
				 tv_userid.setText(cursor.getString(4));
			}
		};
		lv_history.setAdapter(adapter);
	}

	private void setLisenter() {
		
		//点击添加用户条到添加用户页面
		setAddUserLisenter();
		// 点击转账跳到转账页面
		setbtn_zhuanZhanLisenter();
		
		//跳到 SecondActivity查询用户
		setbtnTiaoZhuan();
		
		//setShowLogLisenter();
		
	}
	/**跳到SecondActivity	 */
	private void setbtnTiaoZhuan() {
		btn_users.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent =new Intent();
				intent.setClass(MainActivity.this,SecondActivity.class);
				
				startActivity(intent); 
			}
		});
	}

	private void setShowLogLisenter() {
		btn_userslog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BankAccountDao.logUsers(db);
			}
		});
	}
	/**
	 * 点击转账跳到转账页面
	 */
	private void setbtn_zhuanZhanLisenter() {
		btn_zhuanZhan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View vzz = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_zhuanzhan, null);
				final EditText et_payId = (EditText) vzz.findViewById(R.id.et_payId);
				final EditText et_toId = (EditText) vzz.findViewById(R.id.et_toId);
				final EditText et_money = (EditText) vzz.findViewById(R.id.et_money);
				
				AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_LIGHT);
				builder.setTitle("转账");
				builder.setView(vzz);
				builder.setNegativeButton("退出", null);
				builder.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String moneyStr = et_money.getText().toString();
						String payId = et_payId.getText().toString();
						String toId = et_toId.getText().toString();
						if(moneyStr.isEmpty()||payId.isEmpty()||toId.isEmpty()){
							showTips(MainActivity.this, "信息不完整，转账失败");
							return ;
						}
						Date date = new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						String dataStr = sdf.format(date);
						
						try {
							db.beginTransaction(); //开启事务
							
							Object payUser[] = BankAccountDao.queryById(db,payId); 
							Object toUser[] = BankAccountDao.queryById(db,toId);
							if(payUser[0] ==null ||toUser[0] ==null ){ 
								showTips(MainActivity.this, " 转账或者收款没有人 ");
								return ; //有为空表示 转账或者收款没有人 
							}
							
							Double money = Double.valueOf(moneyStr);
							
							Double payNewMoney=((Double)payUser[2])-money;
							BankAccountDao.update(db,payId,(String)payUser[1],payNewMoney);
							BankHistoryDao.insert(db,payId,-money,(String)payUser[1],dataStr);
							
							Double toNewMoney=((Double)toUser[2])+money;
							BankAccountDao.update(db,toId,(String)toUser[1],toNewMoney);
							BankHistoryDao.insert(db,toId,money,(String)toUser[1],dataStr);
							
							
							if(payNewMoney<0.0 ){ 
								showTips(MainActivity.this, "金额不足 ");
								return ; //金额不足
							}
							
							db.setTransactionSuccessful();  //成功
							showTips(MainActivity.this, "转账成功");
							
							
						} catch (NumberFormatException e) {
							showTips(MainActivity.this, "转账失败");
							e.printStackTrace();
						}finally{
							db.endTransaction(); //结束事务
						}
						//转账成功
						setAdapter();
					}
				});
				builder.show();
			}
		});
	}
	/**
	 * 点击添加用户条到添加用户页面
	 */
	private void setAddUserLisenter() {
		btn_addUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View v2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_adduser, null);
				final EditText et_name = (EditText) v2.findViewById(R.id.et_name);
				final EditText et_money = (EditText) v2.findViewById(R.id.et_money);
				
				AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
				builder.setTitle("添加用户");
				builder.setView(v2);
				builder.setNegativeButton("取消", null);
				
				builder.setPositiveButton("确定",  new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String name = et_name.getText().toString();
						String moneyStr = et_money.getText().toString();
						
						if(name.isEmpty()){
							showTips(MainActivity.this, "插入失败，姓名不能为空");
							return;
						}
						Double money = 0.0;
						if(!moneyStr.isEmpty())
							money=Double.valueOf(moneyStr);
						
						BankAccountDao.insert(db,name,money);
						showTips(MainActivity.this, "插入成功");
					}
				} );
				
				builder.show();
			}
		});
	}

	private void initView() {
		btn_addUser = (Button)findViewById(R.id.btn_addUser);
		btn_zhuanZhan =(Button)findViewById(R.id.btn_zhuanZhan);
		btn_users =(Button)findViewById(R.id.btn_queryUsers);
		//btn_userslog =(Button)findViewById(R.id.btn_userslog);
		lv_history =(ListView)findViewById(R.id.lv_history);
	}

	public static void showTips(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}

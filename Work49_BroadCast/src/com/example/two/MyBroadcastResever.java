package com.example.two;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastResever extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		System.out.println("MyBroadcastResever	.来了");
		
	}
}

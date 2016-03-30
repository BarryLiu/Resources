package com.example.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myaidl.IMusicAidlInterface;

public class MainActivity extends AppCompatActivity {

    ServiceConnection serviceConnection ;
    MusicService musicService ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                musicService = (MusicService) IMusicAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };




    }


    public void playMusic(View view) {
        musicService.play();
    }

    public void stopMusic(View view) {
        musicService.pause();
    }



    public void startService(View view) {
        Intent intent = new Intent(this,MusicService.class);
        startService(intent);
    }

    public void bindMyService(View view) {
        Intent intent = new Intent(this,MusicService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
}

package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.myaidl.IMusicAidlInterface;

import java.io.FileDescriptor;

public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBind();
    }

    MediaPlayer mp;
    @Override
    public void onCreate() {
        super.onCreate();

        mp = MediaPlayer.create(this,R.raw.music02);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp!=null)
            mp.release();

    }

    public void play(){
        mp.start();
    }

    public void pause(){
        mp.pause();
    }


    public class MyBind extends IMusicAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void playMusic() throws RemoteException {
            play();
        }

        @Override
        public void pauseMusic() throws RemoteException {
            pause();
        }
    }
}

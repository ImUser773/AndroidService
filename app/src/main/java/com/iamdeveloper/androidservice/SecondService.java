package com.iamdeveloper.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * Created by IamDeveloper on 9/11/2016.
 */
public class SecondService extends Service {
    private IBinder mBinder = new MyBinder();
    private Chronometer chronometer;
    long second;
    long millis;

    public class MyBinder extends Binder {
        SecondService getService(){
            return SecondService.this;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("onBind","onBind");
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("onCreate","onCreate");
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("onUnbind","onUnbind");
        Toast.makeText(this,"onUnbind",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v("onRebind","onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("onDestroy","onDestroy");
    }

    public long onCount(){
        millis = SystemClock.elapsedRealtime() - chronometer.getBase();
        second = millis / 1000;

        return second;
    }



}

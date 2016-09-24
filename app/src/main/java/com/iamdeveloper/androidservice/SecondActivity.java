package com.iamdeveloper.androidservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iamdeveloper.androidservice.SecondService.MyBinder;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_start,button_stop;
    private TextView text;
    private SecondService service;
    private boolean mBound = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button_start = (Button) findViewById(R.id.btn_start);
        button_stop = (Button) findViewById(R.id.btn_stop);
        text = (TextView) findViewById(R.id.text);
        button_start.setOnClickListener(this);
        button_stop.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,SecondService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_start:
                if(mBound){
                    text.setText(""+service.onCount());
                }


                break;
            case R.id.btn_stop:
                if(mBound){
                    text.setText(""+service.onCount());
                    unbindService(serviceConnection);
                    mBound = false;
                }
                Intent intent = new Intent(this,SecondService.class);
                stopService(intent);

                break;
        }
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBinder myBinder = (MyBinder) iBinder;
            service = myBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
}

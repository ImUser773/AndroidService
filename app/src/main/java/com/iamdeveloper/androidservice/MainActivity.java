package com.iamdeveloper.androidservice;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_service1,button_service2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_service1 = (Button) findViewById(R.id.btn_service1);
        button_service2 = (Button) findViewById(R.id.btn_service2);

        button_service1.setOnClickListener(this);
        button_service2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_service1:
                startService(new Intent(this,FirstService.class));
                break;
            case R.id.btn_service2:
                startActivity(new Intent(this,SecondActivity.class));
                break;


        }
    }


}

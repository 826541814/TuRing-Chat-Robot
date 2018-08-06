package com.cr.turingrobot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ActivityManager.getInstance().addActivity(this);

        Button bt= (Button) findViewById(R.id.btn5);
        Button bt1= (Button) findViewById(R.id.btn6);
        Button bt2= (Button) findViewById(R.id.btn7);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Log.this,UserSetting.class));
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Log.this,MainActivity.class));
                ActivityManager.getInstance().exit();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log.this,MainActivity.class));
            }
        });


    }
}

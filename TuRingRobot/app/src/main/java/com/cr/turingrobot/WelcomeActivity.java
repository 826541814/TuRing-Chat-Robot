package com.cr.turingrobot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cr.turingrobot.ActivityManager;
import com.cr.turingrobot.Log;
import com.cr.turingrobot.Myopenhelper;
import com.cr.turingrobot.R;
import com.cr.turingrobot.Register;

public class WelcomeActivity extends AppCompatActivity {
    Myopenhelper myopenhelper;
    SQLiteDatabase db;
    Button bt1,bt2;
    EditText username;
    EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ActivityManager.getInstance().addActivity(this);


        bt1= (Button) findViewById(R.id.btn1);
        bt2= (Button) findViewById(R.id.btn2);
        username= (EditText) findViewById(R.id.username);
        pwd= (EditText) findViewById(R.id.passwd);


        myopenhelper = new Myopenhelper(this);

        //db = myopenhelper.getWritableDatabase();
        //  db.execSQL("insert into person (username,passwd) values('lisi','123')");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = myopenhelper.getReadableDatabase();

                Cursor cursor= db.rawQuery("select * from person where username=? and passwd=?",new String[]{username.getText().toString(),pwd.getText().toString()});
                if(cursor.getCount()<1)
                    Toast.makeText(WelcomeActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                else
                    startActivity(new Intent(WelcomeActivity.this,Log.class));
                db.close();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,Register.class));
            }
        });


    }
}


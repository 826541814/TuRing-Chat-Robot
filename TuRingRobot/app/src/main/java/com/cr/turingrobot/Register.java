package com.cr.turingrobot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Myopenhelper myopenhelper;
    SQLiteDatabase db;
    Button bt3,bt4;
    EditText username1;
    EditText pwd1;
    EditText repwd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActivityManager.getInstance().addActivity(this);

        bt4= (Button) findViewById(R.id.btn4);

        bt3= (Button) findViewById(R.id.btn3);
        username1= (EditText) findViewById(R.id.username1);
        pwd1= (EditText) findViewById(R.id.passwd1);
        repwd1= (EditText) findViewById(R.id.repasswd1);
        myopenhelper = new Myopenhelper(this);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username1.getText().toString().equals("")||pwd1.getText().toString().equals("")||repwd1.getText().toString().equals(""))
                    Toast.makeText(Register.this,"用户名或密码不能为空", Toast.LENGTH_LONG).show();
                else
                {
                    if (!pwd1.getText().toString().equals(repwd1.getText().toString()))
                    {
                        Toast.makeText(Register.this,"两次密码输入不相同", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        db = myopenhelper.getReadableDatabase();
                        Cursor cursor= db.rawQuery("select * from person where username= ? ",new String[]{username1.getText().toString()});

                        if(cursor.getCount()>0)
                        {
                            Toast.makeText(Register.this,"用户名已存在", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            db = myopenhelper.getWritableDatabase();
                            db.execSQL("insert into person (username,passwd) values(?,?)", new Object[]{username1.getText().toString(),repwd1.getText().toString()});
                            Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,WelcomeActivity.class));
                        }
                        db.close();
                    }
                }
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,WelcomeActivity.class));
            }
        });
    }
}

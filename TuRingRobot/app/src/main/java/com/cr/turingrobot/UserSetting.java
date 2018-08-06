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

public class UserSetting extends AppCompatActivity {
    Myopenhelper myopenhelper;
    SQLiteDatabase db;
    Button bt6,bt7;
    EditText username1;
    EditText pwd1;
    EditText repwd1;
    EditText password222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        ActivityManager.getInstance().addActivity(this);

        bt7= (Button) findViewById(R.id.btn7);

        bt6= (Button) findViewById(R.id.btn6);
        password222= (EditText) findViewById(R.id.password222);
        username1= (EditText) findViewById(R.id.username1);
        pwd1= (EditText) findViewById(R.id.passwd1);
        repwd1= (EditText) findViewById(R.id.repasswd1);
        myopenhelper = new Myopenhelper(this);

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = myopenhelper.getReadableDatabase();
                Cursor cursor= db.rawQuery("select * from person where username= ? and passwd=? ",new String[]{username1.getText().toString(),password222.getText().toString()});
                if(cursor.getCount()>0)
                {
                    if(pwd1.getText().toString().equals("")||repwd1.getText().toString().equals(""))
                        Toast.makeText(UserSetting.this,"密码不能为空", Toast.LENGTH_LONG).show();
                    else
                    {
                        if (!pwd1.getText().toString().equals(repwd1.getText().toString()))
                        {
                            Toast.makeText(UserSetting.this,"两次密码输入不相同", Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                                db = myopenhelper.getWritableDatabase();
                                db.execSQL("update person set passwd=? where username=?", new Object[]{pwd1.getText().toString(),username1.getText().toString()});
                                Toast.makeText(UserSetting.this, "用户账号修改成功", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(UserSetting.this,MainActivity.class));

                                db.close();
                        }
                    }
                }
                else
                    Toast.makeText(UserSetting.this,"用户名或密码错误", Toast.LENGTH_LONG).show();
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserSetting.this,Log.class));
            }
        });
    }
}

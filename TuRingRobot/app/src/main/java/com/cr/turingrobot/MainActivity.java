package com.cr.turingrobot;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<>();
    private final static String apiUrl = "http://www.tuling123.com/openapi/api";
    private final static String apiKey = "7325a566c856491e917fc36c6cd4ce2d";
    String urlStr = apiUrl+"?key="+apiKey;
    private static String question = null;
    final static int ROBOT_MESSAGE =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgListView = findViewById(R.id.msg_list_view);
        adapter =new MsgAdapter(MainActivity.this,R.layout.msg_item,msgList);
        initMsgs();
        msgListView.setAdapter(adapter);

    }
    public void initMsgs(){
        Msg msg = new Msg("图灵机器人为您服务",Msg.TYPE_RECEIVED);
        msgList.add(msg);
        //adapter.notifyDataSetChanged();
        //msgListView.setSelection(msgList.size());
    }
    public void sendMessage(View view){
        question = inputText.getText().toString();
        if(!"".equals(question)){
            Msg msg = new Msg(question,Msg.TYPE_SENT);
            msgList.add(msg);
            adapter.notifyDataSetChanged();
            msgListView.setSelection(msgList.size());
            inputText.setText("");
            MyAsync myAsync =new MyAsync();
            myAsync.execute();
        }
    }


    protected  class MyAsync extends AsyncTask
    {
        String getURL;
        String str2;
        @Override
        protected Object doInBackground(Object[] params) {
            String INFO=question;
            try {
                INFO= URLEncoder.encode(question,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            getURL = urlStr + "&info=" + INFO;
            try {
                URL url=new URL(getURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is=connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));

                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                // 断开连接
                connection.disconnect();

                str2 = sb.toString();

                JSONObject newjson=new JSONObject(str2);
                System.out.println(newjson);

//                String datanum1 = newjson.getString("text");
//                System.out.println(datanum1);
                return newjson;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String data123;
            if(o==null){
                data123 = "连接失败";
                //Msg msg = new Msg(data123,ROBOT_MESSAGE);
            }else{
            JSONObject jsonObject = (JSONObject) o;
            data123 = null;
            try {
                data123 = jsonObject.getString("text");
            } catch (JSONException e) {
                e.printStackTrace();
            } }
            System.out.println(data123);
            Msg msg = new Msg(data123,ROBOT_MESSAGE);
            msgList.add(msg);
            adapter.notifyDataSetChanged();
            msgListView.setSelection(msgList.size());
        }
    }
}

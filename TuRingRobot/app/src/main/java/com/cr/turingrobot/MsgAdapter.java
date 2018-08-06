package com.cr.turingrobot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends ArrayAdapter<Msg>{
    private int resourceId;
    public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.robotLayout=view.findViewById(R.id.robot_layout);
            viewHolder.senderLayout=view.findViewById(R.id.sender_layout);
            viewHolder.robotMsg=view.findViewById(R.id.robot_msg);
            viewHolder.senderMsg=view.findViewById(R.id.sender_msg);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        if(msg.getType()==Msg.TYPE_RECEIVED){
            viewHolder.robotLayout.setVisibility(View.VISIBLE);
            viewHolder.senderLayout.setVisibility(View.GONE);
            viewHolder.robotMsg.setText(msg.getContent());
        }else if(msg.getType()==Msg.TYPE_SENT){
            viewHolder.robotLayout.setVisibility(View.GONE);
            viewHolder.senderLayout.setVisibility(View.VISIBLE);
            viewHolder.senderMsg.setText(msg.getContent());
        }
        return view;
    }
    class ViewHolder {

        LinearLayout robotLayout;

        LinearLayout senderLayout;

        TextView robotMsg;

        TextView senderMsg;

    }
}

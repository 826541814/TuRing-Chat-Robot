package com.cr.turingrobot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment extends android.app.Fragment{
    private static String ARG_PARAM = "param_key";

    private String mParam;
    private Activity mActivity;
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);
    }
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//      //  View root = inflater.inflate(R.layout.fragment_1, container, false);
//        TextView view = root.findViewById(R.id.text);
//        view.setText(mParam);
//        return root;
//    }
    public static Fragment newInstance(String str) {
        Fragment frag = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);   //设置参数
        return frag;
    }
}

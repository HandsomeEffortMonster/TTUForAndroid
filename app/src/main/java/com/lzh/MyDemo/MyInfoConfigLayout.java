package com.lzh.MyDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.serialport.R;

/**
 * Created by LZH on 2018/6/10.
 */

public class MyInfoConfigLayout extends LinearLayout {
    public TextView tv_infoconfigname;
    public TextView tv_infoconfigcontent;
    public Button btn_changeinfocofig;

    public MyInfoConfigLayout(Context context, AttributeSet attris){
        super(context,attris);
        LayoutInflater.from(context).inflate(R.layout.myinfotoconfig,this);
        /*控件定义*/
        tv_infoconfigname = (TextView)findViewById(R.id.tv_infoconfigname);
        tv_infoconfigcontent = (TextView)findViewById(R.id.tv_infoconfigcontent);
        btn_changeinfocofig = (Button)findViewById(R.id.btn_changeinfocofig);
        btn_changeinfocofig.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }
}
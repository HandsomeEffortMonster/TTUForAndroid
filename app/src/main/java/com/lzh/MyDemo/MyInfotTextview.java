package com.lzh.MyDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.serialport.R;

/**
 * Created by LZH on 2018/6/9.
 * 自定义textview 信息显示
 */

public class MyInfotTextview  extends LinearLayout{
    public TextView tv_infoname;
    public TextView tv_infocontent;
    /*infoname 设值*/
    public void setTv_infoname(String str){
        tv_infoname.setText(str);
    }
    /*inforcontent 设内容*/
    public void setTv_infocontent(String str){
        tv_infocontent.setText(str);
    }

    public MyInfotTextview(Context context, AttributeSet attris){
        super(context,attris);
        LayoutInflater.from(context).inflate(R.layout.myinfotextview,this);
        /*控件定义*/
        tv_infoname = (TextView)findViewById(R.id.tv_infoname);
        tv_infocontent = (TextView)findViewById(R.id.tv_infocontent);
    }
}

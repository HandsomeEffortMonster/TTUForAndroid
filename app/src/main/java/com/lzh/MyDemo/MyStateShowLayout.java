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
 * Created by LZH on 2018/6/7.
 * 自定义状态显示控件  避免代码重复冗余
 */

public class MyStateShowLayout extends LinearLayout {
    public TextView tv_swtichingState;
    public TextView tv_swtichingShow;
    public Button btn_handOpen;
    public Button btn_handClose;

    public MyStateShowLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.mystateshow,this);
        /*对控件的绑定*/
        tv_swtichingState = (TextView)findViewById(R.id.tv_swtichingState);
        tv_swtichingShow = (TextView)findViewById(R.id.tv_swtichingShow);
        btn_handOpen = (Button)findViewById(R.id.btn_handOpen);
        btn_handClose = (Button)findViewById(R.id.btn_handClose);

    }
}

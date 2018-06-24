package com.lzh.MyDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.serialport.R;
import com.lzh.constant.ConstantValue;

/**
 * Created by LZH on 2018/6/9.
 * 自定义模拟量显示控件 避免代码重复冗余
 */

public class MyAnalogShowLayout extends LinearLayout{
    public TextView tv_DcAnalog_name;
    public TextView tv_DcAnalog_Value;
    public TextView tv_DcAnalog_unit;
    public Button btn_DcAnalog_config;

    public int getDcAnalog_parameter() {
        return dcAnalog_parameter;
    }

    public void setDcAnalog_parameter(int dcAnalog_parameter) {
        this.dcAnalog_parameter = dcAnalog_parameter;
    }

    /*模拟量除以的参数 做为其中的parameter作为保存  暂做保存  后期数据库处理*/
    private int dcAnalog_parameter;



    public MyAnalogShowLayout(final Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.myanalogshow,this);
        /*控件绑定*/
        tv_DcAnalog_name = (TextView)findViewById(R.id.tv_DcAnalog_name);
        tv_DcAnalog_Value = (TextView)findViewById(R.id.tv_DcAnalog_Value);
        tv_DcAnalog_unit = (TextView)findViewById(R.id.tv_DcAnalog_unit);

        btn_DcAnalog_config = (Button)findViewById(R.id.btn_DcAnalog_config);
        btn_DcAnalog_config.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                /*使用回调实现功能*/
                MyDialog myDialog = new MyDialog(context, "配置参数", new MyDialog.OnMyDialogListener() {
                    @Override
                    public void back(String str_name, String str_unit, int parameter) {
                        tv_DcAnalog_name.setText(str_name);
                        tv_DcAnalog_unit.setText(str_unit);
                        dcAnalog_parameter = parameter;

                    }
                });
                myDialog.show();
            }
        });
    }
}

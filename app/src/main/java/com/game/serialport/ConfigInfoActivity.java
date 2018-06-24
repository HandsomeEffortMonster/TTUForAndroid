package com.game.serialport;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzh.MyDemo.MyInfotTextview;
import com.lzh.constant.ConstantValue;

/**
 * create by lzh 20180607
 * 配置信息界面
 */
public class ConfigInfoActivity extends AppCompatActivity {
    private Context context;

    private MyInfotTextview myInfotTextview1;
    private MyInfotTextview myInfotTextview2;
    private MyInfotTextview myInfotTextview3;
    private MyInfotTextview myInfotTextview4;
    private MyInfotTextview myInfotTextview5;
    private MyInfotTextview myInfotTextview6;
    private MyInfotTextview myInfotTextview7;
    private MyInfotTextview myInfotTextview8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_info);
        context = ConfigInfoActivity.this;

        init();
    }
    public void init(){
        myInfotTextview1 = (MyInfotTextview)findViewById(R.id.myInfoTextView1);
        myInfotTextview2 = (MyInfotTextview)findViewById(R.id.myInfoTextView2);
        myInfotTextview3 = (MyInfotTextview)findViewById(R.id.myInfoTextView3);
        myInfotTextview4 = (MyInfotTextview)findViewById(R.id.myInfoTextView4);
        myInfotTextview5 = (MyInfotTextview)findViewById(R.id.myInfoTextView5);
        myInfotTextview6 = (MyInfotTextview)findViewById(R.id.myInfoTextView6);
        myInfotTextview7 = (MyInfotTextview)findViewById(R.id.myInfoTextView7);
        myInfotTextview8 = (MyInfotTextview)findViewById(R.id.myInfoTextView8);

        myInfotTextview1.setTv_infoname("设备ID：");
        myInfotTextview1.setTv_infocontent(ConstantValue.deviceId);
        myInfotTextview2.setTv_infoname("版本号:");
        myInfotTextview2.setTv_infocontent(ConstantValue.version);

    }
}

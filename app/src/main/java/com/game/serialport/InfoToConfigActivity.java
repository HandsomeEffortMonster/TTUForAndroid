package com.game.serialport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzh.MyDemo.MyInfoConfigLayout;
import com.lzh.constant.ConstantValue;

public class InfoToConfigActivity extends AppCompatActivity implements View.OnClickListener{
    private MyInfoConfigLayout myInfoConfigLayout1;
    private MyInfoConfigLayout myInfoConfigLayout2;
    private MyInfoConfigLayout myInfoConfigLayout3;
    private MyInfoConfigLayout myInfoConfigLayout4;
    private Button btn_infoConfigToThrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_to_config);
        init();
        show();
    }
    public void init(){
        btn_infoConfigToThrow = (Button)findViewById(R.id.btn_infoConfigToThrow);
        btn_infoConfigToThrow.setOnClickListener(this);

        myInfoConfigLayout1 = (MyInfoConfigLayout)findViewById(R.id.myInfoConfigLayout1);
        myInfoConfigLayout2 = (MyInfoConfigLayout)findViewById(R.id.myInfoConfigLayout2);
        myInfoConfigLayout3 = (MyInfoConfigLayout)findViewById(R.id.myInfoConfigLayout3);
        myInfoConfigLayout4 = (MyInfoConfigLayout)findViewById(R.id.myInfoConfigLayout4);


    }

    public void show(){
        myInfoConfigLayout1.tv_infoconfigname.setText("设备主IP地址");
        myInfoConfigLayout1.tv_infoconfigcontent.setText(ConstantValue.masterIPAddress);
        myInfoConfigLayout2.tv_infoconfigname.setText("设备备IP地址");
        myInfoConfigLayout2.tv_infoconfigcontent.setText(ConstantValue.masterIPAddress);
        myInfoConfigLayout3.tv_infoconfigname.setText("设备ID编号");
        myInfoConfigLayout3.tv_infoconfigcontent.setText("88675191");
        myInfoConfigLayout4.tv_infoconfigname.setText("设备软件版本编号");
        myInfoConfigLayout4.tv_infoconfigcontent.setText("1.0.0");

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_infoConfigToThrow:{
                Intent intent1 = new Intent(InfoToConfigActivity.this,ThrowOverActivity.class);
                startActivity(intent1);
            }
            break;
        }
    }
}

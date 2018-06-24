package com.game.serialport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * create by lzh 20180607
 * 转接界面
 */
public class ThrowOverActivity extends AppCompatActivity implements View.OnClickListener{

    private Button goElectric;
    private Button btn_goDCConfig;
    private Button btn_stateShow;
    private Button btn_goConfigInfo;
    private Button btn_goInfoConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw_over);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void init(){
        goElectric = (Button)findViewById(R.id.main_goElectric);
        btn_goDCConfig = (Button)findViewById(R.id.btn_goDCConfig);
        btn_stateShow = (Button)findViewById(R.id.btn_stateShow);
        btn_goConfigInfo = (Button)findViewById(R.id.btn_goConfigInfo);
        btn_goInfoConfiguration = (Button)findViewById(R.id.btn_goInfoConfiguration);

        goElectric.setOnClickListener(this);
        btn_goDCConfig.setOnClickListener(this);
        btn_stateShow.setOnClickListener(this);
        btn_goConfigInfo.setOnClickListener(this);
        btn_goInfoConfiguration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_goElectric:{
                Intent intent1 = new Intent(ThrowOverActivity.this,ElectricActivity.class);
                startActivity(intent1);
            }
            break;
            case R.id.btn_goDCConfig:{
                Intent intent2 = new Intent(ThrowOverActivity.this,dcAnalogActivity.class);
                startActivity(intent2);
            }
            break;
            case R.id.btn_stateShow:{
                Intent intent3 = new Intent(ThrowOverActivity.this,StateShowActivity.class);
                startActivity(intent3);
            }
            break;
            case R.id.btn_goConfigInfo:{
                Intent intent4 = new Intent(ThrowOverActivity.this,ConfigInfoActivity.class);
                startActivity(intent4);
            }
            break;
            case R.id.btn_goInfoConfiguration:{
                Intent intent5 = new Intent(ThrowOverActivity.this,InfoToConfigActivity.class);
                startActivity(intent5);
            }
        }
    }
}

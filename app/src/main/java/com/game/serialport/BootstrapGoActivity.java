package com.game.serialport;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by lzh 2018/06/07
 * 启动界面
 */
public class BootstrapGoActivity extends AppCompatActivity {

    private Context context;
    private TextView text_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootstrap_go);
        context = BootstrapGoActivity.this;
        init();


        /*延时5面进入主界面*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startIntent = new Intent(BootstrapGoActivity.this,ThrowOverActivity.class);
                startActivity(startIntent);
            }
        },5000);
    }

    public void init(){
        text_date = (TextView)findViewById(R.id.text_date);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str_date = sdf.format(d).toString();
        text_date.setText(str_date);
    }
}

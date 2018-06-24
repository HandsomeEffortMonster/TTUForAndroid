package com.game.serialport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzh.MyDemo.MyStateShowLayout;
import com.lzh.Service.SerialPortTtys0Service;
import com.lzh.Service.SerialPortTtys1Service;
/**
 * create by lzh 20180607
 * 状态显示界面
 */
public class StateShowActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    /*定义的mystateshowLayout*/
    private MyStateShowLayout myStateShowLayout1;
    private MyStateShowLayout myStateShowLayout2;
    private MyStateShowLayout myStateShowLayout3;
    private MyStateShowLayout myStateShowLayout4;
    private MyStateShowLayout myStateShowLayout5;
    private MyStateShowLayout myStateShowLayout6;
    private MyStateShowLayout myStateShowLayout7;
    private MyStateShowLayout myStateShowLayout8;
    private MyStateShowLayout myStateShowLayout9;
    private MyStateShowLayout myStateShowLayout10;
    private MyStateShowLayout myStateShowLayout11;
    private MyStateShowLayout myStateShowLayout12;
    private MyStateShowLayout myStateShowLayout13;
    private MyStateShowLayout myStateShowLayout14;
    private MyStateShowLayout myStateShowLayout15;
    private MyStateShowLayout myStateShowLayout16;
    private Button btn_stateToThrowOver;

    /*监听器*/
    private Sttys0Receiver sttys0Receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_show);
        context = StateShowActivity.this;
        init();
        //启动服务
        startService(new Intent(StateShowActivity.this, SerialPortTtys0Service.class));
        //注册广播接收器
        //这里通过Handler解决android 的ANR 问题  非常关键 一般不推荐直接在onReceive中去直接操作activity  用handler让UIthread工作
        sttys0Receiver = new Sttys0Receiver(new Handler());
        IntentFilter filterTtys0= new IntentFilter();
        filterTtys0.addAction("com.lzh.Service.SerialPortTtys0Service");
        StateShowActivity.this.registerReceiver(sttys0Receiver,filterTtys0);
    }

    @Override
    protected void onDestroy(){
        //结束服务
        stopService(new Intent(StateShowActivity.this,SerialPortTtys1Service.class));
        if(sttys0Receiver != null){
            unregisterReceiver(sttys0Receiver);
            //及时销毁广播 非常必要 receiver绑定了activity的引用
            // ，如果不及时销毁广播那么会造成内存泄露，即大对象在持久代中一直存在
        }
        super.onDestroy();
    }
    public void init(){
        myStateShowLayout1 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout1);
        myStateShowLayout2 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout2);
        myStateShowLayout3 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout3);
        myStateShowLayout4 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout4);
        myStateShowLayout5 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout5);
        myStateShowLayout6 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout6);
        myStateShowLayout7 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout7);
        myStateShowLayout8 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout8);
        myStateShowLayout9 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout9);
        myStateShowLayout10 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout10);
        myStateShowLayout11 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout11);
        myStateShowLayout12 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout12);
        myStateShowLayout13 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout13);
        myStateShowLayout14 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout14);
        myStateShowLayout15 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout15);
        myStateShowLayout16 = (MyStateShowLayout)findViewById(R.id.mystateShowLayout16);


        btn_stateToThrowOver = (Button)findViewById(R.id.btn_stateToThrowOver);
        btn_stateToThrowOver.setOnClickListener(this);

        myStateShowLayout1.tv_swtichingState.setText("开关量1");
        myStateShowLayout2.tv_swtichingState.setText("开关量2");
        myStateShowLayout3.tv_swtichingState.setText("开关量3");
        myStateShowLayout4.tv_swtichingState.setText("开关量4");
        myStateShowLayout5.tv_swtichingState.setText("开关量5");
        myStateShowLayout6.tv_swtichingState.setText("开关量6");
        myStateShowLayout7.tv_swtichingState.setText("开关量7");
        myStateShowLayout8.tv_swtichingState.setText("开关量8");
        myStateShowLayout9.tv_swtichingState.setText("开关量9");
        myStateShowLayout10.tv_swtichingState.setText("开关量10");
        myStateShowLayout11.tv_swtichingState.setText("开关量11");
        myStateShowLayout12.tv_swtichingState.setText("开关量12");
        myStateShowLayout13.tv_swtichingState.setText("开关量13");
        myStateShowLayout14.tv_swtichingState.setText("开关量14");
        myStateShowLayout15.tv_swtichingState.setText("开关量15");
        myStateShowLayout16.tv_swtichingState.setText("开关量16");


    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_stateToThrowOver:{
                Intent intent = new Intent(StateShowActivity.this,ThrowOverActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
    /*接收广播的类*/
    public class Sttys0Receiver extends BroadcastReceiver{
        private final Handler handler;//Handler used to execute code on UI thread

        public Sttys0Receiver(Handler handler){
            this.handler = handler;
        }
        @Override
        public void onReceive(Context context,final  Intent intent) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = intent.getExtras();
                    int[] result_temp = bundle.getIntArray("resultStateArr");
                    /* stateShow(result_temp);*/
           /*状态量改变  0x00变绿色 0x01变红色*/
                    if (result_temp[0] == 0x01) {
                        myStateShowLayout1.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout1.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }

                    if (result_temp[1] == 0x01) {
                        myStateShowLayout2.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout2.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[2] == 0x01) {
                        myStateShowLayout3.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout3.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[3] == 0x01) {
                        myStateShowLayout4.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout4.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[4] == 0x01) {
                        myStateShowLayout5.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout5.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[5] == 0x01) {
                        myStateShowLayout6.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout6.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[6] == 0x01) {
                        myStateShowLayout7.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout7.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[7] == 0x01) {
                        myStateShowLayout8.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout8.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[8] == 0x01) {
                        myStateShowLayout9.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout9.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[9] == 0x01) {
                        myStateShowLayout10.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout10.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[10] == 0x01) {
                        myStateShowLayout11.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout11.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[11] == 0x01) {
                        myStateShowLayout12.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout12.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[12] == 0x01) {
                        myStateShowLayout13.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout13.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[13] == 0x01) {
                        myStateShowLayout14.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout14.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[14] == 0x01) {
                        myStateShowLayout15.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout15.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }
                    if (result_temp[15] == 0x01) {
                        myStateShowLayout16.tv_swtichingShow.setBackgroundColor(Color.RED);
                    } else {
                        myStateShowLayout16.tv_swtichingShow.setBackgroundColor(Color.GREEN);
                    }

                }
            });
            /*Color.parseColor("##53FF53"*//*绿*//*)*/

        }
    }
/*注意：静态方法无法直接调用对象中的成员变量，静态方法是类级别的东西 而成员变量时对象级别的东西
* 这样就会导致一个现象静态方法无法直接调用成员变量，而成员变量可以被成员函数直接访问，因为默认
* 成员函数和成员对象是属于一个类中的，所以可以访问。那么如果需要解决静态方法中调用成员函数的方法的话需要
* 在静态方法中实例化一个对象后在访问该对象的静态方法*/
    /*    *//*根据接收到的int【】 去改变stateshow的状态 长度16*//*

    public static void stateShow(int[] iArr){
        if(iArr[0]==0x01){

        }
    }*/

}

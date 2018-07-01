package com.game.serialport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.MyDemo.MyAnalogShowLayout;
import com.lzh.MyDemo.MyDialog;
import com.lzh.Service.SerialPortTtys1Service;
import com.lzh.constant.ConstantValue;
import com.lzh.tools.Transform;

/**
 * lzh 20180607 直流模拟量界面
 */
public class dcAnalogActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
/*  private TextView tv_firstDcAnalog_name;
    private TextView tv_firstDcAnalog_Value;
    private TextView tv_firstDcAnalog_unit;
    private Button btn_firstDcAnalog_config;*/

    private MyAnalogShowLayout myAnalogShowLayout1;
    private MyAnalogShowLayout myAnalogShowLayout2;
    private MyAnalogShowLayout myAnalogShowLayout3;
    private MyAnalogShowLayout myAnalogShowLayout4;
    private MyAnalogShowLayout myAnalogShowLayout5;
    private MyAnalogShowLayout myAnalogShowLayout6;
    private MyAnalogShowLayout myAnalogShowLayout7;
    private MyAnalogShowLayout myAnalogShowLayout8;
    private MyAnalogShowLayout myAnalogShowLayout9;
    private MyAnalogShowLayout myAnalogShowLayout10;

    private Button btn_configGoback;

    private boolean input_flag = true;

    /*监听器*/
    private Sttys1Receiver sttys1Receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc_analog);
        context = dcAnalogActivity.this;
        init();

        //注册广播接收器
        //这里需要注意下 new handler将其交给UI线程工作
        sttys1Receiver = new Sttys1Receiver(new Handler());
        IntentFilter filterTtys1 = new IntentFilter();
        filterTtys1.addAction("com.lzh.Service.SerialPortTtys1Service");
        dcAnalogActivity.this.registerReceiver(sttys1Receiver,filterTtys1);

    }

    @Override
    protected void onDestroy(){

        if(sttys1Receiver != null){
            unregisterReceiver(sttys1Receiver);
            //及时销毁广播 非常必要 receiver绑定了activity的引用
            // ，如果不及时销毁广播那么会造成内存泄露，即大对象在持久代中一直存在
        }
        super.onDestroy();
    }

    public void init(){
/*        tv_firstDcAnalog_name = (TextView)this.findViewById(R.id.tv_firstDcAnalog_name);
        tv_firstDcAnalog_Value = (TextView)this.findViewById(R.id.tv_firstDcAnalog_Value);//监听外部参数
        tv_firstDcAnalog_unit = (TextView)this.findViewById(R.id.tv_firstDcAnalog_unit);
        btn_firstDcAnalog_config = (Button)this.findViewById(R.id.btn_firstDcAnalog_config);
        btn_firstDcAnalog_config.setOnClickListener(this);*/

        btn_configGoback = (Button)this.findViewById(R.id.btn_configGoback);
        btn_configGoback.setOnClickListener(this);

        myAnalogShowLayout1 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout1);
        myAnalogShowLayout2 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout2);
        myAnalogShowLayout3 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout3);
        myAnalogShowLayout4 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout4);
        myAnalogShowLayout5 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout5);
        myAnalogShowLayout6 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout6);
        myAnalogShowLayout7 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout7);
        myAnalogShowLayout8 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout8);
        myAnalogShowLayout9 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout9);
        myAnalogShowLayout10 = (MyAnalogShowLayout)findViewById(R.id.myAnalogShowLayout10);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
/*            case R.id.btn_firstDcAnalog_config:{
                MyDialog myDialog = new MyDialog(this, "配置参数", new MyDialog.OnMyDialogListener() {
                    @Override
                    public void back(String str_name, String str_unit, int parameter) {
                        tv_firstDcAnalog_name.setText(str_name);
                        tv_firstDcAnalog_unit.setText(str_unit);
                        ConstantValue.dcAnalog_first_parameter = parameter;

                    }
                });
                myDialog.show();

            }
            break;*/

            case R.id.btn_configGoback:{
                Intent intent = new Intent(dcAnalogActivity.this,ThrowOverActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
    /*接收广播的类*/
    public class Sttys1Receiver extends BroadcastReceiver{
        private final Handler handler;//handler used to execute code on UI thread

        public Sttys1Receiver(Handler handler){
            this.handler = handler;
        }
        @Override
        public void onReceive(Context context,final Intent intent){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = intent.getExtras();
                    long[] result_temp = bundle.getLongArray("resultDCArr");
                    myAnalogShowLayout1.tv_DcAnalog_Value.setText(result_temp[0]+"");
                    myAnalogShowLayout2.tv_DcAnalog_Value.setText(result_temp[1]+"");
                    myAnalogShowLayout3.tv_DcAnalog_Value.setText(result_temp[2]+"");
                    myAnalogShowLayout4.tv_DcAnalog_Value.setText(result_temp[3]+"");
                    myAnalogShowLayout5.tv_DcAnalog_Value.setText(result_temp[4]+"");
                    myAnalogShowLayout6.tv_DcAnalog_Value.setText(result_temp[5]+"");
                    myAnalogShowLayout3.tv_DcAnalog_Value.setText(result_temp[6]+"");
                    myAnalogShowLayout4.tv_DcAnalog_Value.setText(result_temp[7]+"");
                    myAnalogShowLayout5.tv_DcAnalog_Value.setText(result_temp[8]+"");
                    myAnalogShowLayout6.tv_DcAnalog_Value.setText(result_temp[9]+"");
                }
            });

        }
    }
}

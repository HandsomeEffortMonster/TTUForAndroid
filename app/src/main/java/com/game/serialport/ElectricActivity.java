package com.game.serialport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.Service.SerialPortService;
import com.lzh.constant.ControlFlag;
import com.lzh.handler.Att7022eHandler;
import com.lzh.tools.Transform;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialUtilOld;

/**
 * lzh 20180604 电能实时信息界面
 */
public class ElectricActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView voltageA;
    private TextView currentA;
    private TextView averagePowerA;
    private TextView apparentA;
    private TextView voltageB;
    private TextView currentB;
    private TextView averagePowerB;
    private TextView apparentB;
    private TextView voltageC;
    private TextView currentC;
    private TextView averagePowerC;
    private TextView apparentC;
    private Button reback;
    private Button history;
/*    private SerialUtilOld serialPort2;
    private String path = "/dev/ttyS2";
    private int baudrate = 115200;
    private ReadThread readThread;*/

    /*监听器*/
    private Sttys2Receiver sttys2Receiver;
    private byte[] error = new byte[]{-1,-1};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        init();
        //启动服务
        startService(new Intent(ElectricActivity.this, SerialPortService.class));
        //动态注册注册广播接收器  需要响应位置销毁广播
        //new Handler需要注意一下详情见stateShowActivity
        sttys2Receiver = new Sttys2Receiver(new Handler());
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lzh.Service.SerialPortService");
        ElectricActivity.this.registerReceiver(sttys2Receiver,filter);

    }
    @Override
    protected void onDestroy() {
        //结束服务
        stopService(new Intent(ElectricActivity.this,SerialPortService.class));
        if(sttys2Receiver != null){
            unregisterReceiver(sttys2Receiver);//这销毁广播
        }
        super.onDestroy();
    }
    protected void init(){
        voltageA = (TextView)findViewById(R.id.text_voltageA);
        currentA = (TextView)findViewById(R.id.text_currentA);
        averagePowerA = (TextView)findViewById(R.id.text_averagePowerA) ;
        apparentA = (TextView)findViewById(R.id.text_apparentPowerA);
        voltageB = (TextView)findViewById(R.id.text_voltageB);
        currentB = (TextView)findViewById(R.id.text_currentB);
        averagePowerB = (TextView)findViewById(R.id.text_averagePowerB) ;
        apparentB = (TextView)findViewById(R.id.text_apparentPowerB);
        voltageC = (TextView)findViewById(R.id.text_voltageC);
        currentC = (TextView)findViewById(R.id.text_currentC);
        averagePowerC = (TextView)findViewById(R.id.text_averagePowerC) ;
        apparentC = (TextView)findViewById(R.id.text_apparentPowerC);

        reback = (Button)findViewById(R.id.reback);
        history = (Button)findViewById( R.id.btn_history);

        reback.setOnClickListener(this);
        history.setOnClickListener(this);
/*        try{
            serialPort2 = new SerialUtilOld(path,baudrate,0);
        }catch (NullPointerException e){
            Toast.makeText(ElectricActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        //开启串口读写线程
        if(readThread==null){
            readThread = new ReadThread();
            readThread.start();
        }*/
    }

    /*绑定监听事件*/
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.reback:{
                Intent intent = new Intent(ElectricActivity.this,ThrowOverActivity.class);
                startActivity(intent);

            }
            break;
            case  R.id.btn_history:{
                Intent intent2 = new Intent(ElectricActivity.this,HistoricalActivity.class);
                startActivity(intent2);
            }
        }
    }

    public class Sttys2Receiver extends BroadcastReceiver{
        private final Handler handler;//Handler used to execute code on UI thread
        public Sttys2Receiver(Handler handler){
            this.handler = handler;
        }
        @Override
        public void onReceive(Context context, final Intent intent){

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = intent.getExtras();
                    long[] result = bundle.getLongArray("resultArr");
                    voltageA.setText(result[0]+"V");
                    currentA.setText(result[1]+"A");
                    averagePowerA.setText(result[2]+"W");
                    apparentA.setText(result[3]+"W");
                    voltageB.setText(result[4]+"V");
                    currentB.setText(result[5]+"A");
                    averagePowerB.setText(result[6]+"W");
                    apparentB.setText(result[7]+"W");
                    voltageC.setText(result[8]+"V");
                    currentC.setText(result[9]+"A");
                    averagePowerC.setText(result[10]+"W");
                    apparentC.setText(+result[11]+"W");
                }
            });

        }
    }
/*
    */
/*串口读写线程函数*//*

    private class ReadThread extends Thread{
        @Override
        public void run(){
            super.run();
            if(ControlFlag.electricControlFlag){
                while(!Thread.currentThread().isInterrupted()){
                    try{
                        byte[] data = serialPort2.getDataByte();
                        if(data!=null){
                            onDataReceived(data);
                        }
                    }catch(NullPointerException e){
                        onDataReceived(error);
                        e.printStackTrace();
                        readThread.interrupt();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        onDataReceived(error);
                        readThread.interrupt();
                    }
                }
            }

        }
    }

    */
/*接收回调入口*//*

    protected void onDataReceived(final byte[] data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示出来
                if(data[0]==-1&&data[1]==-1){
                    Toast.makeText(ElectricActivity.this, "串口设置有误，无法接收", Toast.LENGTH_SHORT).show();
                }else {
                    //receive_tv.append(bytesToHexFun1(data));
                    int[] receive =  Transform.toReceiveNews(data);
                    long [] result = Att7022eHandler.handleInformation(receive);
                    // (new Integer(data[i]<0?256+data[i]:data[i]).toString())
                    voltageA.setText("A相电压："+result[0]+"V");
                    currentA.setText("A相电流:"+result[1]+"A");
                    averagePowerA.setText("A相有功功率:"+result[2]+"W");
                    apparentA.setText("A相视在功率："+result[3]+"W");
                    voltageB.setText("B相电压："+result[4]+"V");
                    currentB.setText("B相电流:"+result[5]+"A");
                    averagePowerB.setText("B相有功功率:"+result[6]+"W");
                    apparentB.setText("B相视在功率："+result[7]+"W");
                    voltageC.setText("C相电压："+result[8]+"V");
                    currentC.setText("C相电流:"+result[9]+"A");
                    averagePowerC.setText("C相有功功率:"+result[10]+"W");
                    apparentC.setText("C相视在功率："+result[11]+"W");
                }
            }
        });
    }
*/

}

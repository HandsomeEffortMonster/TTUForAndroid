package com.game.serialport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android_serialport_api.SerialUtilOld;

/**
 * 没用上
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView receive_tv;
    private Button receive_b;
    private EditText send_et;
    private Button sendt_b;
    private Button stop_b;
    private ReadThread readThread;
    private int size=-1;
    private static final String TAG = "MainActivity";
    private SerialUtilOld serialUtilOld;
    private String path="/dev/ttyS2";
    private int baudrate=115200;
    private int flags=0;
    private byte[] bytes= new byte[]{-2,0x02,0x03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    protected void init(){
        receive_tv=(TextView)findViewById(R.id.main_recive_tv);
        receive_b=(Button) findViewById(R.id.main_recive_b);
        send_et=(EditText)findViewById(R.id.main_send_et);
        sendt_b=(Button)findViewById(R.id.main_send_b);
        stop_b=(Button)findViewById(R.id.main_stop_b);
        receive_b.setOnClickListener(this);
        sendt_b.setOnClickListener(this);
        stop_b.setOnClickListener(this);
        try {
            //设置串口号、波特率，
            serialUtilOld =new SerialUtilOld(path,baudrate,0);
        } catch (NullPointerException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_recive_b:{
//                if(size!=-1){
                    readThread=new ReadThread();
                    readThread.start();
//                }
            }
                break;
            case R.id.main_send_b:{
                 String context=send_et.getText().toString();
                Log.d(TAG, "onClick: "+context);
                try {
                    serialUtilOld.setData(
                            //context.getBytes()
                            bytes
                    );
                } catch (NullPointerException e) {
                    Toast.makeText(MainActivity.this, "串口设置有误，无法发送", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
                break;
            case R.id.main_stop_b:{
                //停止接收
                readThread.interrupt();
                receive_tv.setText("");
            }
                break;
        }

    }


    private class ReadThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()){
                try {
                    byte[] data= serialUtilOld.getDataByte();
                    if(data!=null) onDataReceived(new String(data,"UTF-8"));
                }catch (NullPointerException e){
                    onDataReceived("-1");
                    e.printStackTrace();
                    readThread.interrupt();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    onDataReceived("-1");
                    readThread.interrupt();
                }

            }
        }
    }
    protected void onDataReceived(final String data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示出来
                if("-1".equals(data)){
                    Toast.makeText(MainActivity.this, "串口设置有误，无法接收", Toast.LENGTH_SHORT).show();
                }else {
                    receive_tv.append(data);
                }
            }
        });
    }


}

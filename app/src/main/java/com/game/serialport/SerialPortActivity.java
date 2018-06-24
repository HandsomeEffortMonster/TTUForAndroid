package com.game.serialport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.constant.ControlFlag;
import com.lzh.tools.Transform;

import android_serialport_api.SerialUtilOld;
/**
 * lzh 20180604 中间转义界面不适用了
 */
public class SerialPortActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView receive_tv;
    private Button receive_b;
    private EditText send_et;
    private Button sendt_b;
    private Button stop_b;
    private Button goElectric;
    private Button btn_goDCConfig;

    private static final String TAG = "SerialPort";
    private ReadThread readThread;
    private  int size = -1;
    private SerialUtilOld serialUtilOld;
    private String path="/dev/ttyS2";
    private int baudrate=115200;
    private int[] bytes = new int[]{0x01,0x03,0x78,0x39,0x81,0x97,0xf8,0xf2,0xa22,0x80,0xff,0x7f,0x00};
    private byte[] error = new byte[]{-1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_port);
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    protected void init(){
        receive_tv = (TextView)findViewById(R.id.main_recive_tv);
        receive_b = (Button) findViewById(R.id.main_recive_b);
        send_et = (EditText)findViewById(R.id.main_send_et);
        sendt_b = (Button)findViewById(R.id.main_send_b);
        stop_b = (Button)findViewById(R.id.main_stop_b);
        goElectric = (Button)findViewById(R.id.main_goElectric);
        btn_goDCConfig= (Button)findViewById(R.id.btn_goDCConfig);


        receive_b.setOnClickListener(this);
        sendt_b.setOnClickListener(this);
        stop_b.setOnClickListener(this);
        goElectric.setOnClickListener(this);
        btn_goDCConfig.setOnClickListener(this);

        try {
            //设置串口号、波特率，
            serialUtilOld =new SerialUtilOld(path,baudrate,0);
        } catch (NullPointerException e) {
            Toast.makeText(SerialPortActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_recive_b:{
//                if(size!=-1){
                if(readThread==null){
                    readThread=new ReadThread();
                    readThread.start();
                }
//                }
            }
            break;
            case R.id.main_send_b:{
                String context=send_et.getText().toString();
                Log.d(TAG, "onClick: "+context);
                try {
                    serialUtilOld.setData(
                            //context.getBytes()
                            Transform.toCmd(bytes)
                    );
                } catch (NullPointerException e) {
                    Toast.makeText(SerialPortActivity.this, "串口设置有误，无法发送", Toast.LENGTH_SHORT).show();
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
            case R.id.main_goElectric:{
                Intent intent = new Intent(SerialPortActivity.this,ElectricActivity.class);
                startActivity(intent);
                ControlFlag.serialPortControlFlag = false;
                ControlFlag.electricControlFlag = true;
            }
            break;
            case R.id.btn_goDCConfig:{
                Intent intent = new Intent(SerialPortActivity.this,dcAnalogActivity.class);
                startActivity(intent);
            }
        }

    }



    private class ReadThread extends Thread{
        @Override
        public void run(){
            super.run();
            if(ControlFlag.serialPortControlFlag){
                while(!Thread.currentThread().isInterrupted()){
                    try{
                        byte[] data = serialUtilOld.getDataByte();
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

    protected void onDataReceived(final byte[] data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示出来
                if(data[0]==-1&&data[1]==-1){
                    Toast.makeText(SerialPortActivity.this, "串口设置有误，无法接收", Toast.LENGTH_SHORT).show();
                }else {
                                //receive_tv.append(bytesToHexFun1(data));
                    int[] receive = Transform.toReceiveNews(data);
                    receive_tv.append(String.valueOf(receive[0])+String.valueOf(receive[1])+String.valueOf(receive[2])+String.valueOf(receive[3]));
                                       // (new Integer(data[i]<0?256+data[i]:data[i]).toString())
                }
            }
        });
    }


    /*接收的16进制转换成字符串*/
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }


}

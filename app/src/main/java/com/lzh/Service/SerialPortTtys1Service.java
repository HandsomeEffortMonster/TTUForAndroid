package com.lzh.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lzh.handler.Att7022eHandler;
import com.lzh.handler.DCAnalogHandler;
import com.lzh.tools.Transform;

import android_serialport_api.SerialUtil;
import android_serialport_api.SerialUtilOld;

/**
 * Created by LZH on 2018/5/31.
 * SerialPort  ttyS1 冗余度比较  需优
 */
public class SerialPortTtys1Service extends Service {

    SerialUtilOld serialPort_ttyS1;
    private String path = "/dev/ttyS1";
    private int baudrate = 115200;
    private ReadThread readThread;
    private byte[] error = new byte[]{-1,-1};
    /*
    * 广播传值 onbind
    * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
/*        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");*/
        return null;
    }
    /*
    * 创建时建立线程和serialport对象
    * */
    @Override
    public void onCreate(){
        try {
            if(serialPort_ttyS1 == null){
                serialPort_ttyS1 = new SerialUtilOld(path,baudrate,0);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(readThread == null){
            readThread = new ReadThread();
            readThread.start();
        }
        Log.d("Sttys1Service","oncreate executed");
    }

    @Override
    public void onDestroy(){
        if(readThread == null){
            readThread.interrupt();
        }
        if(serialPort_ttyS1 != null){
            serialPort_ttyS1 = null;
        }
        super.onDestroy();
        Log.d("Sttys1Service","destory executed");
    }
    @Override
    public  int onStartCommand(Intent intent,int flags,int startId){
        Log.d("serialPortTtyS1","service go to run");
        return  super.onStartCommand(intent,flags,startId);
    }

    private class ReadThread extends Thread{
        @Override
        public void run(){
            super.run();
            while(!isInterrupted()){
                try {
                    byte[] data = serialPort_ttyS1.getData();
                    if(data != null){
                        onDataReceived(data);
                    }
                }catch (NullPointerException e){
                    onDataReceived(error);
                    e.printStackTrace();
                    readThread.interrupt();
                }catch (Exception e){
                    e.printStackTrace();
                    onDataReceived(error);
                    readThread.interrupt();
                }
            }
        }
    }
    /*
    * 接受线程回调数据处理 主要处理逻辑不同与ttys1
    * */
    protected void onDataReceived(final byte[] data){
        if(data[0]==-1 && data[1]==-1){
            Log.e("serialPortttyS1","error");
        }else {
            int [] receive = Transform.toReceiveNews(data);
            long [] result_temp = DCAnalogHandler.handleInformaton(receive);
            Log.d("SerialPortttys1Service","receive int[]"+receive[0]);

            //广播传值
            Intent intentTtyS1 = new Intent();
            intentTtyS1.putExtra("resultDCArr",result_temp);
            intentTtyS1.setAction("com.lzh.Service.SerialPortTtys1Service");
            sendBroadcast(intentTtyS1);
        }
    }
}

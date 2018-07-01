package com.lzh.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lzh.handler.StateHandler;
import com.lzh.tools.Transform;

import android_serialport_api.SerialUtilOld;

/**
 * created by LZH on 2018/06/08
 * SerialPort ttyS0 冗余度与 1 2相同  可考虑合并
 */
public class SerialPortTtys0Service extends Service {

    private static final String TAG ="SerialPortTtys0Service";

    SerialUtilOld serialPort_ttyS0;
    private String path = "/dev/ttyS0";
    private int baudrate = 115200;
    private ReadThread readThread;
    private byte[] error = new byte[]{-1,-1};
    /*
    * 广播传值
    * */
    @Override
    public IBinder onBind(Intent intent) {
        /*// TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");*/
        return null;
    }
    /*
    * 创建时建立线程和serialport对象
    * */
    @Override
    public void  onCreate(){
        try {
            if(serialPort_ttyS0 == null){
                serialPort_ttyS0 = new SerialUtilOld(path,baudrate,0);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(readThread == null){
            readThread = new ReadThread();
            readThread.start();
        }
        Log.d(TAG,"oncreate executed");
    }

    @Override
    public void onDestroy(){
        if(readThread == null){
            readThread.interrupt();
        }
        if(serialPort_ttyS0 != null){
            serialPort_ttyS0 = null;
        }
        super.onDestroy();
        Log.d(TAG,"destory executed");
    }

    @Override
    public  int onStartCommand(Intent intent,int flags,int startId){
        Log.d(TAG,"service go to run");
        return  super.onStartCommand(intent,flags,startId);
    }

    private class ReadThread extends Thread{
        @Override
        public void run(){
            super.run();
            while(!isInterrupted()){
                try{
                    byte[] data = serialPort_ttyS0.getData();
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
    * 线程回调 传值
    * */
    protected void onDataReceived(final  byte[] data){
        if(data[0]==-1 && data[1]==-1){
            Log.e(TAG,"error");
        }else {
            int [] receive = Transform.toReceiveNews(data);
            int [] result_temp = StateHandler.handleInformaton(receive);
            Log.d(TAG,"receive int[]"+receive[0]);

            //广播传值
            Intent intentTtyS0 = new Intent();
            intentTtyS0.putExtra("resultStateArr",result_temp);
            intentTtyS0.setAction("com.lzh.Service.SerialPortTtys0Service");
            sendBroadcast(intentTtyS0);
        }
    }
}

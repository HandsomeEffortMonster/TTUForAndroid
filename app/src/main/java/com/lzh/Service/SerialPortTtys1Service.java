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

    private static final String TAG ="SerialPortTtys1Service";
    SerialUtilOld serialPort_ttyS1;
    private String path = "/dev/ttyS1";
    private int baudrate = 115200;
    private ReadThread readThread;
    private byte[] error = new byte[]{-1,-1};
    /*
    * 广播传值 onbind
    * 绑定服务时才会调用
     * 必须要实现的方法
    * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
/*        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");*/
        return null;
    }
    /*
    * 创建时建立线程和serialport对象  首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
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
        Log.d(TAG,"oncreate executed");
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
        Log.d(TAG ,"destory executed");
    }

    /**
     * 每次都会在服务启动后调用，service start
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public  int onStartCommand(Intent intent,int flags,int startId){
        Log.d(TAG ,"service go to run");
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
            Log.e(TAG ,"error");
        }else {
            int [] receive = Transform.toReceiveNews(data);
            long [] result_temp = DCAnalogHandler.handleInformaton(receive);
            Log.d(TAG ,"receive int[]"+receive[0]);

            //广播传值
            Intent intentTtyS1 = new Intent();
            intentTtyS1.putExtra("resultDCArr",result_temp);
            intentTtyS1.setAction("com.lzh.Service.SerialPortTtys1Service");
            sendBroadcast(intentTtyS1);
        }
    }
}

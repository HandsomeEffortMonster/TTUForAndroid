package com.lzh.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.lzh.Bean.ElectricBean;
import com.lzh.dao.ElectricDao;
import com.lzh.handler.Att7022eHandler;
import com.lzh.tools.OrmLiteHelper;
import com.lzh.tools.Transform;

/*import java.sql.Date;*/

import java.text.SimpleDateFormat;
import java.util.Date;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialUtilOld;

/**
 * Created by LZH on 2018/5/31.
 * SerialPort  ttyS2
 */

public class SerialPortService extends Service{

    SerialUtilOld serialPort_ttyS2;
    private String path = "/dev/ttyS2";
    private int baudrate = 115200;
    private ReadThread readThread;
    private byte[] error = new byte[]{-1,-1};

    /*
    * 广播传值 onbind
    * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
    * 创建时建立线程和serialport对象
    * */
    @Override
    public void onCreate(){
        try{
            if(serialPort_ttyS2==null){
                serialPort_ttyS2 = new SerialUtilOld(path,baudrate,0);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(readThread==null){
            readThread = new ReadThread();
            readThread.start();
        }
        Log.d("Sttys2Service","oncreate executed");
    }

    @Override
    public void onDestroy(){
        if(readThread!=null){
            readThread.interrupt();
        }
        if(serialPort_ttyS2 != null){
            serialPort_ttyS2= null;
        }
        super.onDestroy();
        Log.d("Sttys2Service","destory executed");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d("serialPortTtyS2","service go to run");
        return super.onStartCommand(intent,flags,startId);
    }

    private class ReadThread extends Thread{
        @Override
        public void run(){
            super.run();
            while(!isInterrupted()){
                try{
                    byte[] data = serialPort_ttyS2.getDataByte();
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

    /*
    * 接受线程回调数据进行处理
    * */
    protected void onDataReceived(final byte[] data){
        if(data[0]==-1&&data[1]==-1){
            Log.e("serialPortttyS2","error");
        }else {

            //receive_tv.append(bytesToHexFun1(data));
            int[] receive =  Transform.toReceiveNews(data);
            long [] result = Att7022eHandler.handleInformation(receive);
            // (new Integer(data[i]<0?256+data[i]:data[i]).toString())
            Log.d("SerialPortttys2Service","receive int[]"+receive[0]);




            //广播传值
            Intent intentTtyS2 = new Intent();
            intentTtyS2.putExtra("resultArr",result);//puutExtra传值
            intentTtyS2.setAction("com.lzh.Service.SerialPortService");
            sendBroadcast(intentTtyS2);

            //插入数据库
            ElectricDao electricDao = new ElectricDao(getApplicationContext());
            ElectricBean electricBean = new ElectricBean();

            electricBean.setVoltageA(result[0]);
            electricBean.setCurrentA(result[1]);
            electricBean.setAveragePowerA(result[2]);
            electricBean.setApparentA(result[3]);
            electricBean.setVoltageB(result[4]);
            electricBean.setCurrentB(result[5]);
            electricBean.setAveragePowerB(result[6]);
            electricBean.setApparentB(result[7]);
            electricBean.setVoltageC(result[8]);
            electricBean.setCurrentC(result[9]);
            electricBean.setAveragePowerC(result[10]);
            electricBean.setApparentC(result[11]);
            /*插入实时日期*/
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str_date = sdf.format(d).toString();
            electricBean.setCreateTime(str_date);

            electricDao.add(electricBean);
            Log.i("electricBean",electricBean.toString());
        }
    }



}

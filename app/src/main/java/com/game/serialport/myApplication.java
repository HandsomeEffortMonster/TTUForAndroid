package com.game.serialport;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

import com.lzh.Service.SerialPortService;
import com.lzh.Service.SerialPortTtys0Service;
import com.lzh.Service.SerialPortTtys1Service;

/**
 * Created by UPC on 2018/5/31.
 */

public class myApplication extends Application {

    private static final String TAG ="myApplication";
    @Override
    public void onCreate() {
        // 程序创建的时候执行
        Log.d(TAG, "onCreate");
        //启动电能信息ttys服务
        startService(new Intent(getApplicationContext(), SerialPortService.class));
        //启动服务 直流模拟量通道串口监听
        startService(new Intent(getApplicationContext(), SerialPortTtys1Service.class));
        //启动状态量服务监听
        startService(new Intent(getApplicationContext(), SerialPortTtys0Service.class));
        super.onCreate();
    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");
        //结束服务
        stopService(new Intent(getApplicationContext(),SerialPortService.class));
        //结束服务
        stopService(new Intent(getApplicationContext(),SerialPortTtys1Service.class));
        //结束服务
        stopService(new Intent(getApplicationContext(),SerialPortTtys1Service.class));
        super.onTerminate();
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}

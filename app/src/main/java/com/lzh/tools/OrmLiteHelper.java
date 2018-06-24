package com.lzh.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lzh.Bean.ElectricBean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LZH on 2018/6/1.
 * 数据库帮助类
 */

public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {
    /*
    * 数据库名字
    * */
    private static final String DB_NAME = "Ts.db";
    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 3;

    /**
     * 用来存放Dao的map
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    private static OrmLiteHelper instance;

    /**
     * 获取单例的 ormLiteHelper
     * @param context
     * @return
     */
    public static synchronized OrmLiteHelper getHelper(Context context){
        context = context.getApplicationContext();
        if(instance == null){
            synchronized (OrmLiteHelper.class){
                if(instance == null){
                    instance = new OrmLiteHelper(context);
                }
            }
        }
        return instance;
    }
    /*
    * 构造方法
    * @param context
    * */
    public OrmLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    /*
    * 这里创造表
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource){
        //创建表
        try{
            TableUtils.createTable(connectionSource, ElectricBean.class);
            Log.i("ElectricBean","create table");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param sqLiteDatabase 数据库
     * @param connectionSource 数据源
     * @param oldVersion 老版本、、用于更新操作
     * @param newVersion 新版本
     * 这里进行更新表操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,ConnectionSource connectionSource,
                          int oldVersion,int newVersion){
        try{
            TableUtils.dropTable(connectionSource,ElectricBean.class,true);
            TableUtils.createTable(connectionSource, ElectricBean.class);
            Log.i("Electricdb","create onUpgrade");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 通过类的加载获得指定dao
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String className = clazz.getSimpleName();
        if(daos.containsKey(className)){
            dao = daos.get(className);
        }
        if(dao == null){
            dao = super.getDao(clazz);
            daos.put(className,dao);
        }
        return dao;
    }

    /**
     * close 关闭释放资源
     */
    @Override
    public void close(){
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }


}

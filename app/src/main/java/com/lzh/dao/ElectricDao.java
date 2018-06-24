package com.lzh.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.lzh.Bean.ElectricBean;
import com.lzh.tools.OrmLiteHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZH on 2018/6/1.
 * ElectricBean dao操作类
 * 进行一系列CRUD操作 xxxxxx
 */

public class ElectricDao {
    private Context context;
    private Dao<ElectricBean,Integer> electricDao;
    private OrmLiteHelper ormLiteHelper;

    /*
    * 构造方法
    * 获得ormsqlite帮助实例 通过class对象得到对应dao
    *
    * */
    public ElectricDao(Context context){
        this.context = context;
        try{
            ormLiteHelper = ormLiteHelper.getHelper(context);
            electricDao = ormLiteHelper.getDao(ElectricBean.class);
            Log.i("electricDao","create Successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /*
    * 添加一条记录
    * */
    public void add(ElectricBean electricBean){
        try{
            electricDao.create(electricBean);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /*
    * 删除一条记录
    * */
    public void delete(ElectricBean electricBean){
        try{
            electricDao.delete(electricBean);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /*
    * 查询一条记录
    * */
    public ElectricBean queryForId(int id){
        ElectricBean electricBean = null;
        try{
            electricBean = electricDao.queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return electricBean;
    }
    /*
    * 根据时间查找记录
    * */
    public List<ElectricBean> queryForTime(String str){
        List<ElectricBean> electricBeens = new ArrayList<>();
        try{
            /*这里的createTime是创建时间 对应ElectricBean中的createTime字段*/
            electricBeens = electricDao.queryBuilder().where().like("createTime","%"+str+"%").query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return electricBeens;
    }
    /*
    * 返回数据库中数据总数
    * */
    public long getCount(){
        long number = 0;
        try {
           number = electricDao.queryBuilder().countOf();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  number;
    }
    /*
    * 查询最近多少条数据
    * */
    public List<ElectricBean> queryForNum(long num){
        List<ElectricBean> electricBeens = new ArrayList<>();
        try{
            electricBeens = electricDao.queryBuilder().where().gt("_id",num).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  electricBeens;
    }
    /*
    * 查询所有的记录
    * */
    public List<ElectricBean> queryForAll(){
        List<ElectricBean> electricBeens = new ArrayList<>();
        try{
            electricBeens = electricDao.queryForAll();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return electricBeens;
    }

}

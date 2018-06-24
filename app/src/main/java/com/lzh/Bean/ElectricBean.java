package com.lzh.Bean;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.TimeStampType;
import com.j256.ormlite.table.DatabaseTable;
import com.lzh.dao.ElectricDao;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by lzh on 2018/6/1.
 * ormLite根据这个类生成对应的table，在根据这个getter与setter 操作private变量
 * 会根据private xx 生成对应字段
 */
@DatabaseTable(tableName = "electric_table")
public class ElectricBean implements Serializable{

    @DatabaseField(generatedId = true)
    private long _id;

    @DatabaseField(columnName = "voltageA",canBeNull = true)
    private long voltageA;
    @DatabaseField(columnName = "currentA",canBeNull = true)
    private long currentA;
    @DatabaseField(columnName = "averagePowerA",canBeNull = true)
    private long averagePowerA;
    @DatabaseField(columnName = "apparentA",canBeNull = true)
    private long apparentA;

    @DatabaseField(columnName = "voltageB",canBeNull = true)
    private long voltageB;
    @DatabaseField(columnName = "currentB",canBeNull = true)
    private long currentB;
    @DatabaseField(columnName = "averagePowerB",canBeNull = true)
    private long averagePowerB;
    @DatabaseField(columnName = "apparentB",canBeNull = true)
    private long apparentB;

    @DatabaseField(columnName = "voltageC",canBeNull = true)
    private long voltageC;
    @DatabaseField(columnName = "currentC",canBeNull = true)
    private long currentC;
    @DatabaseField(columnName = "averagePowerC",canBeNull = true)
    private long averagePowerC;
    @DatabaseField(columnName = "apparentC",canBeNull = true)
    private long apparentC;

    @DatabaseField(columnName = "createTime" )
    private String createTime;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getVoltageA() {
        return voltageA;
    }

    public void setVoltageA(long voltageA) {
        this.voltageA = voltageA;
    }

    public long getCurrentA() {
        return currentA;
    }

    public void setCurrentA(long currentA) {
        this.currentA = currentA;
    }

    public long getAveragePowerA() {
        return averagePowerA;
    }

    public void setAveragePowerA(long averagePowerA) {
        this.averagePowerA = averagePowerA;
    }

    public long getApparentA() {
        return apparentA;
    }

    public void setApparentA(long apparentA) {
        this.apparentA = apparentA;
    }

    public long getVoltageB() {
        return voltageB;
    }

    public void setVoltageB(long voltageB) {
        this.voltageB = voltageB;
    }

    public long getCurrentB() {
        return currentB;
    }

    public void setCurrentB(long currentB) {
        this.currentB = currentB;
    }

    public long getAveragePowerB() {
        return averagePowerB;
    }

    public void setAveragePowerB(long averagePowerB) {
        this.averagePowerB = averagePowerB;
    }

    public long getApparentB() {
        return apparentB;
    }

    public void setApparentB(long apparentB) {
        this.apparentB = apparentB;
    }

    public long getVoltageC() {
        return voltageC;
    }

    public void setVoltageC(long voltageC) {
        this.voltageC = voltageC;
    }

    public long getCurrentC() {
        return currentC;
    }

    public void setCurrentC(long currentC) {
        this.currentC = currentC;
    }

    public long getAveragePowerC() {
        return averagePowerC;
    }

    public void setAveragePowerC(long averagePowerC) {
        this.averagePowerC = averagePowerC;
    }

    public long getApparentC() {
        return apparentC;
    }

    public void setApparentC(long apparentC) {
        this.apparentC = apparentC;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ElectricBean{" +
                "_id=" + _id +
                ", voltageA=" + voltageA +
                ", currentA=" + currentA +
                ", averagePowerA=" + averagePowerA +
                ", apparentA=" + apparentA +
                ", voltageB=" + voltageB +
                ", currentB=" + currentB +
                ", averagePowerB=" + averagePowerB +
                ", apparentB=" + apparentB +
                ", voltageC=" + voltageC +
                ", currentC=" + currentC +
                ", averagePowerC=" + averagePowerC +
                ", apparentC=" + apparentC +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}

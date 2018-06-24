package com.lzh.handler;

/**
 * Created by LZH on 2018/5/30.
 * 采集板att7022获取的数据解析
 */

public class Att7022eHandler {
    public static long[] handleInformation(int[] data ){
        long[] result = new long[12];
        for(int i=0;i<data.length;i++){
            if(data[i]==0x5a&&data[i+1]==0xa5){
                result[0]=data[i+2]+data[i+3]*256+data[i+4]*256*256+data[i+5]*256*256*256;
                result[1]=data[i+6]+data[i+7]*256+data[i+8]*256*256+data[i+9]*256*256*256;
                result[2]=data[i+10]+data[i+11]*256+data[i+12]*256*256+data[i+13]*256*256*256;
                result[3]=data[i+14]+data[i+15]*256+data[i+16]*256*256+data[i+17]*256*256*256;
                result[4]=data[i+18]+data[i+19]*256+data[i+20]*256*256+data[i+21]*256*256*256;
                result[5]=data[i+22]+data[i+23]*256+data[i+24]*256*256+data[i+25]*256*256*256;
                result[6]=data[i+26]+data[i+27]*256+data[i+28]*256*256+data[i+29]*256*256*256;
                result[7]=data[i+30]+data[i+31]*256+data[i+32]*256*256+data[i+33]*256*256*256;
                result[8]=data[i+34]+data[i+35]*256+data[i+36]*256*256+data[i+37]*256*256*256;
                result[9]=data[i+38]+data[i+39]*256+data[i+40]*256*256+data[i+41]*256*256*256;
                result[10]=data[i+42]+data[i+43]*256+data[i+44]*256*256+data[i+45]*256*256*256;
                result[11]=data[i+46]+data[i+47]*256+data[i+48]*256*256+data[i+49]*256*256*256;

                break;//后面的数据不再处理了
            }
            if(data[i]==0x6c&&data[i+1]==0xc6){
                break;
            }
        }
        return  result;
    }
}

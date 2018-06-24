package com.lzh.handler;

/**
 * Created by LZH on 2018/6/8.
 * 状态量数据处理
 */

public class StateHandler {
    public static int[] handleInformaton(int[] data){
        int[] result = new int[16];
        for(int i=0;i<data.length;i++){
            if(data[i] == 0x5a && data[i+1] == 0xc5){
                result[0] = data[i+2];
                result[1] = data[i+3];
                result[2] = data[i+4];
                result[3] = data[i+5];
                result[4] = data[i+6];
                result[5] = data[i+7];
                result[6] = data[i+8];
                result[7] = data[i+9];
                result[8] = data[i+10];
                result[9] = data[i+11];
                result[10] = data[i+12];
                result[11] = data[i+13];
                result[12] = data[i+14];
                result[13] = data[i+15];
                result[14] = data[i+16];
                result[15] = data[i+17];
                break;
            }
            if(data[i]==0x6c&&data[i+1]==0xc6){
                break;
            }
        }
        return result;
    }
}

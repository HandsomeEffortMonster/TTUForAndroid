package com.lzh.tools;

/**
 * Created by LZH on 2018/5/30.
 * 转换帮助类 主要问题是这样的 C 传递过的unsigned 是无符号的0~256
 * java所有的默认有符号同样八位 表示的是 -128~127
 */

public class Transform {
    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * @param data
     * 发送函数 当发送的数字大于
     */
    public static byte[] toCmd(int[] data){
        byte[] bytes = new byte[data.length];
        for(int i=0;i<data.length;i++){
            if(data[i]>=128){
                bytes[i] =(byte) (data[i] - 256);
            }else{
                bytes[i] = (byte)(data[i]);
            }
        }
        return  bytes;
    }

    /**
     * @param data
     * 接收到的数值小于0 加 256 转换 java与C直接数据
     */
    public static int[] toReceiveNews(byte[] data){
        int[] news = new int[data.length];
        for(int i=0;i<data.length;i++){
            if(data[i]<0){
                news[i] = data[i]+256;
            }else{
                news[i] = data[i];
            }
        }
        return news;
    }

    /**
     * @param str
     * 判断是不是一个数字
     */
    public static boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}

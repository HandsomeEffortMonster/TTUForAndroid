package com.lzh.tools.netty;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by LZH on 2018/6/30.
 * netty没有移植到android上 非常失败QAQ  尝试将其放弃android执行  but失败
 */

/*
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf>{
    private static final String TAG ="MyClientHandler";
    private int counter = 0;
    private static final String REQ = "CLIENT REQUEST BUILD SUCCESS,END";
    private static final byte[] BUILD_SUCCESS = {0x01,0x02,0x03};

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    */
/**
     * 当收到连接成功的通知,发送一条消息.
     * @param ctx
     * @throws Exception
     *//*

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

            ctx.writeAndFlush( Unpooled.copiedBuffer(REQ.getBytes()) );
        //ctx.writeAndFlush(BUILD_SUCCESS);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.capacity()];
        msg.readBytes(bytes);
        System.out.println("receive:"+new String(bytes));

        Log.i(TAG ,"receive"+ sdf.format(new Date()));
        Log.i(TAG ,"receive"+ new String(bytes));
        //这一段在java调试中会报错 原因是普通java项目中不能导入android的包
    }

    */
/**
     * 异常发生时,记录错误日志,关闭channel
     * @param ctx
     * @param cause
     * @throws Exception
     *//*

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印堆栈的错误日志
        ctx.close();
    }
}
*/

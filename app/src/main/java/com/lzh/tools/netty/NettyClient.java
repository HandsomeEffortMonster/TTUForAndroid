package com.lzh.tools.netty;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by LZH on 2018/6/30.
 * netty没有移植到android上 非常失败QAQ  尝试将其放弃android执行  but失败
 */

/*
public class NettyClient {

    private static NettyClient nettyInstance = new NettyClient();
    public static NettyClient getInstance() {
        if (nettyInstance == null) {
            nettyInstance = new NettyClient();
        }
        return nettyInstance;
    }

    public synchronized void connect(int port,String host){
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //发包缓冲区
                    .option(ChannelOption.SO_SNDBUF,1024*256)
                    //收报缓冲区
                    .option(ChannelOption.SO_RCVBUF,1024*256)
                    //tcp立即发包
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception{
                            channel.pipeline().addLast(new ChildChannelHandler());
                        }
                    });
            //发起异步连接操作 绑定端口同步等待成功
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();

            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    //通道管理注册各个对应handler 将SocketChannel各个部分分割好
    private class ChildChannelHandler extends
            ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 消息用***作为分隔符,加入到DelimiterBasedFrameDecoder中，第一个参数表示单个消息的最大长度，当达到该
            // 长度后仍然没有查到分隔符，就抛出TooLongFrameException异常，防止由于异常码流缺失分隔符导致的内存溢出
            //new byte[]{0x01,0x02,0x03}
            ByteBuf delimiter = Unpooled.copiedBuffer("END".getBytes());//！！作为结束标志
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(4096,delimiter));
            ch.pipeline().addLast(new MyClientHandler());

        }
    }

*/
/*
    public static void main(String[] args)throws Exception{
        new NettyClient().connect(8887,"192.168.1.154");
    }
*//*


}
*/

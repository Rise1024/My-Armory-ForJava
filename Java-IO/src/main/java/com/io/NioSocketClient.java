package com.io;

/**
 * @author zds
 * @Description
 * @createTime 2022/3/30 11:43
 */
//客户端client类

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketClient {
    public void start() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8081);
            socketChannel.connect(socketAddress);

            int sendCount = 0;

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //这里最好使用selector处理   这里只是为了写的简单
            while (sendCount < 5) {
                buffer.clear();
                //向服务端发送消息
                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                //读取模式
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

                //从服务端读取消息
                int readLenth = socketChannel.read(buffer);
                //读取模式
                buffer.flip();
                byte[] bytes = new byte[readLenth];
                buffer.get(bytes);
                System.out.println(new String(bytes, "UTF-8"));
                buffer.clear();
                sendCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端断开连接");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 粘包 ->客户端分10批发送16个字节 而服务端只接受了一次160个字节
     * 原因
     * ->接收方得ByteBuf设置太大(netty默认1024)
     * ->滑动窗口，窗口过大并且接收方响应慢 报文会缓存在滑动窗口造成粘包
     * ->Nagle算法：凑够了一定大小得消息在发送
     * 半包 -> 客户端分10批发送16个字节 而客户端只接受了5次 但是每一次字节数都不和我们客户端发送得字节数匹配
     * 原因
     * ->接收方得ByteBuf设置太小(小于实际发送量)
     * ->滑动窗口，当前窗口剩余字节数小于信息发送量，只会传一部分
     * ->MSS限制：消息数据超过MSS限制(1500个字节)，会将数据切分
     * @param args
     */
    public static void main(String[] args) {
        new NioSocketClient().start();
    }
}

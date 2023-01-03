package com.netty.webscoket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author zds
 * @Description
 * @createTime 2021/8/23 17:14
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final ProtobufEncoder PROTOBUF_ENCODER = new ProtobufEncoder();
    private static final ProtobufDecoder PROTOBUF_DECODER = new ProtobufDecoder(PersonJ.Person.getDefaultInstance());

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

//        pipeline.addLast("httpResponseEncoder", new HttpResponseEncoder());
//        pipeline.addLast("httpRequestDecoder", new HttpRequestDecoder());

        // HttpRequestDecoder和HttpResponseEncoder的一个组合，针对http协议进行编解码
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(1048576));
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        pipeline.addLast("preflightHandler", new PreflightHandler());
        pipeline.addLast(new WebSocketServerHandler());
    }
}


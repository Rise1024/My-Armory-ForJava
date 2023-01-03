package com.netty.webscoket.client;


import com.netty.webscoket.server.Message;
import com.netty.webscoket.server.PersonJ;
import com.netty.utils.JsonUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author zds
 * @Description
 * @createTime 2021/8/16 15:43
 */
public class ClientMain {
    private static final ProtobufEncoder PROTOBUF_ENCODER = new ProtobufEncoder();
    private static final ProtobufDecoder PROTOBUF_DECODER = new ProtobufDecoder(PersonJ.Person.getDefaultInstance());

    public static void main(String[] args) throws Exception {
        for(int i=0;i<1;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        start();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void start() throws URISyntaxException, InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap boot=new Bootstrap();
        boot.option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                .group(group) .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new ChannelHandler[]{new HttpClientCodec(), new HttpObjectAggregator(1024*1024*10)});
//                        p.addLast(PROTOBUF_ENCODER);
//                        p.addLast(PROTOBUF_DECODER);
                        p.addLast("hookedHandler", new WebSocketClientHandler());
                    }
                });
        URI websocketURI = new URI("ws://172.17.1.16:9999/ws/tset");
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        //进行握手
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String)null, true,httpHeaders);
        System.out.println("connect");
        final Channel channel=boot.connect(websocketURI.getHost(),websocketURI.getPort()).sync().channel();
        WebSocketClientHandler handler = (WebSocketClientHandler)channel.pipeline().get("hookedHandler");
        handler.setHandshaker(handshaker);
        handshaker.handshake(channel);
        //阻塞等待是否握手成功
        handler.handshakeFuture().sync();
        Thread text = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("send ping to server");
                    try {
//                        PersonJ.Person build = PersonJ.Person.newBuilder().setEmail("dsafsd").setId(1).setName("zds").build();
//                        String str = Base64.encodeBase64String(build.toByteArray());
//
//                        ByteBuf buf = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
                        Message message=new Message();
                        message.setMessage("32423");
                        message.setType(1);
                        TextWebSocketFrame webSocketFrame = new TextWebSocketFrame(JsonUtils.mapToJsonString(message));
                        channel.writeAndFlush(webSocketFrame).addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if(channelFuture.isSuccess()){
                                    System.out.println("text send success");
                                }else{
                                    System.out.println("text send failed  "+channelFuture.cause().getMessage());
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        text.start();
    }
}

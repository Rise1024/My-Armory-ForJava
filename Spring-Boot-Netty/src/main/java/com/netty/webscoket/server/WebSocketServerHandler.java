package com.netty.webscoket.server;

/**
 * @author zds
 * @Description
 * @createTime 2021/8/23 17:17
 */

import com.netty.utils.JsonUtils;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;

import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;


public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    private WebSocketServerHandshaker handshaker;
    /**
     * 接收客户端发过来的消息并处理
     * FullHttpRequest ：
     *  官网解释：Combine the {@link HttpRequest} and {@link FullHttpMessage}, so the request is a <i>complete</i> HTTP
     * request.
     * 这个请求是 代表http请求完成的标记。
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if(msg instanceof FullHttpRequest){//接收到客户端的握手请求，开始处理握手
            handleHttpRequest(ctx,(FullHttpRequest)msg);
        }else if(msg instanceof WebSocketFrame){//接收到客户端发过来的消息（只过滤文本消息），处理后发给客户端。
            handleWebSocketFrame(ctx, (WebSocketFrame)msg);
        }
        ctx.fireChannelRead(msg);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req) throws Exception{
        /**
         * 如果不成功或者消息头不包含"Upgrade"，说明不是websocket连接，报400异常。
         */
        if(!req.getDecoderResult().isSuccess()||(!"websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket",null,false);
        handshaker = wsFactory.newHandshaker(req);//创建一个握手协议
        if(handshaker == null){
            /**
             * Return that we need cannot not support the web socket version
             * 返回不支持websocket 版本
             */
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(), req);//开始握手
            ctx.fireUserEventTriggered(new SockjsConnectionEvent(true));
            System.out.println("握手成功");
        }
    }
    /**
     * 我们判断数据类型，只支持文本类型
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame) {
        if(frame instanceof CloseWebSocketFrame){//如是接收到的是关闭websocket，就关闭连接
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        if(frame instanceof PingWebSocketFrame){//如果信息是2进制数据，就反给它，
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if(!(frame instanceof TextWebSocketFrame)){//哪果不是文本的数据，就报错。
            throw new UnsupportedOperationException(String.format("%s frame types not supported",frame.getClass().getName()));
        }

        String s = JsonUtils.mapToJsonString(((TextWebSocketFrame) frame).text());

//        byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(request);

//        Message message = JsonUtils.parse(request, Message.class);

        System.out.println("获得结果"+s );
        Message msage=new Message();
        msage.setExt("sdafsd");
        msage.setType(1);
            ctx.channel().write(new TextWebSocketFrame(JsonUtils.mapToJsonString(msage)));

    }
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res){
        if(res.getStatus().code() != 200){
            DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.METHOD_NOT_ALLOWED);
            response.headers().add(HttpHeaderNames.ALLOW, HttpMethod.GET.toString());
            sendHttpResponse1(ctx, req, response);
            return;

        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);//发送消息
        if(!isKeepAlive(req)||res.getStatus().code()!= 200){//如果断开连接，或者发送不成功，断开连接。
            f.addListener(ChannelFutureListener.CLOSE);//关闭连接
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static void sendHttpResponse1(ChannelHandlerContext ctx, io.netty.handler.codec.http.HttpRequest req, HttpResponse res) {
        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}


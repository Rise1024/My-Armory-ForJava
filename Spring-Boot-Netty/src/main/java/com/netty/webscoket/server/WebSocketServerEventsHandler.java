package com.netty.webscoket.server;

/**
 * @author zds
 * @Description
 * @createTime 2021/8/23 17:17
 */

import com.netty.utils.JsonUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;

import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;


public class WebSocketServerEventsHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger.getLogger(WebSocketServerEventsHandler.class.getName());

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}


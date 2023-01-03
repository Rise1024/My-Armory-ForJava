package com.netty.webscoket.server;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.AttributeKey;

/**
 * @Auther: zds
 * @Date: 20-4-10 09:57
 * @Description:
 */
public class AttributeKeys {
    public static final AttributeKey<String> origin = AttributeKey.valueOf(HttpHeaderNames.ORIGIN.toString());
    public static final AttributeKey<String> corsHeaders = AttributeKey.valueOf(HttpHeaderNames.ACCESS_CONTROL_REQUEST_HEADERS.toString());
}
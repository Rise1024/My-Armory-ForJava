package com.jdk17.jdk10;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * 增加了websocket的原生支持，再也不用使用第三方包来构建websocket client了
 */
public class WebSocketTest {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private List<CharSequence> textParts = new ArrayList<>(16);
    private List<ByteBuffer> binaryParts = new ArrayList<>(16);
    private String url;
    private WebSocket webSocket;

    public WebSocketTest(String url) {
        this.url = url;
        var wsCompletableFuture =
                HttpClient.newHttpClient().newWebSocketBuilder()
                .buildAsync(URI.create(url), new WebSocketListener());
        webSocket = wsCompletableFuture.join();
        executor.scheduleAtFixedRate(()->{
            // send ping msg  to keep alive every 5 second
            if (webSocket!=null && !webSocket.isOutputClosed()) {
                System.out.println("send ping");
                var objectMapper = new ObjectMapper(); //jackson
                var map = new HashMap<String,Object>(1);
                map.put("ping",System.currentTimeMillis());
                try {
                    webSocket.sendText(objectMapper.writeValueAsString(map),true);
                } catch (JsonProcessingException ignore) {
                }
            }
        },5,5,TimeUnit.SECONDS);
    }

    private class WebSocketListener implements WebSocket.Listener {

        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("websocket opened.");
            webSocket.request(1);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            webSocket.request(1);
            textParts.add(data);
            if (last) {
                String content = String.join("", textParts);
                System.out.println(content);
                textParts.clear();
            }
            return null;
        }
        @Override
        public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
            webSocket.request(1);
            binaryParts.add(data);
            if (last) {
                int size = 0; 
                for (var binaryPart : binaryParts) {
                    size+=binaryPart.array().length;
                }
                var allocate = ByteBuffer.allocate(size);
                binaryParts.forEach(allocate::put);
                binaryParts.clear();
                var content = uncompress(allocate.array());
                System.out.println(new String(content));
            }
            return null;
        }
        @Override
        public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
            webSocket.request(1);
            System.out.println("ping");
            System.out.println(message.asCharBuffer().toString());
            return null;
        }
        @Override
        public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
            webSocket.request(1);
            System.out.println("pong");
            return null;
        }
        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("ws closed with status(" + statusCode + "). cause:"+reason);
            webSocket.sendClose(statusCode, reason);
            return null;
        }
        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.out.println("error: " + error.getLocalizedMessage());
            webSocket.abort();
        }

    }

    private byte[] uncompress(byte[] bytes) {
        return bytes;
      // ...
    }

    public String getUrl() {
        return url;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

     public static void main(String[] args) throws JsonProcessingException {
        var huobiWebSocket = new WebSocketTest("wss://test/ws");
        var map = new HashMap<String, Object>();
        map.put("hander","handerValue");
        var objectMapper = new ObjectMapper();
        huobiWebSocket.getWebSocket().sendText(objectMapper.writeValueAsString(map),true);
    }
}
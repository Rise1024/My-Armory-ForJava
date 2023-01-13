package com.jdk17.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @Auther: zds
 * @Date: 2023/01/10/17:32
 * @Description:函数式编程
 */
public class HttpclientTest {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse.BodyHandler<String> responseBodyHandler = new HttpResponse.BodyHandler<String>() {
            @Override
            public HttpResponse.BodySubscriber<String> apply(HttpResponse.ResponseInfo responseInfo) {
                return (HttpResponse.BodySubscriber<String>) responseInfo;
            }
        };
        HttpResponse<String> response = httpClient.send( HttpRequest.newBuilder(new URI("//www.foo.com/")).headers("Foo", "foovalue", "Bar", "barvalue").GET().build(), responseBodyHandler);
               int statusCode = response.statusCode();
               String body = response.body();


    }


    String jsonStr = "{\"name\": \"大阳\"}";
    //文本块，不需要转义字符
    String textBlocks = """
            {"name":"大阳"}
             """ ;


}

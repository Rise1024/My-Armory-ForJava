package com.jdk17.jdk10;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @Auther: zds
 * @Date: 2023/01/14/16:01
 * @Description:
 *  HttpClient 最初在 JDK 9 中提供，
 *  后来在 JDK 10 升级，
 *  在 JDK 11 终于稳定成为标准功能，
 *  同时支持 HTTP/1.1 和 HTTP/2
 */
public class httpClientTest {
    public static void main(String[] args) {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("www.duyin.com"))
                .setHeader("User-Agent", "Java 11")
                .build();


       HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.statusCode());
        System.out.println(response.body());

    }
}

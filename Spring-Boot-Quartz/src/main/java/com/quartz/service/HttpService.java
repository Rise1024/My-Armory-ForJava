package com.quartz.service;

import com.quartz.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: DS
 * @Date: 2023/11/30 17:27
 * @Description:
 **/
@Slf4j
@Service
public class HttpService {


    public void test(){
        try {
            // 设置要调用的 API 端点 URL
            String apiUrl = "https://api.example.com/data"; // 请将此处替换为您要调用的实际 API 端点 URL

            // 创建 URL 对象
            URL url = new URL(apiUrl);

            Thread.sleep(5000);
            // 创建 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET（可以根据需要修改为 POST、PUT、DELETE 等）
            connection.setRequestMethod("GET");

            // 可选：设置请求头，例如 API 密钥、内容类型等
            connection.setRequestProperty("Authorization", "Bearer YOUR_API_KEY");
            // 如果需要传递 JSON 数据，可以设置 Content-Type
            // connection.setRequestProperty("Content-Type", "application/json");

            // 发起请求并获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 读取 API 响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // 输出响应内容
            System.out.println("Response: " + response.toString());

            // 关闭连接
            connection.disconnect();

        } catch (Exception e) {
            log.error("has error e:{}",e);
            throw new ApiException("test has error");
        }
    }
}

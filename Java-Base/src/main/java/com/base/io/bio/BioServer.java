package com.base.io.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8888);
            Socket accept = socket.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while ((msg = bufferedReader.readLine())!=null){
                System.out.println("服务端收到消息："+msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
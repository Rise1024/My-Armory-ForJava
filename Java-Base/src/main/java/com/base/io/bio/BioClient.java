package com.base.io.bio;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class BioClient {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream p = new PrintStream(outputStream);
            p.println("hello server!");
            p.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
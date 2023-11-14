package com.video.jvm;

import java.util.ArrayList;

/**
 * @Auther: zds
 * @Date: 2023/01/20/00:24
 * @Description:
 */
public class JvmTest1 {

    public static void main(String[] args) {
        /**
         * 栈溢出
         */
//        main(args);

        /**
         * 堆溢出
         */
//        byte[] stack= new byte[11 * 1024 * 1024];

        /**
         * GC回收时间过长
         */
        ArrayList<OomData> oomData = new ArrayList<>();
        int i=1;
        new Thread(()->{
            while (true){

            }
        }).start();
        while (true){
            oomData.add(new OomData(i++,"a"));
        }
    }
}
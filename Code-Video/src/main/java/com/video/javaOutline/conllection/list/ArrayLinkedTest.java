package com.video.javaOutline.conllection.list;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: zds
 * @Date: 2023/02/08/15:03
 * @Description:
 */
public class ArrayLinkedTest {

    /**
     * 在中部插入元素
     * @param args
     */
    public static void main(String[] args) {
        /*ArrayList*/
        List<String> arrayList = new ArrayList<>();
        for (int i=0;i<=10000000;i++){
            arrayList.add("array");
        }
        Long startTime1 = System.currentTimeMillis();
//        arrayList.add(900000,"123");
        arrayList.remove(6000000);
        System.out.println("ArrayList耗时：" + (System.currentTimeMillis()-startTime1) + "ms");


        /*LinkedList*/
        List<String> linkedList = new LinkedList<>();
        for (int i=0;i<=10000000;i++){
            linkedList.add("linked");
        }
        System.out.println("linked 增加完毕");
        Long startTime = System.currentTimeMillis();
//        linkedList.add(900000,"123");
        linkedList.remove(6000000);
        System.out.println("LinkedList耗时：" + (System.currentTimeMillis()-startTime) + "ms");

//        int i = 10000000 >> 1;
//        boolean b = 4999999 < (10000000 >> 1);
//        System.out.println(i);
//
//        System.out.println(b);



    }
}

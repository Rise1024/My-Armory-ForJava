package com.base.datastructure;

import java.util.Arrays;

public class arrayDemo {

    /*int数组长度*/
        public static void arrayLenth() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7};
            int len = arr.length;
            System.out.println("数组长度为：" + len);
        }

    /**
     * int数组填充：将一个数组或数组指定元素用固定值添加到数组中。可以使用 Arrays 类提供的 fill 对数组进行填充
     Arrays.fill(数组名, 值)  作用：将值全部填充到数
     Arrays.fill(数组名, 开始下标, 结束下标, 值) 作用：将值填充到从开始下标到结束下标部分
     **/
        public static void arrayFill() {
            int[] a = new int[5];
            int[] b = new int[5];
            Arrays.fill(a, 1); //给数组 a 填充值 1
            Arrays.fill(b, 2, 4, 20);//用 20 来填充数组b的开始下标2到结束下标4部分
            //遍历数组的值
            for(int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
            for(int i = 0; i < b.length; i++) {
                System.out.print(b[i] + " ");
            }
        }
    //运行结果如下
    // 1 1 1 1 1
    // 0 0 20 20 0

    /**
     *数组复制是将一个指定数组范围内的元素值复制到另一个数组中去，Java提供了 Arraycopy函数（方法）来进行数组的复制操作。：
     */
        public static void main(String[] args) {
            int[] a = {1, 2, 3, 4, 5};
            int[] b = {11, 12, 13, 14, 15};
            //将数组a复制到数组b
            System.arraycopy(b, 1, a, 2, 3);
            System.out.println("复制后a数组的值：");
            for(int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
        }
//运行结果如下：
//1 2 12 13 14



}

package com.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: zds
 * @Date: 2022/11/07/15:12
 * @Description:
 *
 * 冒泡排序
 * 1.依次比较每两个相邻的数的大小，将较大的数交换到后面。
 * 2.当第一次整个数据比较和交换完成后，整个数组中最大的数字则被排在数组的最后。
 * 3.此时，已经将一个数排到了其相应的位置，不需要再移动了。
 * 4.再次遍历整个数组，将次大的数通过比较和交换，排在其相应的位置。
 * 5.重复上述流程，知道整个数组都被排序。
 *
 * 优点:比较简单，空间复杂度较低，是稳定的；
 *
 * 缺点:时间复杂度太高，效率慢；
 */

public class BubbleSort {


    /**
     * 模拟算法循环
     * 外层循环第一遍
     *i=0
     *j=0,1,2,3
     * 1,23,2,34,12
     * if（arr[0] > arr[1]）不冒泡
     * 1,23,2,34,12
     * if（arr[1] > arr[2]）冒泡
     * 1,2,23,34,12
     * if（arr[2] > arr[3]）不冒泡
     * 1,2,23,34,12
     * if（arr[3] > arr[4]）冒泡
     *  1,2,23,12,34
     *
     * 第二遍
     *i=1
     *j=0,1,2
     * 1,2,23,12,34
     * if（arr[0] > arr[1]）不冒泡
     * 1,2,23,12,34
     * if（arr[1] > arr[2]）不冒泡
     * 1,2,23,12,34
     * if（arr[2] > arr[3]）冒泡
     * 1,2,12,23,34
     *
     * 第三遍
     *i=2
     *j=0,1
     * 1,2,12,23,34
     * if（arr[0] > arr[1]）不冒泡
     * 1,2,12,23,34
     * if（arr[1] > arr[2]）不冒泡
     *
     * 第四遍
     *i=3
     *j=0
     * 1,2,12,23,34
     * if（arr[0] > arr[1]）不冒泡
     * @param arr
     */

    public static void bubbleSort(int[] arr){
        //因为排好数组中第二个数时，第一个数也自然排好了，所以遍历的此数为length - 1
        for (int i = 0; i < arr.length - 1; i++) {
            //第i + 1次遍历整个数组
            for (int j = 0; j < arr.length - i - 1; j++) {
                //左右对比，如果左边的大于右边，就进行左右交换
                if (arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
            System.out.println("第"+(i)+"次循环");
            //此时倒数第i + 1个数则排序好了
        }
    }

    /**
     * 冒泡排序升级版
     * 子循环不再冒泡时，break;
     * @param arr

     */
    public static void bubbleSort1(int[] arr){

        //因为排好数组中第二个数时，第一个数也自然排好了，所以遍历的此数为length - 1
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag=true;
            //第i + 1次遍历整个数组
            for (int j = 0; j < arr.length - i - 1; j++) {
                //左右对比，如果左边的大于右边，就进行左右交换
                if (arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                    flag=false;
                }
            }
            if (flag){
                break;
            }
            System.out.println("第"+(i)+"次循环");
            //此时倒数第i + 1个数则排序好了
        }
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] a={1,23,2,34,12};
        bubbleSort1(a);
        Arrays.stream(a).forEach(value -> System.out.println(value));
    }


}

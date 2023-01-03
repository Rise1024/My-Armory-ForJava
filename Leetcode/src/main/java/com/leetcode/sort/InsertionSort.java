package com.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: zds
 * @Date: 2022/11/07/16:13
 * @Description:
 * 插入排序
 * 1.在排序时，有序区间为[ 0 , i )，无序区间为[ i , arr.length )，注意两区间都是左闭右开的区间。
 * 2.选择当前无序区间的第一个数，和前面的数依次比较，若前面的数比当前的数大，则交换，直到遇到遇到一个小于它的数，或已经到达数组的最前位置。
 * 3.此时，排好了一个数，当前的有序区间为[ 0 , i + 1)，[ i - 1, arr.length )。
 * 4.并继续按照上述流程比较和交换，直到整个数据排序完成。

 */
public class InsertionSort {

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a={1,23,2,34,12};
        insertSort(a);
        Arrays.stream(a).forEach(value -> System.out.println(value));
    }

}

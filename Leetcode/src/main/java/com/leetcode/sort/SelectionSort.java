package com.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: zds
 * @Date: 2022/11/07/15:48
 * @Description:
 *
 * 1.初次遍历数组时，根据依次比较，筛选出当前数组中最小的数。
 * 2.将此数通过交换到数组最前的位置，此数则排序完成。
 * 3.再将除排序好的数以外的数组，再次筛选出最小值。
 * 4.再次将当前最小值通过交换，排到数组的最前。
 * 5.重复上述流程，直到整个数组排序完成。
 */
public class SelectionSort {
    public static void selectSort(int[] arr){
        //依次筛选并排序最小值
        for (int i = 0; i < arr.length - 1; i++) {
            //用min记录当前数组最小值的索引
            int min = i;
            //遍历数组，筛选出最小值得索引
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            //交换最小值和当前数组最前的位置的值
            swap(arr, min, i);
            //此时当前数组最小值得排序完成
        }
    }

    /**
     * 改进双向选择排序
     * 1.在每次遍历数组时，筛选出当前数组中的最小值和最大值。
     * 2.将最小值和最大值分别交换到当前数组的最前和最后。
     * 3.再将除已排序完的数的数组，再次按照上述流程进行排序。
     * 注：
     * 可能会出现max和low重合的情况，即max的值并未变化的情况，则此时min才是max应该指向的位置。
     * @param arr
     */
    public static void selectSortOP(int[] arr){
        //记录最小值得索引
        int low = 0;
        //记录最大值的索引
        int high = arr.length - 1;
        while (low <= high){
            //从左边开始遍历
            int min = low;
            int max= low;
            for (int i = low + 1; i <= high; i++) {
                //筛选出更小的数
                if (arr[i] < arr[min]){
                    min = i;
                }
                //筛选出更大的值
                if (arr[i] > arr[max]){
                    max = i;
                }
            }
            //交换数组最左边的值和最小值
            swap(arr, min, low);
            //max和low重合的情况
            if (max == low){
                max = min;
            }
            //交换数组最右边的值和最大值
            swap(arr, max, high);
            //移动左右指针
            ++low;
            --high;
        }
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] a={1,23,2,34,12};
        selectSort(a);
        Arrays.stream(a).forEach(value -> System.out.println(value));
    }

}

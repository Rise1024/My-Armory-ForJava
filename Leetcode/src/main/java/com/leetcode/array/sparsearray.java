package com.leetcode.array;

/**
 * 数组 链表
 */
public class sparsearray {

    public static void main(String[] args) {
        //二维数组
        int[][] TowArr = new int[11][11];
        TowArr[0][0] = 1;
        TowArr[1][1] = 2;
        TowArr[1][2] = 2;
        TowArr[10][10] = 1;
        for(int[] row : TowArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //1、遍历找到非0个数
        int sum = 0;
        for(int i = 0 ; i < TowArr.length;i++){
            for(int j = 0;j < TowArr[i].length;j++){
                if(TowArr[i][j] != 0){
                    sum++;
                }
            }
        }

        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = TowArr.length;
        sparseArr[0][1] = TowArr[0].length;//稀疏数组的原数组行数列数各自相等
        sparseArr[0][2] = sum;


        //遍历插入有效值
        int count = 0;
        for(int i = 0 ; i < TowArr.length;i++){
            for(int j = 0;j < TowArr[i].length;j++){
                if(TowArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = TowArr[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("==========稀疏数组=============");
        for(int[] row : sparseArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //稀疏数组还原二维数组
        int[][] TOWArr2 =new int[sparseArr[0][0]][sparseArr[0][1]];
        System.out.println("==========二维数组恢复==========");
        for(int i = 1;i<sparseArr.length;i++){
            TOWArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for(int[] row : TOWArr2){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
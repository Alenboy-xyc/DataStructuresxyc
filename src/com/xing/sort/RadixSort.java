package com.xing.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr={53,3,542,748,14,214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr){
        //1.得到数组中最大的数位数
        int max = arr[0]; //假设第一位最大数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max+"").length();
        int[][] bucket = new int[10][arr.length];
        //记录每个桶实际放了多少个数据
        int[] bucketElementCounts = new int[10];
        for (int i = 0,n = 1; i < maxLength; i++,n*=10) {
            //针对每一轮，个十百千
            for (int j = 0; j < arr.length; j++) {
                //取出元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放到对应桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index=0;
            //遍历每一桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k]!=0){
                    for (int l = 0;l<bucketElementCounts[k];l++){
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮，排序处理arr="+ Arrays.toString(arr));
        }
    }
}

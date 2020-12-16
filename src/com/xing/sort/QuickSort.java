package com.xing.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,85,23,-567,70,50,80,-20,-15,10};
        quickSort(arr,0, arr.length-1);
        System.out.println("arr="+ Arrays.toString(arr));
    }
    //快速排序
    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp = 0;
        //让比pivot小的放左边，大的放右边
        while(l<r){
            //在pivot左边一直找，找到大于等于pivot的值，退出
            while(arr[l]<pivot){
                l+=1;
            }
            while(arr[r]>pivot){
                r-=1;
            }
            //如果l>=r说明pivot的左右两边的值，已经按照左边全部都是小于等于pivot值，右边全部都是大于等于pivot值
            if (l>=r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l]==pivot){
                r-=1;
            }
            if (arr[r]==pivot){
                l+=1;
            }
        }
        if (l == r){
            l+=1;
            r-=1;
        }
        if (left<r){
            quickSort(arr,left,r);
        }
        if (right>l){
            quickSort(arr,l,right);
        }
    }
}

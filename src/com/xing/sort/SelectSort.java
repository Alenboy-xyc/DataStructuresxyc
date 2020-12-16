package com.xing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        /* int[] arr={3,9,-1,10,20};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));*/
        int[] arr=new int[80000];
        for (int i=0;i<80000;i++){
            arr[i]=(int)(Math.random()*8000000);
        }
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是="+date1Str);
        selectSort(arr);
        Date date2=new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是="+date2Str);
    }
    //选择排序
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min>arr[j]){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if (minIndex!=i){
                arr[minIndex] = arr[i];
                arr[i]=min;
            }
        }
    }
}

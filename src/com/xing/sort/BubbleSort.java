package com.xing.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 大
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={20,18,-11,33,1,0,5,8,15,-12};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        /*int[] arr=new int[80000];
        for (int i=0;i<80000;i++){
            arr[i]=(int)(Math.random()*8000000);
        }
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是="+date1Str);
        bubbleSort(arr);
        Date date2=new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是="+date2Str);*/
    }
    //冒泡排序
    public static void bubbleSort(int[] arr){
        int temp=0;
        boolean flag=false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if (!flag){
                break;
            }else {
                flag=false;
            }
        }
    }
}

package com.xing.test;

import java.util.Arrays;

public class xx {
    public static void main(String[] args) {
        int[] arr = {-10,20,5,6,7,90,50,0,40};
        soot(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void soot(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                break;
            }else {
                flag = false;
            }
        }
    }
}

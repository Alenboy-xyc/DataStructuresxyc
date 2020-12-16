package com.xing.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[] arr={1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr, 10));
    }

    //非递归得到一个斐波那契数列
    public static int[] fib(){
        int[] f =new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    public static int fibSearch(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        int k = 0;
        int mid = 0;
        int[] f = fib();
        while (high>f[k]-1){
            k++;
        }
        //不足会有0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        //实际上需要a数组最后的数填充temp
        for (int i = high+1;i<temp.length;i++){
            temp[i] = a[high];
        }
        while (low<=high){
            mid = low+f[k-1]-1;
            if (key<temp[mid]){ //向数组前面查找
                high = mid-1;
                k--;
            }else if (key>temp[mid]){
                low = mid+1;
                k-=2;
            }else {
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}

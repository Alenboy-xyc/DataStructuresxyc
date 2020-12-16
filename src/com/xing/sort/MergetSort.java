package com.xing.sort;

import java.util.Arrays;

public class MergetSort {
    public static void main(String[] args) {
        int[] arr= {8,4,5,7,1,3,6,2};
        int[] temp=new int[arr.length];
        mergeSort(arr,0, arr.length-1,temp );
        System.out.println("归并排序后="+ Arrays.toString(arr));
    }
    //归并排序


    //分+和方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序数列的初始索引
     * @param mid 中间的索引
     * @param right 右边的索引
     * @param temp 中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left; //左边有序序列的初始索引
        int j = mid+1; //初始化j，右边有序序列的初始索引
        int t = 0;//指向temp的索引
        //（一）
        //先把左右两边的有序的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i<=mid && j<=right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组
            //然后t++
            if (arr[i]<=arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }else { //反之将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t+=1;
                j+=1;
            }
        }
        //（二）
        //把有剩余1数组一边的数据依次全部填充到temp
        while (i<=mid){
            temp[t]=arr[i];
            t+=1;
            i+=1;
        }
        while (j<=right){
            temp[t]=arr[j];
            t+=1;
            j+=1;
        }
        //（三）
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t=0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}

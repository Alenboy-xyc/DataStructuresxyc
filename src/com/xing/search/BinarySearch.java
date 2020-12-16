package com.xing.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,8,10,89,1000,1000,1234};
        int resIndex = binarySearch(arr,0,arr.length-1,1000);
        System.out.println("resIndex="+resIndex);
        List<Integer> resIndex2 = binarySearch2(arr,0,arr.length-1,1000);
        System.out.println("resIndex2="+resIndex2);
    }

    //二分查找算法
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    //思路分析
    /*
    思路分析
    1.找到mid不要马上返回
    2.向mid索引左面扫描，将所有满足1000的元素下标加入到list中
    3.向mid索引右面扫描，将所有满足1000的元素下标加入到list中
    4.返回list
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        if (left>right){
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            ArrayList<Integer> resIndexlist = new ArrayList<Integer>();
            int temp = mid-1;
            while(true){
                if (temp<0 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp-=1;
            }
            resIndexlist.add(mid);//中间的
            temp = mid+1;
            while(true){
                if (temp>arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp+=1;
            }
            return resIndexlist;
        }
    }
}

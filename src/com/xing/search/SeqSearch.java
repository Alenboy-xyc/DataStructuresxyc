package com.xing.search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        try {
            int num = seqSearch(arr,10);
            if (num!=-1){
                System.out.println("第"+(num+1)+"位");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        throw new RuntimeException("没有查到值");
    }
}

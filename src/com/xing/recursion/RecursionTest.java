package com.xing.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题回顾递归调用机制
        test(4);
    }
    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }
}

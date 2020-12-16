package com.xing.recursion;

public class Queue8 {
    int max=8;
    int[] array=new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue = new Queue8();
        queue.check(0);
        System.out.printf("共有种%d方式",count);
    }
    //放置第n个皇后
    private void check(int n){
        if (n==max){
            print();
            return;
        }
        //依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //接着放n+1个皇后
                check(n+1);
            }
        }
    }


    //检查摆放的皇后和之前的是否冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //1.array[i] == array[n]表示第n个皇后是否和前面的在同一列
            if (array[i] == array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}

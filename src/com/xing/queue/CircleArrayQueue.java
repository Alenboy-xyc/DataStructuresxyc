package com.xing.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形数组案例");
        CirceArray circeArray = new CirceArray(4);//设置为4，但队列有效数据最大为3
        char key= ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key){
                case 's':
                    circeArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    circeArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circeArray.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circeArray.headQueue();
                        System.out.printf("队列头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CirceArray{
    private int maxSize; //表示数组最大容量
    //变量含义做调整，指向队列的第一个元素，初始值为0
    private int front;
    //指向队列最后一个元素的后一位，空出一个空间作为约定，初始值为0
    private int rear;
    private int[] arr; //用于存放数据，模拟队列

    public CirceArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //默认初始化rear和front为0，不需要设置
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear = (rear+1)%maxSize;
    }
    //获取队列的数据，数据出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据"); //throw后不用return，其中已经包含
        }
        //这里需要分析出front是指向队列第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value=arr[front];
        front = (front+1)%maxSize;
        return value;
    }
    //显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //思路，从front开始遍历，遍历多少个元素
        //动脑筋，
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }
    //求出当前队列有效数据
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头数据，不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}

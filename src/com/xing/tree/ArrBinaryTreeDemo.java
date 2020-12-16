package com.xing.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}
//编写ArrBinaryTree，实现顺序存储二叉树遍历

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    //编写方法完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //输出当前元素
        System.out.println(arr[index]);
        if (index*2+1< arr.length){
            preOrder(2*index+1);
        }
        if (index*2+2< arr.length){
            preOrder(2*index+2);
        }
    }
}
package com.xing.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
        System.out.println("前序遍历方式");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode!=null){
            System.out.println("找到了信息为no="+resNode.getNo()+" 名字="+resNode.getName());
        }else {
            System.out.println("没有找到no="+5+"的英雄");
        }
        System.out.println("中序遍历方式");
        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
        if (resNode1!=null){
            System.out.println("找到了信息为no="+resNode1.getNo()+" 名字="+resNode1.getName());
        }else {
            System.out.println("没有找到no="+5+"的英雄");
        }
        System.out.println("后序遍历方式");
        HeroNode resNode2 = binaryTree.postOrderSearch(5);
        if (resNode2!=null){
            System.out.println("找到了信息为no="+resNode2.getNo()+" 名字="+resNode2.getName());
        }else {
            System.out.println("没有找到no="+5+"的英雄");
        }
    }
}
//定义BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void  delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空数，不能删除--");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序查找
    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode {no = "+no+", name ="+name+"}";
    }
    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this); //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //编写中序遍历的方法
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //编写后序遍历的方法
    public void postOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.postOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right !=null && this.right.no == no){
            this.right = null;
            return;
        }
        //向左子树递归删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //向右子树递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //前序遍历查找
    /**
     *  前序查找
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是
        if (this.no == no){
            return this;
        }
        //1.判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.right!=null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    /**
     * 中序遍历
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right!=null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    /**
     * 后序查找
     */
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left!=null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.right!=null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}
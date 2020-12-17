package com.xing.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

    }
}

//定义ThreadedBinaryTree，实现线索化二叉树
class ThreadedBinaryTree{
    private HeroNode1 root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode1 pre = null;
    public void setRoot(HeroNode1 root) {
        this.root = root;
    }
    //编写对二叉树进行中序线索化的方法

    /**
     *
     * @param node1 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode1 node1){
        //如果node==null，不能线索化
        if (node1 == null){
            return;
        }
        //1.先线索化左子树
        threadedNodes(node1.getLeft());
        //2.线索化当前节点
        if (node1.getLeft() == null){
            node1.setLeft(pre);
            node1.setLeftType(1);
        }
        if (pre!=null && pre.getRight() == null){
            pre.setRight(node1);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node1;
        //3.再线索化右子树
        threadedNodes(node1.getRight());
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
    public HeroNode1 preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode1 infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode1 postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}


//创建HeroNode节点
//先创建HeroNode节点
class HeroNode1{
    private int no;
    private String name;
    private HeroNode1 left; //默认null
    private HeroNode1 right; //默认null
    //1.如果leftType==0表示指向的是左子树，如果是1则表示指向前驱节点
    //2.如果rightType==0表示指向的是右子树，如果是1则表示指向后续节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode1(int no, String name) {
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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
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
    public HeroNode1 preOrderSearch(int no){
        //比较当前节点是不是
        if (this.no == no){
            return this;
        }
        //1.判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到节点，则返回
        HeroNode1 resNode = null;
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
    public HeroNode1 infixOrderSearch(int no){
        HeroNode1 resNode = null;
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
    public HeroNode1 postOrderSearch(int no){
        HeroNode1 resNode = null;
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
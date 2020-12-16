package com.xing.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入节点
        /*
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        */
        //加入按照标号的存在
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小鹿","玉麒麟~~~");
        singleLinkedList.update(newHeroNode);
        //显示
        System.out.println("修改后的链表");
        singleLinkedList.list();
        //反转链表
        reverseList(singleLinkedList.getHead());
        //显示
        System.out.println("反转后的链表");
        singleLinkedList.list();
        //删除一个节点
        singleLinkedList.del(1);
        //显示
        System.out.println("删除后的链表");
        singleLinkedList.list();
        //测试求单链表节点个数
        System.out.println("有效的节点个数"+getLength(singleLinkedList.getHead()));
        //测试一下得到倒数第k个节点
        HeroNode res=findLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println("res="+res);
    }

    //将节点反转
    public static void reverseList(HeroNode head){
        //如果链表为空或只有一个节点，无需反转
        if (head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next=null;  //指向当前节点【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，放在新的链表reverseHead中的最前端
        while (cur!=null){
            next=cur.next; //先暂时保存当前节点的下一个节点，因为后面用
            cur.next=reverseHead.next; //将cur的下一个节点指向新的链表最前端
            reverseHead.next=cur;  //将cur连接到新的链表上
            cur=next; //让cur后移
        }
        head.next=reverseHead.next;
    }

    //方法，获取单链表节点的个数
    public static int getLength(HeroNode head){
        if (head.next==null){
            return 0;
        }
        int length=0;
        //定义一个辅助变量，这里没有统计头节点
        HeroNode cur=head.next;
        while(cur!=null){
            length++;
            cur=cur.next;//遍历
        }
        return length;
    }
    //查找单链表中倒数第k个节点【新浪面试题】
    //思路
    //1.编写一个方法，接收head节点，同时接受一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表总长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        int size=getLength(head);
        if (index<=0||index>size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }
}
//定义SingleLinkedList管理英雄
class SingleLinkedList{
    //初始化头节点，头节点不动
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //不考虑编号时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，需要一个辅助遍历temp
        HeroNode temp=head;
        //通过遍历，找到最后
        while (true){
            //找到链表的最后
            if (temp.next==null){
                break;
            }
            //没有找到就后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向链表最后
        //将最后节点next指向新节点
        temp.next=heroNode;
    }

    //第二种添加英雄方式，根据排名将英雄添加到指定位置
    //如果有这个排名，添加失败，该提示
    public void addByOrder(HeroNode heroNode){
        //因为头节点不动，因此还需要辅助指针来找到添加位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if (temp.next==null){ //说明temp已在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp后面插入
                break;
            }else if (temp.next.no==heroNode.no){ //说明要插入的节点已存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (false){
            System.out.printf("准备插入的英雄的编号%d已存在",heroNode.no);
        }else {
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改节点，根据no
        //定义辅助变量
        HeroNode temp=head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;//已经遍历完链表
            }
            if (temp.no== newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name= newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }else {//没有找到节点
            System.out.printf("没有找到编号%d的节点\n",newHeroNode.no);
        }
    }

    //删除节点
    //1.head不能动，需要辅助节点找到待删除节点的前一个节点
    //2.说明在比较时，时temp.next.no和需要删除节点的no比较
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的%d 节点不存在\n",no);
        }
    }

    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while(true){
            //判断是否到链表最后
            if (temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp=temp.next;
        }
    }
}

//定义HeroNode，每一个HeroNode对象是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

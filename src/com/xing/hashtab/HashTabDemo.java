package com.xing.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    int id1 =scanner.nextInt();
                    hashTab.findEmpById(id1);
                    break;
                case "delete":
                    System.out.println("输入id");
                    int id2 =scanner.nextInt();
                    hashTab.deleteEmpById(id2);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray; //放链表的数组
    private int size;
    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //这时不要忘了分别初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp){
        //根据员工的id得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将Emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }
    //遍历所有链表，遍历Hashtab
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //输入id查找雇员
    public void findEmpById(int id){
        //根据员工的id得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findById(id);
        if (emp!=null){
            System.out.println("在第"+(empLinkedListNO+1)+"条链表中找到该雇员 id="+id);
        }else {
            System.out.println("没有找到该雇员");
        }
    }
    //输入id删除雇员
    public void deleteEmpById(int id){
        int empLinkedListNO = hashFun(id);
        empLinkedListArray[empLinkedListNO].deleteById(id);
    }
    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next; //next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
//创建一个EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，指向第一个雇员，因此这个链表的head是直接指向第一个雇员的
    private Emp head; //默认是空的
    //添加雇员到链表
    //1.假定，当添加雇员时，id是自增长，即id的分配总是从小到大
    //因此我们将该雇员加到本链表最后即可
    public void add(Emp emp){
        //如果是添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用辅助指针定位到最后
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){//说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        //退出时直接将emp加到最后
        curEmp.next =emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null){ //说明链表为空
            System.out.println("第 "+(no+1)+" 链表为空");
            return;
        }
        System.out.print("第 "+(no+1)+" 链表的信息为");
        Emp curEmp = head;
        while (true){
            System.out.print(" => id = "+curEmp.id+" name = "+curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next; //后移遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //找到返回Emp，没有返回null
    public Emp findById(int id){
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp =head;
        while(true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
    //删除溯源
    public void deleteById(int id){
        if (head == null){
            System.out.println("链表为空");
        }
        if (head.id == id){
            head = null;
            System.out.println("删除成功");
        }else {
            Emp curEmp = head;
            while(true){
                if (curEmp.next == null){
                    System.out.println("没有找到");
                    break;
                }
                if (curEmp.next.id == id && curEmp.next.next == null){
                    curEmp = null;
                    System.out.println("删除成功");
                    break;
                }
                if (curEmp.next.id == id && curEmp.next.next != null){
                    curEmp.next = curEmp.next.next;
                    System.out.println("删除成功");
                    break;
                }
                curEmp = curEmp.next;
            }
        }
    }
}


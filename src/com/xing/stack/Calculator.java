package com.xing.stack;

public class Calculator {
    public static void main(String[] args) {
        //根据思路，完成表达式的运算
        String expression = "30+2*6-2";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index=0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0; //接收操作符的变量，可以用int也可以char
        int res = 0;
        char ch= ' '; //将每次扫描得到的char保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始用while循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){ //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈不为空，进行比较，如果当前的操作符优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将结果入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //将运算结果入数栈
                        numStack.push(res);
                        //将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果操作符大于栈中的符号，直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            }else {
                //如果是数，则直接入数栈
                //numStack.push(ch-'0'); //ch是字符，转为int类型asici码减48
                //或者写为 numStack.push(ch-48);
                //1.当处理多位数时，不能发现是一个数就直接入栈
                //2.处理数时，要想expression的表达式的index后再看一位，如果是数就再扫描，如果是符号才入栈
                //3.因此需要定义一个变量字符串用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch是expression的最后一位
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //判断下一个字符是不是数字，如果是就继续扫描，如果是运算符则入栈
                    //只是看后一位，不是index++
                    //如果后一位是运算符，则入栈
                    numStack.push(Integer.parseInt(keepNum));
                    //重要的！！！,keepNum清空
                    keepNum="";
                }
            }
            //让index+1，并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕时，顺序从数栈和符号栈中pop出相应的数和字符
        while(true){
            //如果符号栈为空，就计算到最后结果，数栈只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈
        }
        //将数栈的最后的数pop出来
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d",expression,res2);
    }
}

class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈
    private int top = -1; //top表示栈顶，初始化为-1
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回栈顶的一个值，但不是真正pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean idFull(){
        return top==maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top==-1;
    }

    //入栈
    public void push(int value){
        //判断栈是否满
        if (idFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的数据返回
    public int pop(){
        //判断栈是否空
        if (isEmpty()){
            throw new RuntimeException("栈空,没有数据");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //显示栈（遍历栈）,遍历时需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级
    //数字越大，优先级越高
    public int priority(int oper){
        if (oper == '*'||oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1; //假定表达式只有加减乘除
        }
    }
    //判断是不是运算符
    public boolean isOper(char val){
        return val=='+' || val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}

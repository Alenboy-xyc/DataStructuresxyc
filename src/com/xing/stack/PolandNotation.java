package com.xing.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转后后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5  --->1 2 3 + 4 * + 5 -
        //2. 因为直接对字符串操作不方便，因此先将字符串转成中缀表达式对应的List
        //即"1+((2+3)*4)-5" ===>ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List转为后缀表达式对应的List
        //即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] =>ArrayList[1,2,3,+,4,*,+,5,-]
        String expression="1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List="+infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List="+suffixExpressionList);
        System.out.printf("expression=%d\n",calculate(suffixExpressionList));
        //先定义一个逆波兰表达式
        //(3+4)*5-6
        String suffixExpression="3 4 + 5 * 6 -";
        //思路
        //1.先将“3 4 + 5 * 6 -”放到ArrayList中
        //2.将ArrayList传给一个方法，配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        int res = calculate(rpnList);
        System.out.println("计算的结果是="+res);
    }
    //即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] =>ArrayList[1,2,3,+,4,*,+,5,-]
    //方法：将得到的中缀List换为后缀List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack直接用List s2
        List<String> s2 = new ArrayList<String>(); //储存中间结果
        //遍历ls
        for (String item:ls){
            //如果是一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号，则依次弹出s1栈顶的运算符，压入s2，知道遇到左括号为止，将这一括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //!!!将（弹出s1栈，消除小括号
            }else {
                //当item的优先级<=栈顶运算符，将s1栈顶的运算符弹出并加入s2中，再次转到（4.1）与
                //s1中新的栈顶运算符相比较
                //问题：缺少运算符比较方法
                while(s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2; //注意因为是存放到List，因此按顺序输出就是逆波兰输出
    }


    //方法：将中缀表达式转成对应的List
    //s="1+((2+3)*4)-5";
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式
        List<String> ls = new ArrayList<String>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c; //每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，需要放到ls
            if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
                ls.add(""+c);
                i++;
            }else { //如果是一个数，需考虑多位数
                str=""; //先将str置成”“
                while(i<s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }


    //将一个逆波兰表达式，依次将数据和运算符放到ArrayList
    public static List<String> getListString(String suffixExpression){
        String[] split=suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰的运算
    /**
     * 1.从左往右扫描，将3和4压入堆栈
     * 2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算3+4，得7，再将7入栈
     * 3.将5入栈
     * 4.接下来时*运算符，弹出5和7，计算出7*5=35，将35入栈
     * 5.将6入栈
     * 6.最后时-运算符，计算出35-6的值，即29，由此得出结果
     */
    public static int calculate(List<String> ls) {
        //创建栈，，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有问题");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在static就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
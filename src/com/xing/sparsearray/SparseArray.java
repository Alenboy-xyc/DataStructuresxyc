package com.xing.sparsearray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建11*11数组
        //0:没有棋子 1:黑子 2:蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("输出原始数组~~~");
        for (int row[] : chessArr1) {
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组变为稀疏数组
        //遍历二维数组，得到非零的个数
        int sum = 0;
        for (int i = 0 ; i < 11 ; i++){
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //创建对应稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非零的数放到稀疏数组中
        int count = 0; //用于记录第几个非零数
        for (int i = 0 ; i < 11 ; i++){
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("输出稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        //将稀疏数组放到文件array.txt中
        File file = new File("E:\\ideaProject\\DataStructures\\array.txt");
        FileWriter out = new FileWriter(file);
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<3;j++){
                out.write(sparseArr[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();

        //将稀疏数组从文件array.txt中取出
        int sparseArr2[][] = new int[sum+1][3];
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;  //一行数据
        int row=0;
        //逐行读取，并将每个数组放入到数组中
        while((line = in.readLine()) != null){
            String[] temp = line.split("\t");
            for(int j=0;j<temp.length;j++){
                sparseArr2[row][j] = Integer.parseInt(temp[j]);
            }
            row++;
        }
        in.close();

        //将稀疏数组恢复到二维数组
        //先读取稀疏数组第一行，创建相应的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("输出恢复后二维数组");
        for (int row1[] : chessArr2) {
            for (int data : row1){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}

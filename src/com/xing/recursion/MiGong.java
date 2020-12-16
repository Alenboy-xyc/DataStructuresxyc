package com.xing.recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图的情况
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        //找路
        setWay(map,1,1);
        System.out.println("小球找路的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路
    //约定：当map[i][j]为0表示该点没有走过，当为1时表示墙，2表示通路可走，3表示已经走过并且走不通
    //策略：下--》右--》上--》左
    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置找
     * @param j
     * @return 如果找到通路，返回true
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j]=2;
                if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i-1,j)){
                    return true;
                }else if (setWay(map,i,j-1)){
                    return true;
                }else {
                    //说明走不通，死路
                    map[i][j] = 3;
                    return false;
                }
            }else { //如果map[i][j]!=0,可能是1，2，3
                return false;
            }
        }
    }
}

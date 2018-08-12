package toutiao_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
/*
 * 字节跳动笔试题：
 * 寻求一个矩阵中所有的连通域个数(上下左右、斜对角都算连通),并输出最大的连通域包含的连通数
 * 测试用例：
10,10
0,0,0,1,0,1,0,0,0,0
0,0,1,1,0,1,0,0,0,0
0,0,1,1,0,0,1,0,0,0
0,0,0,0,0,0,0,0,0,0
0,1,0,1,0,1,1,0,0,0
0,0,1,1,1,1,0,0,0,0
0,0,0,1,0,0,0,0,1,0
0,0,0,0,0,0,1,0,0,1
0,0,0,1,1,1,1,0,0,0
0,0,0,1,0,0,1,0,0,0
 */
import java.util.List;
public class TestApp {

	private static int span;//矩阵的行数
	private static int row;//矩阵的列数
	//把每个连通域的连通数都存入这个集合中，最后取最大的数即可
	private static List<Integer> crow = new ArrayList<Integer>();
	//连通域的连通数，初始为0
	private static int p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//读入矩阵大小
		String size = br.readLine();//大小
		String[] lines = size.split(",");
		span = Integer.parseInt(lines[0]);
		row = Integer.parseInt(lines[1]);
		//构建相应大小的矩阵
		int[][] maze = new int[span][row];
		//循环赋值，并转换为整型
		for (int i = 0; i < span; i++) {
			String[] strings = br.readLine().split(",");
			for (int j = 0; j < row; j++) {
				maze[i][j] = Integer.parseInt(strings[j]); 
			}
		}
		//关闭输入
		br.close();
		
		find(maze,span,row);
		
	}
	public static void find(int[][] maze,int span,int row){
		int a = 0;//连通域个数
		for (int i = 0; i < span; i++) {
			for (int j = 0; j < row; j++) {
				p = 0;
				set(maze, i, j);	
				crow.add(p);//把每个连通域包含的个数添加进集合
				//若连通数不为0，则说明有连通域
				if (p!=0) {
					a++;
				}
			}
		}
		System.out.println(a);
		System.out.println(Collections.max(crow));
	}
	public static void set(int[][] maze,int a,int b){
		//递归方法
		if (maze[a][b]==1) {
			maze[a][b]=0;
			p++;
			//探测上面一行
			if (a-1>=0&&b-1>=0&&b+1<row) {
				set(maze, a-1, b-1);
				set(maze, a-1, b+1);
				set(maze, a-1, b);
				
			}
			//探测此行
			if (b-1>=0&&b+1<row) {
				set(maze, a, b-1);
				set(maze, a, b+1);
			}
			//探测下面一行
			if (a+1<span&&b-1>=0&&b+1<row) {
				set(maze, a+1, b);
				set(maze, a+1, b-1);
				set(maze, a+1, b+1);
			}
		}		
	}
}

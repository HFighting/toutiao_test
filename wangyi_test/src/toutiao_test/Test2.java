package toutiao_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 字节跳动笔试题：
 * 输入不同的区间,区间可以重叠,可以合并,最后输出这些不同的区间
 */
public class Test2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//首先输入接下来要输入的行数,并转换为整型数据
		String s = br.readLine();
		int size = Integer.parseInt(s);
		//存储输入的区间所用的集合
		List<Integer[]> list = new ArrayList<Integer[]>();//存储区间
		while (size>0) {			
			String string = br.readLine();
			String[] str1 = string.split(";");
			if (str1==null) {
				return;
			}
			for (int i = 0; i < str1.length; i++) {
				String[] str2 = str1[i].split(",");
				Integer[] a = new Integer[2];
				if (str2==null) {
					return;
				}
				//区间全部转化为数组
				for (int j = 0; j < str2.length; j++) {
					a[j] = Integer.parseInt(str2[j]);
				}
				list.add(a);
			}
			size = size-1;
		}
		br.close();
		//合并list中的区间并输出
		combine(list);
				
	}
	public static void combine(List<Integer[]> list) {
		//存储合并的区间所用的集合
		List<Integer[]> list2 = new ArrayList<Integer[]>();
		for (Integer[] b : list) {
			//若该区间为第一个区间,则直接添加进集合中
			if (list2.isEmpty()) {
				list2.add(b);
			}else {
				//遍历集合,比较该区间是否可以添加进集合中
				for (Integer[] c : list2) {
					if (b[0]>=c[0]) {
						Integer[] d = new Integer[2];
						if (b[1]<c[1]) {
							continue;
						}
						if (b[1]>c[1]) {
							d[0] = c[0];d[1] = b[1];
							list2.remove(c);//删除该区间,并添加新的合并区间
							list2.add(d);
						}
					}
					if (b[0]<c[0]) {
						Integer[] d = new Integer[2];
						if (b[1]>=c[1]) {						
							d[0] = b[0];d[1] = c[1];
							list2.remove(c);
							list2.add(d);
						}
						if (b[1]<c[1]) {
							d[0] = b[0];d[1] = c[1];
							list2.remove(c);
							list2.add(d);
						}
					}
				}
			}
			
		}
		for (Integer[] integers : list2) {
			System.out.println(integers[0]+","+integers[1]);
		}
		
	}
	
	
}

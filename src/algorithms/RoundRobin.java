package algorithms;

import java.util.Scanner;

/*
 * 问题描述 
   设有n位选手参加羽毛球循环赛，循环赛共进行n-1天，每位选手要与其他n-1位
   选手比赛一场，且每位选手每天比赛一场，不能轮空，按此要求为比赛安排日
   程，并可将比赛日程表设计成一个n行n-1列的二维表，其中，第i行第j列表是
   和第i个选手在第j天比赛的选手。 

 */

public class RoundRobin {
	static int[][] a = new int[16][16];

	public static void main(String[] args) {
		// 采用分治法
		System.out.print("How many player duo you want?");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		System.out.println("There are " + n + " player\n");
		arrangement(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void arrangement(int n) {
		if (n == 1) {
			a[0][0] = 1;
			return;
		} else {
			arrangement(n / 2); // 思想：只要确定2人比赛的顺序，那么4人顺序可定，那么8人顺序可定
			merger(n); // 那么就可以确定16人的比赛顺序
		}
	}

	private static void merger(int n) {
		int m = n / 2;
		System.out.println("m = " + m);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j + m] = a[i][j] + m; // 左上算右上
				System.out.printf("a[%d][%d] == a[%d][%d]+%d = %d \t", i, j + m, i, j, m, a[i][j + m]);
				a[i + m][j] = a[i][j + m]; // 右上算右下
				System.out.printf("a[%d][%d] == a[%d][%d] = %d \t", i + m, j, i, j + m, a[i + m][j]);
				a[i + m][j + m] = a[i][j]; // 左上算右下
				System.out.printf("a[%d][%d] == a[%d][%d] = %d\n", i + m, j + m, i, j, a[i + m][j + m]);
			}
			System.out.println();
		}
	}
}

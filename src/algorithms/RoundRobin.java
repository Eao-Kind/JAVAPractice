package algorithms;

import java.util.Scanner;

/*
 * �������� 
   ����nλѡ�ֲμ���ë��ѭ������ѭ����������n-1�죬ÿλѡ��Ҫ������n-1λ
   ѡ�ֱ���һ������ÿλѡ��ÿ�����һ���������ֿգ�����Ҫ��Ϊ����������
   �̣����ɽ������ճ̱���Ƴ�һ��n��n-1�еĶ�ά�����У���i�е�j�б���
   �͵�i��ѡ���ڵ�j�������ѡ�֡� 

 */

public class RoundRobin {
	static int[][] a = new int[16][16];

	public static void main(String[] args) {
		// ���÷��η�
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
			arrangement(n / 2); // ˼�룺ֻҪȷ��2�˱�����˳����ô4��˳��ɶ�����ô8��˳��ɶ�
			merger(n); // ��ô�Ϳ���ȷ��16�˵ı���˳��
		}
	}

	private static void merger(int n) {
		int m = n / 2;
		System.out.println("m = " + m);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j + m] = a[i][j] + m; // ����������
				System.out.printf("a[%d][%d] == a[%d][%d]+%d = %d \t", i, j + m, i, j, m, a[i][j + m]);
				a[i + m][j] = a[i][j + m]; // ����������
				System.out.printf("a[%d][%d] == a[%d][%d] = %d \t", i + m, j, i, j + m, a[i + m][j]);
				a[i + m][j + m] = a[i][j]; // ����������
				System.out.printf("a[%d][%d] == a[%d][%d] = %d\n", i + m, j + m, i, j, a[i + m][j + m]);
			}
			System.out.println();
		}
	}
}

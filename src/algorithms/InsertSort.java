package algorithms;

import java.util.Arrays;

/*
 * Ӧ�ò������������2,6,1,4,5,3,2��������
 */

public class InsertSort {
	public static void main(String[] args) {
		int[] a = { 2, 6, 1, 4, 5, 3, 2 };
		Insertsort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void Insertsort(int[] a) {
		int i, j;
		for (i = 1; i < a.length; i++) {
			int cur = a[i]; // ���Ƚ�ֵ
			for (j = i - 1; j >= 0 && a[j] > cur; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = cur;

		}
	}
}

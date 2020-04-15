package algorithms;

public class Sort {
	public static void selectSort(int[] a) {
		int N = a.length; // 数组长度
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (a[j] < a[min])
					min = j; // 获取最小下标
			}
			exch(a, i, min); //交换位置
		}
	}

	public static void bubbleSort(int[] a) {
		int N = a.length;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (a[j] > a[j + 1])
					exch(a, j, j + 1);
			}
		}
	}

	public static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 2, 6, 1, 4, 5, 3, 2 };// 创建数组
		//selectSort(a); // 选择排序
		bubbleSort(a); //冒泡排序
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");

		}

	}

}

package algorithms;

public class AllSort {
	public static void main(String[] args) {
		int[] a = { 0, 3, 2, 5, 9, 8 };
		Quick.sort(a);
		Sort.show(a);
	}

}

class Sort {

	public static void SelectionSort(int[] a) {
		/*
		 * 选择排序：左有序右无序，遍历右，找未排序中的最小值，放到i位置
		 */
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (less(a[j], a[min])) {
					min = j; // 找到最小值的下标
				}
			}
			exch(a, i, min); // 交换：当前最小值放到第i个位置
		}
	}

	public static void InsertSort(int[] a) {
		/*
		 * 插入排序,左有序右无序，每次找未遍历的数值插入到有序
		 */
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	public static void shellSort(int[] a) {
		/*
		 * 希尔排序
		 */
		int N = a.length;
		for (int h = N / 3 + 1; h >= 1; h /= 3) {
			// 将局部数组变为有序
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
		}
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // 比较大小
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t; // 交换
	}

	public static void show(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}

class MergeSort {
	/*
	 * 递归法归并排序
	 */
	private static int[] aux; // 辅助数组避免频繁开辟空间

	public static void sort(int[] a) {
		aux = new int[a.length];
		sort(a, 0, a.length - 1);
	}

	public static void merge(int[] a, int lo, int mid, int hi) {
		/*
		 * 治 需要两边分别有序
		 */
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; // 辅助数组
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	public static void sort(int[] a, int lo, int hi) {
		// 将数组 a[lo....hi] 排序
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid); // 分
		sort(a, mid + 1, hi); // 分
		merge(a, lo, mid, hi); // 治
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // 比较大小
	}
}

class Quick {
	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // 选基准 切分,保证整体上 左<右
		sort(a, lo, j - 1); // 左边递归 排序
		sort(a, j + 1, hi); // 右边递归 排序
	}

	private static int partition(int[] a, int lo, int hi) {
		// 数组拆分为a[lo...i-1], a[i], a[i+1..hi]
		int i = lo, j = hi + 1; // 左，右扫描指针
		int v = a[lo]; // 切分元素--基准
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v))
				if (i == hi)
					break; // 找出一个>基准
			while (less(v, a[--j]))
				if (i == lo)
					break; // 找出一个<基准
			if (i >= j)
				break;
			exch(a, i, j); // >和< 交换
		}
		exch(a, lo, j); // 基准和a[i] (a[i]<v) 交换，保证整体有序
		return j; // 返回i好像也可以
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // 比较大小
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t; // 交换
	}
}

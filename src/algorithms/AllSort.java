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
		 * ѡ�����������������򣬱����ң���δ�����е���Сֵ���ŵ�iλ��
		 */
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (less(a[j], a[min])) {
					min = j; // �ҵ���Сֵ���±�
				}
			}
			exch(a, i, min); // ��������ǰ��Сֵ�ŵ���i��λ��
		}
	}

	public static void InsertSort(int[] a) {
		/*
		 * ��������,������������ÿ����δ��������ֵ���뵽����
		 */
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	public static void shellSort(int[] a) {
		/*
		 * ϣ������
		 */
		int N = a.length;
		for (int h = N / 3 + 1; h >= 1; h /= 3) {
			// ���ֲ������Ϊ����
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
		}
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // �Ƚϴ�С
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t; // ����
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
	 * �ݹ鷨�鲢����
	 */
	private static int[] aux; // �����������Ƶ�����ٿռ�

	public static void sort(int[] a) {
		aux = new int[a.length];
		sort(a, 0, a.length - 1);
	}

	public static void merge(int[] a, int lo, int mid, int hi) {
		/*
		 * �� ��Ҫ���߷ֱ�����
		 */
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; // ��������
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
		// ������ a[lo....hi] ����
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid); // ��
		sort(a, mid + 1, hi); // ��
		merge(a, lo, mid, hi); // ��
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // �Ƚϴ�С
	}
}

class Quick {
	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // ѡ��׼ �з�,��֤������ ��<��
		sort(a, lo, j - 1); // ��ߵݹ� ����
		sort(a, j + 1, hi); // �ұߵݹ� ����
	}

	private static int partition(int[] a, int lo, int hi) {
		// ������Ϊa[lo...i-1], a[i], a[i+1..hi]
		int i = lo, j = hi + 1; // ����ɨ��ָ��
		int v = a[lo]; // �з�Ԫ��--��׼
		while (true) {
			// ɨ�����ң����ɨ���Ƿ����������Ԫ��
			while (less(a[++i], v))
				if (i == hi)
					break; // �ҳ�һ��>��׼
			while (less(v, a[--j]))
				if (i == lo)
					break; // �ҳ�һ��<��׼
			if (i >= j)
				break;
			exch(a, i, j); // >��< ����
		}
		exch(a, lo, j); // ��׼��a[i] (a[i]<v) ��������֤��������
		return j; // ����i����Ҳ����
	}

	private static boolean less(int v, int w) {
		return v < w ? true : false; // �Ƚϴ�С
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t; // ����
	}
}

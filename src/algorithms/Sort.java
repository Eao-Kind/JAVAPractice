package algorithms;

public class Sort {
	public static void selectSort(int[] a) {
		int N = a.length; // ���鳤��
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (a[j] < a[min])
					min = j; // ��ȡ��С�±�
			}
			exch(a, i, min); //����λ��
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
		int[] a = new int[] { 2, 6, 1, 4, 5, 3, 2 };// ��������
		//selectSort(a); // ѡ������
		bubbleSort(a); //ð������
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");

		}

	}

}

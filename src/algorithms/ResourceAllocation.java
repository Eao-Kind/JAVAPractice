package algorithms;

public class ResourceAllocation {

	public static void main(String[] args) {
		int[][] f = { { 0, 0, 0, 0, 0 }, // û�е�0����Ŀ
				{ 0, 15, 28, 40, 51 }, { 0, 13, 29, 43, 55 }, { 0, 11, 30, 45, 58 }, };

		System.out.println("�������:"+SolutionInvest.investMax(f));
	}
}

class SolutionInvest {
	static int M = 4 + 1; // Ͷ������+1��4��Ԫ�� ��Ϊ����һ��0����
	static int N = 3 + 1; // ��Ŀ��+1��3��Ŀ�� ��Ϊ����һ��0����

	public static int investMax(int[][] f) {
		/*
		 * �����������
		 * 
		 * @param f��Ͷ�ʾ���
		 */

		int[][] Fsum = new int[N][M]; // ǰN����Ŀ��Ͷ��M��Ԫ���������
		int[][] aItem = new int[N][M]; // ��Ͷ��M��Ԫʱ��N����Ŀ��Ͷ�ʽ�����׷��·��

		// ��ʼ��������������߽����,����������ɱ��
		for (int i = 0; i < M - 1; i++) {
			Fsum[1][i] = f[1][i];
			aItem[1][i] = i;
		}

		// �� k ����Ŀ��Ͷ��
		for (int k = 2; k <= N - 1; k++) {
			// ��һ��һ����0����һ�м�ֻͶ��һ����Ŀ������Ѿ���ʼ�����ʴ�2��ʼ
			for (int m = 1; m <= M - 1; m++) { // M��Ͷ����+1�����Դ˴�ѭ����M-1
				// ǰ K ����Ŀ������ m ��Ԫ���������Ͷ��m��Ԫʱ�����÷���
				int max = -1, temp = 0; // ��ʼ������Ϊ-1
				for (int a = 0; a <= m; a++) {
					// �� k ����Ŀ���� a ��Ԫ�� ����Աȵ�Ͷ�ʶ�Ϊm��Ԫʱ�򣬵�K����Ŀ�����Ͷ����
					if (f[k][a] + Fsum[k - 1][m - a] > max) {
						// fk(x) = d ��ʾ��x ��ԪͶ���� k ����Ŀ��Ч��Ϊ d.
						// �Ա� f2(1)+F1(0), f2(0)+F1(1) �ó��õ�Ͷ�ʷ���
						max = f[k][a] + Fsum[k - 1][m - a];
						temp = a; // ��¼��Ͷ��Ϊm��Ԫʱ��k����Ŀ��Ͷ�ʽ��
					}
				}
				Fsum[k][m] = max; // ǰK����Ŀ��Ͷ��m��Ԫʱ����������
				aItem[k][m] = temp; // ��¼Ͷ��m��Ԫʱ���k����Ŀ��Ͷ�ʽ��
			}
		}
		printArray(Fsum);
		printArray(aItem);
		printInfo(aItem); // ���Ͷ����Ϣ
		return Fsum[N - 1][M - 1];
	}

	private static void printInfo(int[][] aItem) {
		int index = M - 1;
		for (int k = N - 1; k > 0; k--) {
			System.out.println("��" + k + "����Ŀ��Ͷ��" + aItem[k][index] + "��Ԫ");
			index -= aItem[k][index];
		}

	}

	private static void printArray(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

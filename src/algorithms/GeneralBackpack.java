package algorithms;

public class GeneralBackpack {
	public static void main(String[] args) {
		float[] w = { 0, 10, 30, 20 };
		float[] v = { 0, 50, 120, 60 };
		float[] x = new float[4];
		float C = 50;
		int n = 4;
		float total = bag.Knapsack(w, v, x, C, n);
		System.out.println("�ܼ�ֵ:" + total);
		for (int i = 1; i < x.length; i++) {
			System.out.printf("��%d����Ʒ����Ϊ:%f,��ֵΪ%f\n", i, x[i], x[i] * v[i]);
		}
	}
}

class bag {
	public static float Knapsack(float[] w, float[] v, float[] x, float C, int n) {
		/**
		 * n����Ʒ�Ѿ�����V/W �ɴ�С ̰�Ĳ��ԣ����Ǽ�ֵ�������������Ķ��ߵ��ۺ�Ч���ķ�������ÿ��ѡ���ֵ��������u:/w
		 * 
		 * @param w: ����
		 * @param v: ��ֵ
		 * @param x: ��
		 * @param C: ��������
		 * @param n: ��Ʒ����
		 * @return total �ܼ�ֵ
		 */
		for (int i = 0; i < n; i++) {
			x[i] = 0; // ��ʼ��������
		}
		int i = 1; // ���ڴ�1��ʼ ����w��vǰ�涼Ҫ��0
		float total = 0; // ������
		while (w[i] < C) { // ���к���feasible / �������solution
			x[i] = 1;
			total = total + v[i]; // �⼯�� ���� �ܼ�ֵ
			C = C - w[i]; // ʣ�౳������
			i++; // ��һ����Ʒ
		}
		x[i] = C / w[i]; // ʹ����ʣ���һ��ռ�
		total = total + v[i] * x[i];
		return total;
	}
}
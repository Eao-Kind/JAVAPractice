package chapter_1;

import java.util.Arrays;

public class SY8 {

	public static void main(String[] args) {
		double a[] = { 9.89, 9.88, 9.99, 9.12, 9.69, 9.76, 8.97 };
		double b[] = { 89, 56, 78, 90, 100, 77, 56, 45, 36, 79, 98 };
		Diving di = new Diving();
		System.out.println("��ˮƽ���֣�" + di.average(a));
		School sc = new School();
		System.out.println("ѧ��ƽ���֣�" + sc.average(b));
	}

}

class Diving implements CompureAverage {
	public double average(double[] x) {
		// ��������
		Arrays.sort(x);
		double sum = 0;
		// ȥ����߷���ͷ�
		for (int i = 1; i < x.length - 1; i++) {
			sum += x[i];
		}
		return sum / (x.length - 2);
	}
}

class School implements CompureAverage {
	public double average(double[] x) {
		double sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i];
		}
		return sum / x.length;
	}
}

interface CompureAverage {
	public abstract double average(double[] x);
}
package project;

public class project4 {
	public static void main(String[] args) {
		Genetic t = new Genetic();
		t.test(100, 50);
	}
}

class Genetic {

	public int[] InitialSolution(int length) { // ��ʼ�� ��� 0/1
		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = (Math.random() > 0.5) ? 1 : 0;
		return a;
	}

	public int[] variation(int[] a) { // ����
		int[] newarray = new int[a.length];
		for (int i = 0; i < a.length; i++) // a[i]-1*��-1�� ��1/length���ʴ�0��1��1��0
			newarray[i] = (Math.random() < 1.0 / a.length) ? (a[i] - 1) * (-1) : a[i];
		return newarray;
	}

	public boolean eliminate(int[] a, int fx) { // ��Ȼѡ�񡪡������ж��Ƿ���x' ������������أ����xi
		return arraySum(a) > fx;
	}

	public int genetic(int length) { // ���� ���Ž�ֵ
		int[] a = InitialSolution(length); // ����һ������Ϊlength�������
		int fx = arraySum(a), count = 0, maxfx = length;
		while (count < 200) {
			int[] newarray = variation(a); // ����
			if (eliminate(newarray, fx)) { // ѡ��
				fx = arraySum(newarray);
				a = newarray;
			}
			count++;
		}
		return fx;
	}

	public void test(int times, int length) {
		int count = 0;
		int[] countArray = new int[times];
		while (count < times) {
			int fx = genetic(length);
			System.out.printf("��%d�εõ����Ž�Ϊ:%d\n", count + 1, fx);
			countArray[count++] = fx;
		}
		System.out.println("ƽ�����Ž�Ϊ��" + arraySum(countArray) / times);
	}

	public int arraySum(int[] temp) {
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[i];
		}
		return sum;
	}
}
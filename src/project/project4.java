package project;

public class project4 {
	public static void main(String[] args) {
		Genetic t = new Genetic();
		int times = 100, n = 50; // ʵ������������ģ
		t.test(times, n);
	}
}

class Genetic {

	public int[] InitialSolution(int length) { // ��ʼ�� ��� 0/1
		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = (int) (Math.random() * 2); //
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
		while (count < 200) { // 200����Ȼѡ��
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

/*
 *(1+1)EA��һ�ּ򵥲�����Ч���ݻ��㷨,���������ݻ���������۷����У��������˱����ѡ�����ֲ���������Ⱥ��СΪ1. ���� ��Ҫ��󻯵�Ŀ�꺯��������Ӧֵ����.(1+1)EA�����������£�

�㷨. (1+1) EA.
1��Begin
2����ʼ�����������һ����ʼ�� ,�� ��
3�� While(��ֹ����������) ��o 
4��   �� �ĸ��ʶ����ķ�ת ��ÿһλ �õ��½�  �����죩
5:    ��� ,�� ��ѡ��
6�� End While
7: ����  
8: End
�������(1+1)EA�㷨���OneMax������OneMax���������� �� ���Կ���һ��λ���� ��Ҳ����˵ �� 
����˵����
�����㷨��2���в����������Ϊ ���� ��
���3����while�е���ֹ������������
�ڵ�4���б���õ��� (Ҳ���ܵõ�һ������Ľ⣬���� �����ڵ�5
���лᱻ����)
�ڵ�5���бȽ� 
�ڵ�6���н�������whileѭ��
��Ϊû�еõ����Ž⣬���Իص���3��������ѭ����ֱ���ҵ����Ž⣬��ֹͣѭ����������Ž� .

Ҫ�󣺲���(1+1)EA�㷨���OneMax���������Ž⡣���㷨��Ҫ������Ž�ĵ������� �� ��ָwhile�����ĵ���������������������㷨�����һ�����Ž�� ���ܳ��ֺܴ������ԣ�������Ҫ��100�Σ��õ��������� ��ƽ��ֵ. OneMax������n=50��n������Ĺ�ģ��
 
 */

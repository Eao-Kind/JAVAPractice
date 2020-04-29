package algorithms;

import java.util.Scanner;

public class RussianMultiplication {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������������֣��س��������");
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();
		int res = Rmult(n, m);
		System.out.printf("%d * %d == %d", n, m, res);
	}

	public static int Rmult(int n, int m) {
		int sum = 0;
		while (n != 0) {
			if (n % 2 != 0) {
				n--; // �������
				sum += m;
			} else {
				n >>= 1;
				m <<= 1;
			}
		}
		return sum;
	}
}

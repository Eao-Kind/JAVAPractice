package algorithms;

import java.util.Scanner;

/*
 * 分治法实现大数相乘
 */


public class BigMult {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入第一个整数:");
		int numOne = sc.nextInt();
		System.out.print("请输入第二个整数:");
		int numTwo = sc.nextInt();
		sc.close();
		long S = mult(numOne, numTwo, 4);
		System.out.println(S);
	}

	public static long mult(long x, long y, int n) {
		long S = sign(x) * sign(y);
		long num1 = Math.abs(x);
		long num2 = Math.abs(y);
		long A, B, C, D, AC, BD, ABDC;
		if (n == 1) {
			return x * y;
		} else {
			A = (long) (num1 / Math.pow(10, n / 2));
			//System.out.println("A = " + A);
			B = (long) (num1 - A * Math.pow(10, n / 2));
			//System.out.println("B = " + B);
			C = (long) (num2 / Math.pow(10, n / 2));
			D = (long) (num2 - C * Math.pow(10, n / 2));
			AC = mult(A, C, n / 2);
			BD = mult(B, D, n / 2);
			ABDC = mult((A - B), (D - C), n / 2) + AC + BD;
			S = S * (long) (AC * Math.pow(10, n) + ABDC * Math.pow(10, n / 2) + BD);
		}
		return S;
	}

	public static int sign(long num) {
		return num > 0 ? 1 : -1;
	}
}
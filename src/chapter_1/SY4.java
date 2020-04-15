package chapter_1;

import java.util.Scanner;

public class SY4 {
	public static double todaygive(double lastDay) {
		return lastDay * 2;
	}

	public static void Print(double[] a) {
		for (int j = 1; j <= a.length; j++) {
			System.out.println("第" + j + "天：" + a[j - 1]);
		}
	}

	public static void problemWhile (double initial_value, int day) {
		int i = 1;
		double[] money = new double[day]; // 数组保存每日的钱
		double  sum = 0.01;
		money[0] =initial_value;
		while (i < day) {
			money[i] = todaygive(initial_value); // 今天要给的钱
			initial_value = money[i];
			sum += initial_value;
			i++;
		}
		Print(money);
		System.out.println("总彩礼:"+sum);
	}

	public static double problemFor(double initial_value, int day) {
		double sum=0;
		for (int i = 0; i < day; i++) {
			sum += initial_value;
			initial_value *=2;
		}
		return sum;
	}

	public static void problemDowhile() {
		int i = 1;
		double[] money = new double[30];
		double firstday = 0.01;
		double lastday = firstday, sum = 0.01;
		money[0] = 0.01;
		do {
			money[i] = todaygive(lastday); // 今天要给的钱
			lastday = money[i];
			sum += lastday;
		} while (i++ < 29);
		Print(money);
		System.out.println("总彩礼：" + sum);
	}

	public static void problemLast() {
		System.out.println("请输入天数:");
		Scanner reader = new Scanner(System.in);
		int numday = reader.nextInt(); // 读取输入
		reader.close();
		double[] money = new double[30];
		double firstday = 0.01, sum = 0.01;
		money[0] = 0.01;
		double lastday = firstday;
		for (int i = 1; i < numday; i++) {
			money[i] = todaygive(lastday); // 今天要给的钱
			lastday = money[i];
			sum += lastday;
		}
		//System.out.println("第 %d天的彩礼是：\t 目前总彩礼数是：%f",numday,sum);
		
	}

	public static void main(String[] args) {
		final double initial_value = 0.01;
		problemWhile(initial_value, 30);
		//problemFor(initial_value, 30);
		//problemDowhile();
		//problemLast();

	}
}

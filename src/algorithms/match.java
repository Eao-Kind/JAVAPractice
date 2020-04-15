package algorithms;

import java.util.Scanner;

public class match {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入子串：");
		String key = sc.nextLine(); // 输入待匹配子字串
		int num = bfSearch("我我是愚人节啊哈哈", key.toString());
		sc.close();
		System.out.println(num+1); //输出匹配到的位置，若无，则输出text长度
	}

	public static int bfSearch(String text, String key) {
		int M = text.length();
		int N = key.length();
		for (int i = 0; i < M - N; i++) {
			int j;
			for (j = 0; j < N; j++) {
				if (text.charAt(i + j) != key.charAt(j))
					break;
			}
			if (j == N)
				return i; //匹配成功
		}
		return M;  //匹配失败

	}
}

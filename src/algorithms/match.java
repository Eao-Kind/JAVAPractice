package algorithms;

import java.util.Scanner;

public class match {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������Ӵ���");
		String key = sc.nextLine(); // �����ƥ�����ִ�
		int num = bfSearch("���������˽ڰ�����", key.toString());
		sc.close();
		System.out.println(num+1); //���ƥ�䵽��λ�ã����ޣ������text����
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
				return i; //ƥ��ɹ�
		}
		return M;  //ƥ��ʧ��

	}
}

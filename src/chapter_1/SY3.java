package chapter_1;

public class SY3 {
	public static void main(String[] args) {
		printEnglishAlphabet();
		printGreekAlphabet();

	}

	public static void printEnglishAlphabet() {
		System.out.print("Ӣ����ĸ��");
		for (int i = (int) 'a'; i < (int) 'z'; i++)
			System.out.print((char) i + " ");
		System.out.println();
	}

	public static void printGreekAlphabet() {
		System.out.print("ϣ����ĸ��");
		char[] t = new char[25];
		for (int i=0; i+'��' <= (int)'��'; i++)
			t[i] = (char)(i+'��');
		for (int i = 0; i < 25; i++)
			System.out.print(t[i] + " ");

	}
}

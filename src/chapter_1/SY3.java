package chapter_1;

public class SY3 {
	public static void main(String[] args) {
		printEnglishAlphabet();
		printGreekAlphabet();

	}

	public static void printEnglishAlphabet() {
		System.out.print("Ó¢ÎÄ×ÖÄ¸£º");
		for (int i = (int) 'a'; i < (int) 'z'; i++)
			System.out.print((char) i + " ");
		System.out.println();
	}

	public static void printGreekAlphabet() {
		System.out.print("Ï£À°×ÖÄ¸£º");
		char[] t = new char[25];
		for (int i=0; i+'¦Á' <= (int)'¦Ø'; i++)
			t[i] = (char)(i+'¦Á');
		for (int i = 0; i < 25; i++)
			System.out.print(t[i] + " ");

	}
}

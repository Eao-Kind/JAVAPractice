package chapter_1;

public class SY14 {
	public static void main(String[] args) {
		System.out.println("��ʽһ:ʵ��Runnable�ӿ�");
		for (int i = 0; i < 10; i++) 
			new Thread(new MyAddOne(i * 10 + 1, i * 10 + 10)).start();
		Thread.currentThread().setPriority(3);
		System.out.println("\n��ʽ��:�̳�Thread��");
		for (int i = 0; i < 10; i++) 
			new Thread(new MyAddTwo(i * 10 + 1, i * 10 + 10)).start();
		Thread.currentThread().setPriority(5);
	}
}

class MyAddOne implements Runnable { // ��ʽһ ʵ��Runnable��
	int start, end, sum;
	
	MyAddOne(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			sum += i;
		}
		System.out.print(sum + " ");
	}
}

class MyAddTwo extends Thread { // ��ʽ�� �̳�Thread��
	int start, end, sum;
	
	MyAddTwo(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			sum += i;
		}
		System.out.print(sum + " ");
	}
}
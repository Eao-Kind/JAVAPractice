package chapter_1;

public class SY14 {
	public static void main(String[] args) {
		System.out.println("方式一:实现Runnable接口");
		for (int i = 0; i < 10; i++) 
			new Thread(new MyAddOne(i * 10 + 1, i * 10 + 10)).start();
		Thread.currentThread().setPriority(3);
		System.out.println("\n方式二:继承Thread类");
		for (int i = 0; i < 10; i++) 
			new Thread(new MyAddTwo(i * 10 + 1, i * 10 + 10)).start();
		Thread.currentThread().setPriority(5);
	}
}

class MyAddOne implements Runnable { // 方式一 实现Runnable类
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

class MyAddTwo extends Thread { // 方式二 继承Thread类
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
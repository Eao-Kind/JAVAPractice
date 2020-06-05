package chapter_1;

public class SY14 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("方式一:实现Runnable接口");
		Thread t1 = new Thread();
		for (int i = 0; i < 10; i++) {
			t1 = new Thread(new MyAddOne(i * 10 + 1, i * 10 + 10));
			t1.start();
		}
		t1.join();
		// new Thread(new MyAddOne(i * 10 + 1, i * 10 + 10)).start();}
		Thread.sleep(50);
		System.out.println("总和" + MyAddOne.allSum);
		System.out.println("\n方式二:继承Thread类");
		for (int i = 0; i < 10; i++)
			new MyAddTwo(i * 10 + 1, i * 10 + 10).start();
		Thread.sleep(50);
		System.out.println("总和" + MyAddTwo.allSum);
	}
}

class MyAddOne implements Runnable { // 方式一 实现Runnable类
	int start, end, temp;
	static int allSum;

	MyAddOne(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			temp += i;
		}
		System.out.println(Thread.currentThread().getName() + "  " + temp);
		allSum += temp;
	}
}

class MyAddTwo extends Thread { // 方式二 继承Thread类
	int start, end, temp;
	static int allSum;

	MyAddTwo(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			temp += i;
		}
		System.out.println(Thread.currentThread().getName() + "  " + temp);
		allSum += temp;
	}
}
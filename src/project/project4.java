package project;

public class project4 {
	public static void main(String[] args) {
		Genetic t = new Genetic();
		t.test(100, 50);
	}
}

class Genetic {

	public int[] InitialSolution(int length) { // 初始解 随机 0/1
		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = (Math.random() > 0.5) ? 1 : 0;
		return a;
	}

	public int[] variation(int[] a) { // 变异
		int[] newarray = new int[a.length];
		for (int i = 0; i < a.length; i++) // a[i]-1*（-1） 以1/length概率从0到1，1到0
			newarray[i] = (Math.random() < 1.0 / a.length) ? (a[i] - 1) * (-1) : a[i];
		return newarray;
	}

	public boolean eliminate(int[] a, int fx) { // 自然选择————判断是否保留x' （与所求函数相关）求和xi
		return arraySum(a) > fx;
	}

	public int genetic(int length) { // 返回 最优解值
		int[] a = InitialSolution(length); // 产生一个长度为length的随机解
		int fx = arraySum(a), count = 0, maxfx = length;
		while (count < 200) {
			int[] newarray = variation(a); // 变异
			if (eliminate(newarray, fx)) { // 选择
				fx = arraySum(newarray);
				a = newarray;
			}
			count++;
		}
		return fx;
	}

	public void test(int times, int length) {
		int count = 0;
		int[] countArray = new int[times];
		while (count < times) {
			int fx = genetic(length);
			System.out.printf("第%d次得到最优解为:%d\n", count + 1, fx);
			countArray[count++] = fx;
		}
		System.out.println("平均最优解为：" + arraySum(countArray) / times);
	}

	public int arraySum(int[] temp) {
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[i];
		}
		return sum;
	}
}